#!/usr/bin/env bash
#nohup sh /opt/dddd/job/ttr/bin/runDateRange.sh 20171001 20171007 'sh /opt/dddd/job/ttr/comm/bin/run_usermodel_4_busi_line.sh -busiline=news -type=all -day=' 2>/dev/null &

cur_dir=`cd $(dirname $0) && pwd`

day=$1
end=$2
cmd=$3

echo $day
echo $end

while [ ${day} -le ${end} ];do
	${cmd}${day} >> $cur_dir/runDateRange_$day.log
	echo -e ${cmd}${day}' finished \n\n' >> $cur_dir/runDateRange.log
	day=`date -d "$day +1 day" "+%Y%m%d"`
done