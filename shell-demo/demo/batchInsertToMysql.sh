#!/usr/bin/env bash
#！/bin/bash
#==================================
#	author WangJun
#	email wangjuntytl@163.com
# 	date 2014-12-05
#=============================
#	desc 每天凌晨5点解析预约log数据job
#=======================


echo '------------------------------------------------'
date
echo "------------------------------------------------"
HOSTNAME="127.0.0.1"                                          
PORT="3306"
USERNAME="root"
PASSWORD=""

DBNAME="test"                                             
TABLENAME="t_order_report"                                  

MYSQL_CMD="mysql -h${HOSTNAME}  -P${PORT}  -u${USERNAME} test"
echo "数据库连接信息-->"${MYSQL_CMD}

function valid(){
	[ $1 != 0 ] && echo $2 && exit 1
}
${MYSQL_CMD}<< EOF
CREATE TABLE IF NOT EXISTS t_order_report ( id int not null auto_increment,booking_id varchar(100) default null,app_key varchar(10) default null,order_time datetime default null,primary key(id));
EOF


valid $? "不能与数据库建立连接！！！ "

# date  +"%Y-%m-%d" -d  "-1 days" 昨天日期，在mac上面不适应
TODAY=`date +%Y-%m-%d`
LOG_FILE="statistics-log-file.log.${TODAY}.log"
[ ! -f ${LOG_FILE} ] && echo "没有发现今天的日志文件：${LOG_FILE}" && exit 1

echo ${TODAY}' 日志信息:'
wc -l ${LOG_FILE}

awk -F '|' '{printf ("insert into t_order_report ( `booking_id`,`app_key`,`order_time`) values ('\'''%s''\'','\'''%s''\'','\'''%s''\'');",$2,$4,$6) }' ${LOG_FILE}  |  ${MYSQL_CMD}

echo '解析完毕...'

exit 0

