#!/usr/bin/env bash

source /etc/profile
source ~/.bash_profile

#arrayWen=(20171101)
#arrayWen=(20171017 20171018 20171019 20171020 20171021 20171022 20171023 20171024 20171025 20171026 20171027 20171028 20171029 20171030 20171031)
#arrayWen=(20171102 20171103 20171104 20171105 20171106 20171107 20171108 20171109 20171110 20171111 20171112 20171113 20171114 20171115 20171116 20171117 20171118 20171120 20171121 20171122 20171123 20171124 20171125 20171126 20171127 20171128 20171129 20171130)
#arrayWen=(20171201 20171202 20171203 20171204 20171205 20171206 20171207 20171208 20171209)
arrayWen=(20171220 20171221 20171222 20171223 20171224 20171225 20171226 20171227 20171228 20171229 20171230 20171231 20180101 20180102 20180104)

#hive settingsexport 
HIVE_CONF="xxx"

for day in ${arrayWen[@]};  
do  
    echo $day

#hadoop fs -ls -h hdfs://xxxxx/hive/warehouse/agg/agg_newsapp_channelid_overview/dt=$day
sql="load data inpath 'hdfs://xxxxx/hive/warehouse/agg/agg_newsapp_channelid_overview/dt=$day' into table agg_newsapp_channelid_overview partition(dt=$day)"

$HIVE_CONF -e "$sql"  

sleep 5s

done
