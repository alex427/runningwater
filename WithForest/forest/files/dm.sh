#!/bin/bash  

#type REALTIME代表实时 OFFLINE代表离线 其他的都退出
#不同类型加载的文件不一样
type=$1
if [ "$type" != "REALTIME" -a "$type" != "OFFLINE" ]; then
	echo "sh datamonitor type[NOT NULL:REALTIME/OFFLINE] dt[NULL DEFAULT:yestoday/lasthour eg:20180122/\"2018-08-22 16:00:00\"]"
	exit
fi
oldifs=$IFS
newifs="|"

#相对日期
dt=""
#日期时间,用于实时
dtime=""
#日期小时，用于实时开始小时
dhour=""

#是否生产环境，测试只发送当天和当前小时段
is_production_envi=$2

if [ "$is_production_envi" == "Y" ];then
    if [ "$type" == "OFFLINE" ];then
        #离线内容
        dt=$3
        #默认前一天
        if [ ! -n "$dt" ];then
            dt=`date -d "1 days ago " "+%Y%m%d"`
        fi
    elif [ "$type" == "REALTIME" ];then
        #实时内容
        dtime=$3
        #默认上一小时整点,eg:20180122150000
        if [ ! -n "$dtime" ];then
            dtime="`date -d "1 hours ago " "+%Y-%m-%d %H"`:00:00"
        fi

        #日期，查询时候日期和时间戳一起使用索引
        dt=${dtime:0:4}${dtime:5:2}${dtime:8:2}

        #变成时间戳
        dtime=`date -d "$dtime" +%s`

        dhour=`date -d @$dtime "+%Y%m%d %H"`
    fi
else
	if [ "$type" == "OFFLINE" ];then
        dt=`date -d "1 days ago " "+%Y%m%d"`
    elif [ "$type" == "REALTIME" ];then
        dtime="`date -d "1 hours ago " "+%Y-%m-%d %H"`:00:00"
        #日期，查询时候日期和时间戳一起使用索引
        dt=${dtime:0:4}${dtime:5:2}${dtime:8:2}
        #变成时间戳
        dtime=`date -d "$dtime" +%s`
        dhour=`date -d @$dtime "+%Y%m%d %H"`
    fi
fi

basepath=$(cd `dirname $0`; pwd)
#输入文件夹路径
in=$basepath/data/in
#实时路径
in_realtime=$basepath/data/in_realtime
#
#读取文件
#离线文件放到in中
if [ "$type" == "OFFLINE" ];then
	monitor_infos=`cat $in/*.data| grep -v ^# | grep -v ^$`
elif [ "$type" == "REALTIME" ];then
	monitor_infos=`cat $in_realtime/*.data| grep -v ^# | grep -v ^$`
fi

#读取数据库信息
app_conns=`cat $basepath/conf/db.data| grep -v ^# | grep -v ^$`

#读取配置文件信息，包含impala连接等
IMPALA_CONN=`cat $basepath/conf/datamonitor.property | grep ^IMPALA_CONN | awk -F= '{print $2}'`

#前一天
yestoday=`date -d "$dt -1 days" +%Y%m%d`
#上周
lastweek=`date -d "$dt -1 weeks" +%Y%m%d`

#邮件业务正文内容
#mail样式
mailContent='<style type=\"text/css\">table.gridtable{font-family:微软雅黑;font-size:12px;color:#333;border-width:1px;border-color:#666;border-collapse:collapse}table.gridtable th{border-width:1px;padding:8px;border-style:solid;border-color:#666;background-color:#dedede}table.gridtable td{border-width:1px;padding:8px;border-style:solid;border-color:#666;background-color:#fff}</style>'
if [ "$type" == "OFFLINE" ];then
	#离线内容
	mailContent=${mailContent}'<table class=\"gridtable\"><tr><th>日期</th><th>负责人</th><th>应用</th><th>表名</th><th>列名</th><th>指标描述</th><th>昨日数据</th><th>前一天数据</th><th>上周数据</th><th>阈值</th><th>日环比增长</th><th>周同比增长</th><th>昨日hive数据</th><th>是否相同</th></tr>'
