#!/usr/bin/env bash
set -e
source ~/.bash_profile

PROJECT_NAME=ttr

AGENT_USER="username"
AGETN_IP="ip address"

PERSON_DIR=zhiguang

GITLAB_HOME=/opt/datacenter/git

PROJECT_PATH=/opt/datacenter/job/$PERSON_DIR
BACKUP_PATH=/opt/datacenter/job_backup/$PERSON_DIR



#1.pull the latest version from gitlab dev branch
cd $GITLAB_HOME/${PROJECT_NAME}

echo `pwd`

#pull code and set no reminder
git config --global core.mergeoptions --no-edit
git pull origin dev

#maven do pack
mvn clean package



#2.Move remote old version files from #project_name to #project_name_#current_timestamp
ssh ${AGENT_USER}@${AGETN_IP} mv ${PROJECT_PATH}/${PROJECT_NAME} ${BACKUP_PATH}/${PROJECT_NAME}/${PROJECT_NAME}_`date +%Y%m%d%H%M%S`

#3.rsync
rsync -av --exclude .git --exclude .idea --exclude 'src' --exclude 'archive-tmp' --exclude 'classes' --exclude 'generated-sources' --exclude 'maven-archiver' --exclude 'surefire' --exclude '
test-classes' ${GITLAB_HOME}/${PROJECT_NAME} ${AGENT_USER}@${AGETN_IP}:/${PROJECT_PATH}

#
