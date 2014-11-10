#!bin/bash
#==========================================
#  author WangJun
#  email wangjuntytl@163.com
#  date 2014-11-06
#====================================
#  desc requerie params
#====================================
#脚本输出根目录
TARGET_DIR="/Users/wangjun/script/$PROJECT_NAME-$PORT"
JETTY_HOME_DIR="/Users/wangjun/software/server/jetty-distribution-9.2.2.v20140723"
#web配置目录
CONF_HOME="${TARGET_DIR}"
#web运行log文件位置
LOG_HOME="/Users/wangjun/logs/$PROJECT_NAME-$PORT"
#代码下载构建目录
BUILD_HOME="${TARGET_DIR}/build"
WEBAPP_HOME="${TARGET_DIR}/webapp"
#部署脚本目录
DEPLOY_HOME="${TARGET_DIR}/deploy"
#控制webapp(启动，重新启动，停止)
CONTROL_HOME="${TARGET_DIR}/control"


