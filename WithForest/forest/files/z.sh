#!/usr/bin/env bash
source /etc/profile
source ~/.bash_profile

#by zhiguang

#hive settings
export HIVE_CONF="xxxxx"

#record the time cost
starttime=$(date +%s)


if [[ 1 -ne $# ]]; then
   day=`date -d "$1 days ago " "+%Y%m%d"`
else
   day=$1
fi

echo -e "-----$day is running on ------\n"

#record the time cost
starttime=$(date +%s)

sql_2="
insert overwrite table agg_topn
partition(dt = '$day')
select c.busi_type, c.terminal_type, c.os_type, c.plat_type, c.video_type, c.user_type,c.vid, c.title, c.playlist,c.pub_time,
c.vv, c.realvv,c.duration_play, c.duration_video, c.fuv, c.realfuv
from (
select b.busi_type, b.terminal_type, b.os_type, b.plat_type, b.video_type, b.user_type,b.vid, b.title, b.playlist,b.pub_time,
b.vv, b.realvv,b.duration_play, b.duration_video, b.fuv, b.realfuv,
row_number() over (distribute by b.compare_mark sort by b.compare_mark asc , b.fuv desc, b.compare_mark is not null )  as rank_num
from (

select  concat (a.terminal_type,a.video_type, a.user_type) as compare_mark,
a.busi_type, a.terminal_type, a.os_type, a.plat_type, a.video_type, a.user_type,a.vid, a.title, a.playlist_id as playlist,a.pub_time,
a.vv, a.realvv,a.duration_play, a.duration_video, a.fuv, a.realfuv
from stage_topn_all a
where a.dt='$day'

) b
) c  where c.rank_num <1201 ;"

$HIVE_CONF -e "$sql_2"


echo "$day  is  ok "
sleep 5s


endtime=$(date +%s)
echo -e '\n hive mission accomplished.....  \n'
echo " total time cost: $(( $endtime - $starttime )) seconds"

