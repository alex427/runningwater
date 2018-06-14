#!/usr/bin/env bash

#by zhiguang

ttr_dir=`cd $(dirname $0)/../../.. && pwd`
usage()
{
    cat << EOF
    Usage: [script options] [options] [day] [sourcepath]
    Options:
           [-h]: print this message and script help
           [-day]: [20171204] date of log
           [-sourcepath]: common.sh filepath
EOF
    exit $1
}

while test $# -gt 0; do
	case "$1" in
	-*=*) optarg=`echo "$1" | sed 's/[-_a-zA-Z0-9]*=//'` ;;
	*) optarg= ;;
	esac

	case "$1" in
	-h)
	   usage 1 1>&2
		;;
	-day=*)
		day=$optarg
		;;
	-sourcepath=*)
		sourcepath=$optarg
		;;
	esac
	shift
done

# check params
if [ ! -n "$sourcepath" ];then
    if [ -f "$ttr_dir/bin/common.sh" ]; then
        source $ttr_dir/bin/common.sh
    else
        usage 1 1>&2
    fi
else
    if [ -f "$sourcepath" ]; then
        source $sourcepath
    else
        usage 1 1>&2
    fi
fi

if [ ! -n "$day" ];then
    day=`date -d "2 days ago " "+%Y%m%d"`
fi

echo $0 $1
echo $day
