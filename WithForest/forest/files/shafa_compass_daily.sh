#!/usr/bin/env bash
source /etc/profile
source ~/.bash_profile

#by zhiguang

#hive settings
export HIVE_CONF="    "
DB_MYSQL=('   ')

#day
if [[ 1 -ne $# ]]; then
   day=`date -d "$1 days ago " "+%Y%m%d"`
else
   day=$1
fi


#db operator
initMysql(){
    db_info=($1)
    sql=$2
    length=${#db_info[@]}
    if [ ! $length -eq 5 ];then
        echo 'ERROR: e.g. initMysql "\${db_info[*]}" "select * from dual"'
        exit 1
    fi
    echo $sql
    mysql -u${db_info[3]} -p${db_info[4]} -h${db_info[0]} -P${db_info[1]} ${db_info[2]} -e "$sql"
}


#record the time cost
starttime=$(date +%s)

echo -e "  \n $day is running on......\n"


sql_a="
set mapreduce.map.memory.mb=4096;
set mapreduce.reduce.memory.mb=4096;
insert  overwrite  table  stage_shafa_content_main_detail    
partition (pdt='$day')   
select    ;
"


sql_b="
set mapreduce.map.memory.mb=4096;
set mapreduce.reduce.memory.mb=4096;
select 
a.dt, 
unix_timestamp( concat(a.dt, '120000'), 'yyyyMMddHHmmss')  as  time_stamp,
b.total_vv, sum(a.vv) as sub_vv, round((sum(a.vv)/b.total_vv),2) as cus_rate
from (
select r.dt, f.cid, r.vv
from stage_shafa_content_fulllist  f
left join
( select pdt as dt,objid, sum(vv) as vv from stage_shafa_content_main_detail d  where d.pdt='$day' group by pdt,objid  ) r
 on r.objid=f.cid
where f.dt='$day'
) a

left join ( select d.pdt as dt, sum(vv) as total_vv from stage_shafa_content_main_detail d  where d.pdt='$day' group by pdt ) b
on a.dt=b.dt
where a.vv is not null
group  by  a.dt, unix_timestamp( concat(a.dt, '120000'), 'yyyyMMddHHmmss'), b.total_vv;
"

$HIVE_CONF -e "$sql_a" && $HIVE_CONF -e "$sql_b" > /home/bdwh/zhiguang/shafa/dailyrate.f

sleep 5s

#data parse
dtd=`awk 'NR=3{print $2}' /home/bdwh/zhiguang/shafa/dailyrate.f | sed -n '4p'`
dts=`awk 'NR=3{print $4}' /home/bdwh/zhiguang/shafa/dailyrate.f | sed -n '4p'`
tvv=`awk 'NR=3{print $6}' /home/bdwh/zhiguang/shafa/dailyrate.f | sed -n '4p'`
svv=`awk 'NR=3{print $8}' /home/bdwh/zhiguang/shafa/dailyrate.f | sed -n '4p'`

echo  -e "$dtd \n"
echo  -e "$dts \n"
echo  -e "$tvv \n"
echo  -e "$svv \n"

sleep 5s

initMysql "${DB_MYSQL[*]}" "delete from compass_sofa_daily  where dt='$day'  "
initMysql "${DB_MYSQL[*]}" "insert into compass_sofa_daily values (null,$dtd,$dts,$svv,$tvv) "

echo -e "ok \n"
