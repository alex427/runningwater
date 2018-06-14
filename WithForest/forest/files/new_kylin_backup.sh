#!/bin/sh

. /etc/profile
. ~/.bash_profile

echo "i am under scheduling....."
#get current date
txdate=`date +%Y%m%d`
#txdate=20170929

#create the log
touch /home/bd-warehouse/zhiguang/kylin_backup_$txdate\.log

#do meata backup
source $KYLIN_HOME/bin/metastore.sh backup >> /home/bd-warehouse/zhiguang/kylin_backup_$txdate\.log

#sleep , waiting for log ready
sleep 60s

#get the backup path
line=`sed -n 200000p /home/bd-warehouse/zhiguang/kylin_backup_$txdate\.log`
tmp=`echo $line | awk '{split($0,a,"Store ");print a[2]}'`
bkpath=`echo $tmp | awk '{split($0,a,")");print a[1]}'`
echo $bkpath


filename=`echo $bkpath | awk '{split($0,a,"meta_backups/");print a[2]}'`
echo $filename


#compress
nohup tar czf /opt/bd-warehouse/apache-kylin-1.6.0-bin/meta_backups/$filename.tar.gz  $bkpath

#send to 49.181
scp /opt/bd-warehouse/apache-kylin-1.6.0-bin/meta_backups/$filename.tar.gz  bd-warehouse@10.16.49.181:/home/bd-warehouse/kylin_39_181

#delete 
#rm -f /opt/bd-warehouse/apache-kylin-1.6.0-bin/meta_backups/$filename.tar.gz
#rm -rf /opt/bd-warehouse/apache-kylin-1.6.0-bin/meta_backups/$filename