elif [ "$type" == "REALTIME" ];then
	#实时内容
	mailContent=${mailContent}'<table class=\"gridtable\"><tr><th>日期小时</th><th>负责人</th><th>应用</th><th>表名</th><th>列名</th><th>指标描述</th><th>本小时数据</th><th>上一小时数据</th><th>昨天本小时数据</th><th>上周本小时数据</th><th>阈值</th><th>时环比增长</th><th>日同比增长</th><th>周同比增长</th></tr>'
fi
#循环monitor_infos
for monitor_info in $monitor_infos
do
	echo `date +"%Y-%m-%d %H:%M:%S"`":Start querying data..."
	echo $monitor_info
	mailContent=$mailContent'<tr>'

	#split monitor_info
	read app_name table_name metric_name metric_desc threshold user where hive_table_name hive_col_name hive_partition hive_where <<<$(IFS="^"; echo $monitor_info)
	#默认dt字段
	#TODO这个需要放到配置中，目前默认dt
	col_dt="dt"

	#处理where为NULL的情况
	if [ "$where" == "NULL" ]; then
		where=""
	fi
	if [ "$hive_where" == "NULL" ]; then
		hive_where=""
	fi

	#处理DITTO情况
	if [ "$hive_table_name" == "DITTO" ]; then
		hive_table_name="$table_name"
	fi
	if [ "$hive_col_name" == "DITTO" ]; then
		hive_col_name="$metric_name"
	fi
	if [ "$hive_partition" == "DITTO" ]; then
		hive_partition="$col_dt"
	fi
	if [ "$hive_where" == "DITTO" ]; then
		hive_where="$where"
	fi

	#循环库信息，找到当前应用所使用的库信息
	for app_conn in $app_conns
	do
		#定义变量用于接收hive_result
		hive_result="-1"
		if [ "$hive_table_name" != "" ]; then
			#通过impala查询hive中的数据
			echo "-----------------------------------------------"
			echo `date +"%Y-%m-%d %H:%M:%S"`":start querying hive table $hive_table_name"

			#1、刷表
			#${IMPALA_CONN} -q "refresh $hive_table_name"
			#2、查表	hive_table_name  hive_partition hive_where
			HQL="refresh $hive_table_name;select sum($hive_col_name) from $hive_table_name where $hive_partition=\"$dt\" ${hive_where//0x20/ }"

			echo $HQL
			hive_result=`${IMPALA_CONN} -q "$HQL"`

			echo `date +"%Y-%m-%d %H:%M:%S"`":end querying hive table $hive_table_name"
			echo "-----------------------------------------------"
		fi
		#split库信息
		read app_name_conn host dbuser password db <<<$(IFS=","; echo $app_conn)
		#应用名称和库信息比对，取库的连接信息
		if [ "$app_name" == "$app_name_conn" ]; then
			#取表的列字段值，包含当前值、同比、环比
			if [ "$type" == "OFFLINE" ];then
				#离线内容
				sql="select concat(
					 '<td>$dt</td>','<td>$user</td>','<td>$app_name</td>',
					 '<td>$table_name</td>','<td>$metric_name</td>','<td>$metric_desc</td>',
					 concat('<td>',today,'</td>'),concat('<td>',yestoday,'</td>'),concat('<td>',lastweek,'</td>'),'<td>$threshold</td>',
					 case when yestoday=0 then '<td></td>' else
					 concat(case when round((today-yestoday)/yestoday*100,2)>$threshold or round((today-yestoday)/yestoday*100,2)<-$threshold then '<td style="background-color:red">' else '<td>' end,round((today-yestoday)/yestoday*100,2),'</td>') end,
					 case when lastweek=0 then '<td></td>' else
					 concat(case when round((today-lastweek)/lastweek*100,2)>$threshold or round((today-lastweek)/lastweek*100,2)<-$threshold then '<td style="background-color:red">' else '<td>' end,round((today-lastweek)/lastweek*100,2),'</td>') end,
					 (case when $hive_result = '-1' then '<td></td>' else '<td>$hive_result</td>' end),
					 (case when $hive_result = '-1' then '<td></td>' when today = '$hive_result' then '<td>相同</td>' else '<td style="background-color:red">不同</td>' end))
					 from(
						 select
						 ifnull(sum(case when $col_dt = '$dt' then $metric_name else 0 end),0) as today,
						 ifnull(sum(case when $col_dt = '$yestoday' then $metric_name else 0 end),0) as yestoday,
						 ifnull(sum(case when $col_dt = '$lastweek' then $metric_name else 0 end),0) as lastweek
						 from $table_name
						 where $col_dt in( '$dt' ,'$yestoday' ,'$lastweek')
						 ${where//0x20/ }
					 ) t;"
			elif [ "$type" == "REALTIME" ];then
				#上一个小时时间戳
				lasthour=$(($dtime - 3600))
				#昨天的这个小时
				yestodayhour=$(($dtime - 3600*24))
				#上周的这个小时
				lastweekhour=$(($dtime - 7*3600*24))

				#实时内容
				sql="select concat(
					 '<td>$dhour</td>','<td>$user</td>','<td>$app_name</td>',
					 '<td>$table_name</td>','<td>$metric_name</td>','<td>$metric_desc</td>',
					 concat('<td>',thishour,'</td>'),concat('<td>',lasthour,'</td>'),concat('<td>',lastdayhour,'</td>'),concat('<td>',lastweekhour,'</td>'),'<td>$threshold</td>',
					 case when lasthour=0 then '<td></td>' else
					 concat(case when round((thishour-lasthour)/lasthour*100,2)>$threshold or round((thishour-lasthour)/lasthour*100,2)<-$threshold then '<td style="background-color:red">' else '<td>' end,round((thishour-lasthour)/lasthour*100,2),'</td>') end,
					 case when lastdayhour=0 then '<td></td>' else
					 concat(case when round((thishour-lastdayhour)/lastdayhour*100,2)>$threshold or round((thishour-lastdayhour)/lastdayhour*100,2)<-$threshold then '<td style="background-color:red">' else '<td>' end,round((thishour-lastdayhour)/lastdayhour*100,2),'</td>') end,
					 case when lastweekhour=0 then '<td></td>' else
					 concat(case when round((thishour-lastweekhour)/lastweekhour*100,2)>$threshold or round((thishour-lastweekhour)/lastweekhour*100,2)<-$threshold then '<td style="background-color:red">' else '<td>' end,round((thishour-lastweekhour)/lastweekhour*100,2),'</td>') end)
					 from(
						 select
						 sum(case when time_stamp>=$dtime and time_stamp<($dtime+3600) then $metric_name else 0 end) as thishour,
						 sum(case when time_stamp>=$lasthour and time_stamp<$dtime then $metric_name else 0 end) as lasthour,
						 sum(case when time_stamp>=$yestodayhour and time_stamp<($yestodayhour+3600) then $metric_name else 0 end) as lastdayhour,
						 sum(case when time_stamp>=$lastweekhour and time_stamp<($lastweekhour+3600) then $metric_name else 0 end) as lastweekhour
						 from $table_name
						 where $col_dt in( '$dt','$yestoday','$lastweek')
						 and
						 (time_stamp>=$dtime and time_stamp<($dtime+3600) or
						  time_stamp>=$lasthour and time_stamp<$dtime or
						  time_stamp>=$yestodayhour and time_stamp<($yestodayhour+3600) or
						  time_stamp>=$lastweekhour and time_stamp<($lastweekhour+3600)
						  ) ${where//0x20/ }
					 ) t;"
			fi
			echo $sql
			#调用户mysql端，执行sql
			result=`mysql -u$dbuser -p$password -h$host -D$db -N -e"$sql"`

			#语句有异常
			if [[ "$?" != "0" ]];then
				result="<td>$dt</td><td>$user</td><td>$app_name</td><td>$table_name</td><td>$metric_name</td><td>$metric_desc</td><td></td><td></td><td></td><td>$threshold</td><td></td><td style="background-color:red">ERROR</td>"
			fi
			echo $result



#发微信功能开始-------------------------------------------
			#有信息长度限制
			#需要警报的信息
			warning=""
			if [ "$type" == "OFFLINE" ];then
				warning=`echo $result | awk -F '<tr>|</tr></table>' '{for(i=1;i<=NF;i++) {if($i~"background-color:red") print $i}}' | awk -F '<td>|</td>|<td style=background-color:red>' '{print "\n日期:"$2,"\n负责人:"$4,"\n应用:"$6,"\n表名:"$8,"\n列名:"$10,"\n指标描述:"$12,"\n昨日数据:"$14,"\n前一天数据:"$16,"\n上周数据:"$18,"\n阈值:"$20,"\n日环比增长:"$22"\n周同比增长:"$24,"\n昨日hive数据:"$26,"\n是否相同:"$28}'`
			elif [ "$type" == "REALTIME" ];then
				warning=`echo $result | awk -F '<tr>|</tr></table>' '{for(i=1;i<=NF;i++) {if($i~"background-color:red") print $i}}' | awk -F '<td>|</td>|<td style=background-color:red>' '{print "\n日期:"$2,"\n负责人:"$4,"\n应用:"$6,"\n表名:"$8,"\n列名:"$10,"\n指标描述:"$12,"\n本小时数据:"$14,"\n上一小时数据:"$16,"\n昨天本小时数据:"$18,"\n上周本小时数据:"$20,"\n阈值:"$22"\n时环比增长:"$24,"\n日同比增长:"$26,"\n周同比增长:"$28}'`
			fi
			#发送微信
			echo "-----------------------------------------------"
			agentId=""
			tag=""
			corpID="wwwwwwwwwwwwwwwwww"
			secret=""
			if [ "$warning" != "" ];then
				#加入当前时间
				if [ "$type" == "OFFLINE" ];then
					warning="离线`date '+%Y-%m-%d %H:%M:%S'`$warning"
					agentId=1111111
					secret="dedddddddddddddddddddddddddddddddd"
					tag=1
				elif [ "$type" == "REALTIME" ];then
					warning="实时`date '+%Y-%m-%d %H:%M:%S'`$warning"
					agentId=22222222
					secret="frrrrrrrrrrrrrrrrrrrrrrrffffffffffff"
					tag=3
				fi
				#非生产环境
				if [ "$is_production_envi" != "Y" ];then
				    tag=2
				fi
				weixin_rest_server=`cat $basepath/conf/datamonitor.property | grep WEIXIN_REST_SERVER | awk -F= '{print $2}'`
				#将警报的数据发送给微信
				curl -d "corpID=$corpID&secret=$secret&msgType=tag&toTag=$tag&agentId=$agentId&content=$warning" "$weixin_rest_server"
			fi
#发微信功能结束-------------------------------------------

			mailContent=$mailContent$result

			#本次查询结束
			break
		fi
	done
	mailContent=$mailContent'</tr>'
	echo `date +"%Y-%m-%d %H:%M:%S"`":Finish querying data..."
	echo "-----------------------------------------------"
done
mailContent=$mailContent'</table>'



#发送邮件
#生产环境
sendusers=""
if [ "$is_production_envi" == "Y" ];then
	#读取配置文件信息,读取发送邮件列表
	sendusers=`cat $basepath/conf/datamonitor.property | grep -v ^# | grep ^SENDUSERS | awk -F= '{print $2}'`
else
    sendusers=`cat $basepath/conf/datamonitor.property | grep -v ^# | grep ^TEST_SENDUSERS | awk -F= '{print $2}'`
fi
mailserver=http://mail.xxxxx.com/ddddd/api/mail/sender/
#默认主题离线
mailSubject="各应用数据校验(离线-`date -d "$dt" +%Y-%m-%d`)"

#实时主题替换
if [ "$type" == "REALTIME" ]; then
	mailSubject="各应用数据校验(实时-$dhour)"
elif [ "$type" == "OFFLINE" ]; then
	mailSubject="各应用数据校验(离线-`date -d "$dt" +%Y-%m-%d`)"
fi

echo `date +"%Y-%m-%d %H:%M:%S"`":Start sending email..."
echo "$mailContent"
IFS=$newifs
curl $mailserver -X POST -i -H "Content-Type:application/json" -d '{"recipients":['$sendusers'],"useTemplate":0,"mailSubject":"'$mailSubject'","mailContent":"'$mailContent'"}'
IFS=$oldifs

echo ""
echo `date +"%Y-%m-%d %H:%M:%S"`":Finish sending email..."
echo "-----------------------------------------------"
