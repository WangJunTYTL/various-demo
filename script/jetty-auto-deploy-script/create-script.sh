#!bin/bash
#=======================================
#desc create web deploy script
#author WangJun
#date 2014-11-5
#=======================================

# env init 
source env.sh
echo "JETTY_HOME:${JETTY_HOME_DIR}" #jetty dir
echo "创建web配置文件在${CONF_HOME}" #web conf dir
echo "webapp运行log文件在LOG_HOME:${LOG_HOME}" #log dir
echo "webapp代码下载构建目录在${BUILD_HOME}" # build dir
echo "webapp_home目录在${WEBAPP_HOME}" #webapp dir
echo "控制webapp运行脚本在${CONTROL_HOME}"
echo "部署webapp脚本在${DEPLOY_HOME}"

[ ! -d $CONF_HOME ] &&  mkdir -p $CONF_HOME  &&  echo "mkdir ${CONF_HOME} success"
[ ! -d $LOG_HOME ] &&  mkdir -p $LOG_HOME  &&  echo "mkdir ${LOG_HOME} success"
[ ! -d $BUILD_HOME ] &&  mkdir -p $BUILD_HOME  &&  echo "mkdir ${BUILD_HOME} success"
[ ! -d $WEBAPP_HOME ] &&  mkdir -p $WEBAPP_HOME  &&  echo "mkdir ${WEBAPP_HOME} success"
[ ! -d $DEPLOY_HOME ] &&  mkdir -p $DEPLOY_HOME  &&  echo "mkdir ${DEPLOY_HOME} success"
[ ! -d $CONTROL_HOME ] &&  mkdir -p $CONTROL_HOME  &&  echo "mkdir ${CONTROL_HOME} success"

PROJECT_NAME=$1
PORT=$2
WEB_CONF_DIR="${CONF_HOME}"/"${PROJECT_NAME}-${PORT}-conf" #配置目录
USAGER="sh $0 project_name port"
TEMPLATE_DIR=template

# init 
[ "x$PROJECT_NAME" == "x" ] && echo "项目名不可以为空,USAGER:${USAGER}" && exit 1
[ "x$PORT" == "x" ] && echo "端口号不可以为空,USAGER:{USAGER}" && exit 1

#### test the ports is used or not
NUM=`lsof -i:$PORT | wc -l`
[ $NUM -ge 2 ]&& echo "PORT: $PORT is used by other process!!" && exit 1

# generate conf file
[ -d $WEB_CONF_DIR ] && rm -rf $WEB_CONF_DIR
cp -r ${TEMPLATE_DIR}/web-conf-template/ $WEB_CONF_DIR
mv $WEB_CONF_DIR/webapps/project-conf-template.xml $WEB_CONF_DIR/webapps/${PROJECT_NAME}.xml
sed -i.bak "s/PORT/${PORT}/g" ${WEB_CONF_DIR}/start.d/http.ini 
sed -i.bak -e "s/PROJECT_NAME/${PROJECT_NAME}/g" -e "s#WEBAPP_HOME#${WEBAPP_HOME}#g" ${WEB_CONF_DIR}/webapps/${PROJECT_NAME}.xml

cp -r ${TEMPLATE_DIR}/deploy/ ${DEPLOY_HOME}	
cp -r ${TEMPLATE_DIR}/control/ ${CONTROL_HOME}
mv ${CONTROL_HOME}/jetty.sh ${CONTROL_HOME}/${PROJECT_NAME}-${PORT}-control.sh	
mv ${DEPLOY_HOME}/web-build-template.sh ${DEPLOY_HOME}/${PROJECT_NAME}-${PORT}-deploy.sh	
sed -i.bak -e "s#JETTY_HOME_DIR#${JETTY_HOME_DIR}#g" -e "s#WEB_CONF_DIR#${WEB_CONF_DIR}#g" ${CONTROL_HOME}/${PROJECT_NAME}-${PORT}-control.sh 
sed -i.bak -e "s/CONTROL_SCRIPT_NAME/${PROJECT_NAME}-${PORT}-control/g"  -e "s/PROJECT_NAME_HOLDER/${PROJECT_NAME}/g" ${DEPLOY_HOME}/${PROJECT_NAME}-${PORT}-deploy.sh
sed -i.bak -e "s#BUILD_HOME#${BUILD_HOME}#g"  -e "s#WEBAPP_HOME#${WEBAPP_HOME}#g" -e "s#CONTROL_HOME#${CONTROL_HOME}#g" ${DEPLOY_HOME}/${PROJECT_NAME}-${PORT}-deploy.sh


