#!/usr/bin/env bash
set -e
#1.处理panther找不到bash_profile问题
function initBashProfile(){
    user_home=`cd ~ && pwd`
    if [ -f $user_home'/.bash_profile' ];then
        source ~/.bash_profile
    fi
}
initBashProfile

#2.处理panther找不到hadoop执行文件
function initHadoop(){
    if [ -f '/opt/cloudera3/parcels/CDH/bin/hadoop' ];then
        export HADOOP_CONN='/xxxxxx/hadoop'
    else
        export HADOOP_CONN='/yyyyy/hadoop'
    fi
}
initHadoop
############# env parameters #################
# 1. users
 

# 2. hdfs path
#   2.1 common path
 
#   2.2 hive path
 

#   2.3 log path
 

# 3.mr settings
 

#4.hive settings
 
#5.impala settings
 
#6.database conn info
#(ip port dbname user password)
export DB_ORACLE_BIGDATA=('111.1111.2222.22222' '1521' 'dbdbdbdb' 'useruser' 'mmmmmm')

#7. common functions
#7.1 初始化目录
function initPath(){
    if [ ! -x "$1" ]; then
        mkdir -p "$1"
        echo 'initPath:mkdir -p '$1
    fi
}

#7.2log functions
function log(){
  echo "`date +"%Y-%m-%d %H:%M:%S"` $0 [ttr] $1: $2"
}

function info(){
    log "INFO" "$*"
}

function error(){
    log "ERROR" "$*" && exit 1
}

#7.3 print files in list
# print all files in $1 ends with $3,delimited with $2
# example: list jars : jar
function list(){
    [ ! -d $1 ] && return
    for f in `ls -d $1/*$3`;do
       [ -f ${f} ] && p=${f}$2${p}
    done
    echo ${p}
}


