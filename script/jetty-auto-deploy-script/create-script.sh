#!bin/bash
#=======================================
#author WangJun
#email wangjuntytl@163.com
#date 2014-11-5
#=======================================
#desc create web deploy script
#=======================================
# env init 
export PROJECT_NAME="$1"
export PORT="$2"
source ./env.sh 


WEB_CONF_DIR="${CONF_HOME}"/"${PROJECT_NAME}-${PORT}-conf" #配置目录
USAGER="sh $0 project_name port"
TEMPLATE_DIR=jetty-base

# init 
[ "x$PROJECT_NAME" == "x" ] && echo "项目名不可以为空,USAGER:${USAGER}" && exit 1
[ "x$PORT" == "x" ] && echo "端口号不可以为空,USAGER:${USAGER}" && exit 1
#### test the ports is used or not
NUM=`lsof -i:$PORT | wc -l`
[ $NUM -ge 2 ]&& echo "PORT: $PORT is used by other process!!" && exit 1


echo "JETTY_HOME:${JETTY_HOME_DIR}" #jetty dir
echo "JETTY_BASE:${CONF_HOME}" #web conf dir
echo "LOG_HOME:${LOG_HOME}" #log dir
echo "BUILD_HOME:${BUILD_HOME}" # build dir
echo "WEBAPP_HOME:${WEBAPP_HOME}" #webapp dir
echo "CONTROL_HOME:${CONTROL_HOME}"
echo "DEPLOY_HOME:${DEPLOY_HOME}"

[ ! -d $CONF_HOME ] &&  mkdir -p $CONF_HOME  &&  echo "mkdir ${CONF_HOME} success"
[ ! -d $LOG_HOME ] &&  mkdir -p $LOG_HOME  &&  echo "mkdir ${LOG_HOME} success"
[ ! -d $BUILD_HOME ] &&  mkdir -p $BUILD_HOME  &&  echo "mkdir ${BUILD_HOME} success"
[ ! -d $WEBAPP_HOME ] &&  mkdir -p $WEBAPP_HOME  &&  echo "mkdir ${WEBAPP_HOME} success"
[ ! -d $DEPLOY_HOME ] &&  mkdir -p $DEPLOY_HOME  &&  echo "mkdir ${DEPLOY_HOME} success"
[ ! -d $CONTROL_HOME ] &&  mkdir -p $CONTROL_HOME  &&  echo "mkdir ${CONTROL_HOME} success"

# generate conf file
[ -d $WEB_CONF_DIR ] && rm -rf $WEB_CONF_DIR
cp -r ${TEMPLATE_DIR}/web-conf-template/ $WEB_CONF_DIR
mv $WEB_CONF_DIR/webapps/project-conf-template.xml $WEB_CONF_DIR/webapps/${PROJECT_NAME}.xml
sed -i.bak "s/PORT/${PORT}/g" ${WEB_CONF_DIR}/start.d/http.ini 
sed -i.bak -e "s/PROJECT_NAME/${PROJECT_NAME}/g" -e "s#WEBAPP_HOME#${WEBAPP_HOME}#g" ${WEB_CONF_DIR}/webapps/${PROJECT_NAME}.xml

cp -r ${TEMPLATE_DIR}/deploy/* ${DEPLOY_HOME}	
cp -r ${TEMPLATE_DIR}/control/* ${CONTROL_HOME}
mv ${CONTROL_HOME}/jetty.sh ${CONTROL_HOME}/${PROJECT_NAME}-${PORT}-control.sh	
mv ${DEPLOY_HOME}/web-build-template.sh ${DEPLOY_HOME}/${PROJECT_NAME}-${PORT}-deploy.sh	
sed -i.bak -e "s#JETTY_HOME_DIR#${JETTY_HOME_DIR}#g" -e "s#WEB_CONF_DIR#${WEB_CONF_DIR}#g" -e "s#LOG_HOME#${LOG_HOME}#g" ${CONTROL_HOME}/${PROJECT_NAME}-${PORT}-control.sh 
sed -i.bak -e "s/CONTROL_SCRIPT_NAME/${PROJECT_NAME}-${PORT}-control/g"  -e "s/PROJECT_NAME_HOLDER/${PROJECT_NAME}/g" ${DEPLOY_HOME}/${PROJECT_NAME}-${PORT}-deploy.sh
sed -i.bak -e "s#BUILD_HOME#${BUILD_HOME}#g"  -e "s#WEBAPP_HOME#${WEBAPP_HOME}#g" -e "s#CONTROL_HOME#${CONTROL_HOME}#g" ${DEPLOY_HOME}/${PROJECT_NAME}-${PORT}-deploy.sh

echo "--------------------------------"
echo -e "\033[41;36m 脚本创建成功 \033[0m"


