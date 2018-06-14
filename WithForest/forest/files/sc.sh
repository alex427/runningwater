#!/usr/bin/env bash

#zhiguang


arrayWen=(20180301 20180302 20180303 20180304 20180305 20180306 20180307 20180308 20180309 20180310 20180311 20180312 20180313 20180314 20180315 20180316 20180317 20180318 20180319 20180320 20180321)


for day in ${arrayWen[@]};  
do  
    echo $day
    sh /home/bdwh/zhiguang/shafa/agg_shafa_content_overview.sh  $day &
    echo "waiting for 90s ....the next day......"
    sleep 90s
done


