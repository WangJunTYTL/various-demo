#/bin/bash
#==================
#author WangJun
#email wangjuntytl@163.com
#date 2014-12-5
#=======================
#desc program 入口
#======================

LOG_DIR=./log
LOG_FILE_NAME=log.txt

[ ! -d $LOG_DIR ] && mkdir $LOG_DIR && echo "mkdir ${LOG_DIR} suc..."

sh ./batchInsertToMySql.sh >> ${LOG_DIR}/${LOG_FILE_NAME}

echo 'app has run... log information in '${LOG_DIR}/${LOG_FILE_NAME}