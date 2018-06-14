#!/usr/bin/env bash
source /etc/profile
source ~/.bash_profile

#by zhiguang
#这个脚本还有问题

#hive settings


#定义扫描函数
function do_scan(){
	tday=$1
	
	fnum=`hadoop fs -ls hdfs://xxxxx/hive/warehouse/dw/dw_newsapp_behavior/dt=$tday/_SUCCESS 2>/dev/null| wc -l`
	gnum=`hadoop fs -ls hdfs://xxxxx/hive/warehouse/comm/comm_user_active_daily/dt=$tday/busi_type=news-app/_SUCCESS 2>/dev/null| wc -l`
	
	if [[ 1 -eq $fnum && 1 -eq $gnum ]] ;then            
        echo 1 
    else
        echo 0
    fi	
}

#定义日期数组
arrayWen=(20171008 20171009 20171010 20171011 20171012 20171013 20171014 20171015 20171016 20171017 20171018 20171019 20171020 20171021 20171022 20171023 20171024 20171025 20171026 20171027 20171028 20171029 20171030 20171031 20171101 20171102 20171103 20171104 20171105 20171106 20171107 20171108 20171111 20171112 20171113 20171114 20171115 20171116 20171117 20171118 20171121 20171122 20171123 20171124 20171125 20171126 20171127 20171128 20171109 20171110 20171119 20171120 20171129 20171130 20171201 20171202 20171203 20171204 20171205 20171206 20171207 20171208 20171209 20171210 20171211 20171212 20171213 20171214 20171215 20171216 20171217 20171218 20171219 20171220 20171221 20171222 20171223 20171224 20171225 20171226 20171227 20171228 20171229 20171230 20171231 20180101 20180102 20180104 20180105 20180106 20180107 20180108 20180109 20180110 20180111 20180112 20180113 20180114 20180115 20180116 20180117 20180118 20180119 20180120 20180121 20180122 20180123 20180124)
#arrayWen=(20171001 20171006 20171007 20180122 20180123 20180124)

index=0 

#进入循环
while true 
do

#下标数组
declare -a arrayMv
mark=0

#遍历日期数组 
	for day in ${arrayWen[@]};  
	do  
	  echo -e "$day is to be scanned ... \n"
	  flag=`do_scan $day`
		  if [[ 1 -eq $flag ]] ; then 
				#条件满足时, 启动两个子进程 
				sh /home/bdwh/zhiguang/newsappchn/agg_newsapp_channel.sh $day &
				sh /home/bdwh/zhiguang/newsapptopn/agg_newsapp_topn.sh $day &
				
				echo -e "$day is working now.....\n"					
				
				#将当前日期索引计入数组2
				arrayMv[$mark]=$index
				let mark++
				let index++
				echo "$mark ....... $index"
				
				#10分钟后,开始下一天
				echo "waiting for 20 minuts"
				sleep 900s 
		  else  
			let index++	
			echo -e "$day is not ready ....\n"
		  fi 	  	  	   
	done
	
	#第一轮结束, 从数组中清除已经在运行的日期
	echo "removing......................"
	for i in ${arrayMv[@]};  
	do  
		echo ${arrayWen[$i]}
	    unset arrayWen[$i]	
	done
	
	
	#打印剩余日期
	echo "remaining......................"
	for r in ${arrayWen[@]};  
	do
	   echo $r
	done
	
	#还原
	index=0
	unset arrayMv
	
	echo "second turn"
	sleep 300s
done 

