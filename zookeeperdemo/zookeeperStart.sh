#!/bin/sh
#=================
#author WangJun
#email wangjuntytl@163.com
#data 2014-11-11
#===========================
#desc 在同一台机器启动多个zookeeper服务
#===================================
CMD=$1
[[ x${CMD} != "xstart" ]] && [[ x${CMD} != "xstop" ]] && echo " 参数错误：USAGE:sh $0 start | stop" && exit 1

ZOOKEEPER_HOME="/Users/wangjun/Downloads/zookeeper-3.5.0-alpha"
USER_DIR=${PWD}
echo "当前目录：${USER_DIR}"
cd ${ZOOKEEPER_HOME}
sh ${ZOOKEEPER_HOME}/bin/zkServer.sh ${CMD} ${USER_DIR}/server1.cfg
sh ${ZOOKEEPER_HOME}/bin/zkServer.sh ${CMD} ${USER_DIR}/server2.cfg
sh ${ZOOKEEPER_HOME}/bin/zkServer.sh ${CMD} ${USER_DIR}/server3.cfg

if [ ${CMD} == "start" ]; then
    echo "启动完毕，各个服务器状态>>>>>>"
    sh ${ZOOKEEPER_HOME}/bin/zkServer.sh status ${USER_DIR}/server1.cfg
    sh ${ZOOKEEPER_HOME}/bin/zkServer.sh status ${USER_DIR}/server2.cfg
    sh ${ZOOKEEPER_HOME}/bin/zkServer.sh status ${USER_DIR}/server3.cfg
fi
#cd ${USER_DIR}

exit 0

