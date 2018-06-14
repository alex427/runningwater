#!/usr/bin/env bash

#by zhiguang


#定义栈

MAXTOP=100               #堆栈能存放元素的最大值  
  
TOP=$MAXTOP             #栈顶指针，初始值是$MAXTOP  


declare -a STACK        #全局数组STACK  

#push进栈操作，可同时将多个元素压入堆栈   
push(){  
	if [ -z "$1" ]      #无参数，返回  
	then  
		 return  
	fi  

	#for ((i=1; i<=$#; i++))  
	until [ $# -eq 0 ]   #until循环将push函数的所有参数都压入堆栈  
	do  
			let TOP=TOP-1    #栈顶指针减1  

			STACK[$TOP]=$1  
			shift            #脚本参数除$0外左移1位，$#参数总个数减1  
	done  

	return  
}  
#pop出栈操作，执行pop函数使栈顶元素出栈                     
pop(){  

	if [ "$TOP" -eq "$MAXTOP" ]   #若堆栈为空，返回  
	then  
	 return  
	fi  

	TEMP=${STACK[$TOP]}           #栈顶元素出栈  
	unset STACK[$TOP]           
	let TOP=TOP+1                 #栈顶指针加1  
	return  
}  

#显示当前堆栈内的元素，以及TOP指针和TEMP变量                         
status(){  
	echo "==========STACK=========="  
	for i in ${STACK[@]}                                  
	do  
		echo $i  
	done  
	echo "Stack Pointer=$TOP"  
	echo "Just popped \""$TEMP"\" off the stack"  
	echo ${#STACK[@]}
	echo "=========================="  
}  

getmsg(){  
	echo "==========STACK=========="  
	for i in ${STACK[@]}                                  
	do  
		printf "$i "  
	done  
	 
} 

#定义扫描函数
function do_scan(){
	tday=$1
	
	fnum=`cat /home/bdwh/zhiguang/mm.txt | wc -L`
	
    echo $fnum

}

#业务                       
work(){  
	
	for i in ${STACK[@]}                                  
	do  
		#判断
		echo -e "$i is to be scanned ... \n"
	    flag=`do_scan $i`
		  if [[ 1 -eq $flag ]] ; then 
				#条件满足时, 启动两个子进程 
				
				echo -e "$i is working now.....\n"
				pop
				
				#5分钟后,开始下一天
				echo "waiting for 5 minuts"
				sleep 30s 
		  else  			
			echo -e "$i is not ready ....\n"
		  fi 	 
		 
	done  
	 
	echo "${#STACK[@]} days are remained......"
	
}  


#定义日期数组
arrayWen=(20171123 20171124 20171125 20171126 20171127 20171128 20171129 20171130 20171201 20171202 20171203 20171204 20171205 20171206 20171207 20171208 20171209 20171210 20171211 20171212 20171213 20171214 20171215 20171216 20171217 20171218 20171219 20171220 20171221 20171222 20171223 20171224 20171225 20171226 20171227 20171228 20171229 20171230 20171231 20180101 20180102 20180103 20180104 20180105 20180106 20180107 20180108 20180109 20180110 20180111 20180112 20180113 20180114 20180115 20180116 20180117 20180118 20180119 20180120 20180121 20180122 20180123 20180124)

#arrayWen=(20180124 20180123 20180122 20171007 20171006 20171001)



#遍历日期数组, 加入栈中 
for day in ${arrayWen[@]};  
do
	push $day  
done


getmsg


while [ 0 -ne ${#STACK[@]} ] 
do 
	#执行
	#work 
	
	#sleep 30s
done


echo "==========================" 
echo ${#STACK[@]} 

#检查栈 
for ele in ${STACK[@]};  
do
	echo $ele  
done












