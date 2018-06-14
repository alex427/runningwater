#!/usr/bin/env bash
source /etc/profile
source ~/.bash_profile

#by zhiguang

#hive settings


#定义扫描函数
function do_scan(){
	local tday=$1
	
	local fnum=`hadoop fs -ls hdfs://xxxx/hive/warehouse/dw/dw_newsapp_behavior/dt=$tday/_SUCCESS 2>/dev/null| wc -l`
	local gnum=`hadoop fs -ls hdfs://xxxx/hive/warehouse/comm/comm_user_active_daily/dt=$tday/busi_type=news-app/_SUCCESS 2>/dev/null| wc -l`
	
	if [[ 1 -eq $fnum && 1 -eq $gnum ]] ;then            
        echo 1 
    else
        echo 0
    fi	
}

function do_check(){
	local tday=$1
	
	local tnum=`hadoop fs -ls hdfs://xxxx/hive/warehouse/agg/agg_newsapp_channelid_overview/dt=$tday 2>/dev/null| wc -l`
	local ttnum=`hadoop fs -ls hdfs://xxxx/hive/warehouse/agg/agg_newsapp_topn_overview/dt=$tday 2>/dev/null| wc -l`
    
	if [[ 0 -eq $tnum && 0 -eq $ttnum ]] ;then            
        echo 0 
    else
        echo 1
    fi	
	
}


#定义日期数组
#arrayWen=(20171206 20171207 20171208 20171209 20171210 20171211 20171212 20171213 20171214 20171215 20171216 20171217 20171218 20171219 20171220 20171221 20171222 20171223 20171224 20171225 20171226 20171227 20171228 20171229 20171230 20171231 20180101 20180102 20180103 20180104 20180105 20180106 20180107 20180108 20180109 20180110 20180111 20180112 20180113 20180114 20180115 20180116 20180117 20180118 20180119 20180120 20180121 20180122 20180123 20180124)

#arrayWen=(20180111 20180112 20180113 20180114 20180115 20180116 20180117 20180118 20180119 20180120 20180121 20180122 20180123 20180124 20180125 20180126 20180127)

arrayWen=(20180125 20180126 20180127)

turnn=2	

#进入循环
while true 
do
#遍历日期数组 
	for day in ${arrayWen[@]};  
	do  
	    echo -e "$day is to be scanned ... \n"
	    flag=`do_scan $day`
		if [[ 1 -eq $flag ]] ; then 
		  	#上游检查OK
			echo -e "$day is ready.....\n"	
			 
		    #下游检查
			ff=`do_check $day`
			echo $ff 
			if [ 0 = $ff ] 
			then
				#条件满足时, 启动两个子进程 
				sh /home/bdwh/zhiguang/newsappchn/agg_newsapp_channel.sh $day &
				sh /home/bdwh/zhiguang/newsapptopn/agg_newsapp_topn.sh $day &
				
				#5分钟后,开始下一天
                echo -e "waiting for 5 minuts .....................................................................\n"
                sleep 300s 
			else 
				echo -e "$day is already written ... \n"
			fi			
	  else  			
		 echo -e "$day is not ready ....\n"
	  fi 	  	  	   
	done
	
		  
	#下一轮循环	 
	echo -e "for next turn, waiting for 15 minuts .....................................................................\n"
	sleep 900s 	
	echo "the $turnn turn is starting......."
    let turnn++
	

	#loop end check	
	lastday='20180127'
	endd=`do_check $lastday `
	if [[ $endd -gt 0  ]] ; then

	   echo "days run out, over...."
	   break;

	fi
	
done 



