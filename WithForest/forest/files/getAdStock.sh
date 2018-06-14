#!/bin/sh
. /etc/profile
. ~/.bash_profile


day=`date -d "1 days ago " "+%Y%m%d"`;
if [ $# -gt 0 ];then
      day=$1;
fi

tempp=`echo ${day:0:4}-${day:4}`
dayy=`echo ${tempp:0:7}-${tempp:7}`

BASE_DIR=/opt/adstat/dist/applist_uv
mkdir -p ${BASE_DIR}/sohuadstock/$day
mkdir -p ${BASE_DIR}/sohuadstock_a/$day

sql="
insert overwrite local directory '${BASE_DIR}/sohuadstock/$day/stockdata'
select dt,plat,sum(pv) pv,sum(stock) stock,sum(arrive_pv) arrive_pv,sum(click) click 
from  kylin_group_cpc_occu where dt = '$day' group by dt,plat
"


#调用hive, 执行上面的sql
#/usr/lib/hive/bin/beeline -u "principal=xxx" --hiveconf mapreduce.job.queuename=datacenter -e "set hive.exec.reducers.max=60;" -e "${sql_appuv}" -e "${sql_wapuv}" -e "${sql_pcuv}" -e "${sql_tvuv}" -e "${sql_tvvv}"

/usr/lib/hive/bin/beeline -u "principal=xxx" --hiveconf mapreduce.job.queuename=datacenter -e "${sql}" 


#转储结果文件 
cat ${BASE_DIR}/sohuadstock/$day/*/* > ${BASE_DIR}/sohuadstock_a/$day/SOHU_AD_STOCK.txt

FILE_A=${BASE_DIR}/sohuadstock_a/$day/SOHU_AD_STOCK_${day}_01.data

sed -e 's/\x01/,/g' ${BASE_DIR}/sohuadstock_a/$day/SOHU_AD_STOCK.txt > ${FILE_A}

cat ${FILE_A} > ${BASE_DIR}/sohuadstock_a/$day/SOHU_AD_STOCK_${day}_01_01.data

echo "${BASE_DIR}/sohuadstock_a/$day/SOHU_AD_STOCK_${day}_01_01.data"

#scp数据结果到目标文件夹, 让下游使用. 下游一般采用sqlloader加载数据到Oracle中
ssh dwetl@101.101.101.101 "mkdir -p /home/dwetl/DATA/receive/$day"
scp  ${BASE_DIR}/sohuadstock_a/$day/SOHU_AD_STOCK_${day}_01_01.data   dwetl@101.101.101.101:/home/dwetl/DATA/receive/$day/