#7.4 检查前置mr/spark数据是否生成
#$1 带检查hdfs路径 $2校验路径下有多少个SUCCESS文件
#e.g. checkFileExisted '/user/useruser/hive/warehouse/dw/dw_tv_play/dt=20171122' 1 [filename]
#     checkFileExisted '/user/useruser/hive/warehouse/dw/dw_tv_play/dt=20171122/*' 24 [filename]
function checkFileExisted(){
    if [ $# -lt 2 ];then
        echo "ERROR: example checkFileExisted '/user/useruser/hive/warehouse/dw/dw_tv_play/dt=20171122' 1 \n or /user/useruser/hive/warehouse/dw/dw_tv_play/dt=20171122/* 24'"
        exit 1
    fi

    #'/user/useruser/hive/warehouse/dw/dw_tv_play/dt=20171122'
    ckpath=$1
    cknum=$2
    file_name='?SUCCESS'
    if [ $# -gt 2 ] ; then
        file_name=$3
    fi
    count=0
    while [ 1 ]
    do
        if [ '/opt/cloudera3/parcels/CDH/bin/hadoop' = $HADOOP_CONN ];then
            fileCount=`HADOOP_PROXY_USER=useruser hadoop fs -ls $ckpath/$file_name 2>/dev/null| wc -l`
            echo 'HADOOP_PROXY_USER=useruser hadoop fs -ls '$ckpath'/'$file_name' 2>/dev/null| wc -l'
        else
            fileCount=`hadoop fs -ls $ckpath/$file_name 2>/dev/null| wc -l`
            echo 'hadoop fs -ls '$ckpath'/'$file_name' 2>/dev/null| wc -l'
        fi

        if [ $fileCount -lt $cknum ] ;then
              echo 'Check '$count' times('$fileCount'),'$ckpath' not existed,sleep 5  minutes'
              sleep 300;
        else
           break;
        fi
        ((count+=1))
    done
    echo "Check $ckpath finished ! Spend $count*5 minutes"
}

#7.5 impala表刷新(支持按空格配置N个表名)
#e.g. refreshImpalaTable agg_user_retention [...]
function refreshImpalaTable(){
    #${IMPALA_CONN} -q "invalidate metadata $1" 2>&1
    #${IMPALA_CONN} -e "invalidate metadata $1 ;"
    #echo 'warning:unimplement refresh impalaTable'
    sql=""
    for i in $@; do
        sql=${sql}'invalidate metadata '${i}';'
    done
    echo 'refreshImpalaTable:'$sql
    ${IMPALA_CONN} -e "$sql"
}

#7.6 执行mysql sql语句
#$1 数据库连接数组 $2 待执行sql
# e.g. execSql4Mysql "${DB_MYSQL_HUE[*]}" "select * from dual"
function execSql4Mysql(){
    #数据库连接数组 (ip port dbname user password)
    db_info=($1)
    #待执行sql
    sql=$2
    length=${#db_info[@]}
    if [ ! $length -eq 5 ];then
        echo -e 'Param[db_info] format error! \n e.g. execSql4Mysql "\${db_info[*]}" "select * from dual"'
        exit 1
    fi
    mysql -u${db_info[3]} -p${db_info[4]} -h${db_info[0]} -P${db_info[1]} ${db_info[2]} -v -e "$sql"
}

#7.7 按hive table执行sqoop导出至mysql
# e.g. sqoopExportByHive4Mysql "${db_info[*]}" "agg_user_retention" "agg_user_retention" "terminal_type,..,os_type" ["1"  ("dt,busi_type" "20171001,tv-app")]
# $1 db_info $2 mtable $3 htable $4 columns [$5 mapnums $6 partitions]
function sqoopExportByHive4Mysql(){
    usage='\n e.g.sqoopExportByHive4Mysql "${db_info[*]}" "agg_user_retention" "agg_user_retention" "terminal_type,..,os_type" ["1"  ("dt,busi_type" "20171001,tv-app")]'
    if [ $# -lt 4 ];then
        echo -e 'Params must greater than 4 !'$usage
        exit 1
    fi
    #1.required params
    db_info=($1)
    dbinfo_length=${#db_info[@]}
    if [ ! $dbinfo_length -eq 5 ];then
        echo -e 'Param[db_info] format error!'$usage
        exit 1
    fi
    mtable=$2
    htable=$3
    columns=$4

    #2.optional params
    maptasks=1
    tmp_partition_option=""
    if [ $# -gt 4 ] ; then
        maptasks=$5
    fi
    if [ $# -gt 5 ] ;then
        partitions=($6)
        tmp_partition_option='--hcatalog-partition-keys '${partitions[0]}' --hcatalog-partition-values '${partitions[1]}
    fi
    echo "sqoop export -D mapreduce.job.queuename=myqueue -jt local -m $maptasks --connect 'jdbc:mysql://'${db_info[0]}':'${db_info[1]}'/'${db_info[2]}'?useUnicode=true&characterEncoding=utf-8' --username ${db_info[3]} --password ${db_info[4]} --hcatalog-database useruser --table $mtable --hcatalog-table $htable --columns $columns $tmp_partition_option"
    sqoop_log_path=$ttr_LOG_PATH'/sqoop'
    initPath $sqoop_log_path
    cd $sqoop_log_path && sqoop export -D mapreduce.job.queuename=myqueue -jt local -m $maptasks --connect 'jdbc:mysql://'${db_info[0]}':'${db_info[1]}'/'${db_info[2]}'?useUnicode=true&characterEncoding=utf-8' --username ${db_info[3]} --password ${db_info[4]} --hcatalog-database useruser --table $mtable --hcatalog-table $htable --columns $columns $tmp_partition_option
}

#7.8 按hdfs文件执行sqoop导出至mysql
#e.g. sqoopExportByHdfs4Mysql "${db_info[*]}" "compass_video_retain" "hdfs://xxxxx/user/useruser/hive/warehouse/sqoop/compass_video_retain" "terminal_type,..,os_type" [1]
# $1 db_info $2 mtable $3 htable $4 columns [$5 mapnums]
function sqoopExportByHdfs4Mysql(){
    usage='\n e.g. sqoopExportByHdfs4Mysql "${db_info[*]}" "compass_video_retain" "hdfs://xxxxx/user/useruser/hive/warehouse/sqoop/compass_video_retain" "terminal_type,..,os_type" [1]'
    if [ $# -lt 4 ];then
        echo -e 'Params must greater than 4 !'$usage
        exit 1
    fi
    #1.required params
    db_info=($1)
    dbinfo_length=${#db_info[@]}
    if [ ! $dbinfo_length -eq 5 ];then
        echo -e 'Param[db_info] format error! '$usage
        exit 1
    fi
    mtable=$2
    hdfs=$3
    columns=$4

    #2.optional params
    maptasks=1
    if [ $# -gt 4 ] ; then
        maptasks=$5
    fi
    echo "sqoop export -D mapreduce.job.queuename=myqueue -jt local -m $maptasks --connect 'jdbc:mysql://'${db_info[0]}':'${db_info[1]}'/'${db_info[2]}'?useUnicode=true&characterEncoding=utf-8' --username ${db_info[3]} --password ${db_info[4]} --table $mtable --export-dir $hdfs --input-null-non-string '\\N' --input-null-string '\\N' --input-fields-terminated-by '\001' --columns $columns"
    sqoop_log_path=$ttr_LOG_PATH'/sqoop'
    initPath $sqoop_log_path
    cd $sqoop_log_path && sqoop export -D mapreduce.job.queuename=myqueue -jt local -m $maptasks --connect 'jdbc:mysql://'${db_info[0]}':'${db_info[1]}'/'${db_info[2]}'?useUnicode=true&characterEncoding=utf-8' --username ${db_info[3]} --password ${db_info[4]} --table $mtable --export-dir $hdfs --input-null-non-string '\\N' --input-null-string '\\N' --input-fields-terminated-by '\001' --columns $columns
}

#7.9 将本地文件上传至HDFS
function putLocalFile2Hdfs(){
    usage="\n e.g. putLocalFile2Hdfs  '/opt/myqueue/job/yt/ttr/comm/bin/_SUCCESS' '/user/useruser/hive/warehouse/check'"
    if [ $# -lt 2 ];then
        echo -e 'Params must greater than 2 !'$usage
        exit 1
    fi
    local_file=$1
    dest_hdfs_dir=$2
    if [ '/opt/cloudera3/parcels/CDH/bin/hadoop' = $HADOOP_CONN ];then
        echo 'HADOOP_PROXY_USER=useruser '$HADOOP_CONN' fs -copyFromLocal -f '$local_file' '$dest_hdfs_dir
        HADOOP_PROXY_USER=useruser $HADOOP_CONN fs -copyFromLocal -f $local_file $dest_hdfs_dir
    else
        echo $HADOOP_CONN' fs -copyFromLocal -f '$local_file' '$dest_hdfs_dir
        $HADOOP_CONN fs -copyFromLocal -f $local_file $dest_hdfs_dir
    fi
}