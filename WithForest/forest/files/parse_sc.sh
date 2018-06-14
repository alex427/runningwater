#!/usr/bin/env bash
source /etc/profile
source ~/.bash_profile

#hive settings
export HIVE_CONF="aaa"

#date ready
day=`date +"%Y%m%d"`

#get data ftom api
curl http://10.11.159.159:8088/api/cdn/video/fulllist |sed 's/,/\n/g' > /home/bdwh/zhiguang/shafa/$day.txt

sleep 5s

#put to hdfs
hadoop fs -rm -r hdfs://dc3/user/bdwh/hive/warehouse/stage/stage_shafa_content_fulllist/$day.txt
hadoop fs -put /home/bdwh/zhiguang/shafa/$day.txt  hdfs://dc3/user/bdwh/hive/warehouse/stage/stage_shafa_content_fulllist/$day.txt

sleep 5s

#load into hive
sql_d="alter table stage_shafa_content_fulllist drop partition (dt='$day');"
$HIVE_CONF -e "$sql_d"

sleep 5s

sql="load data inpath 'hdfs://dc3/user/bdwh/hive/warehouse/stage/stage_shafa_content_fulllist/$day.txt' overwrite into table stage_shafa_content_fulllist partition (dt='$day') ;"
$HIVE_CONF -e "$sql"

sleep 5s
hadoop fs -rm -r hdfs://dc3/user/bdwh/hive/warehouse/stage/stage_shafa_content_fulllist/$day.txt

echo "ok"

