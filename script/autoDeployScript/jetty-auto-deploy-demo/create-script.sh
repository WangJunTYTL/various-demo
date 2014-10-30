#!bin/bash
PROJECT_NAME=$1
PORT=$2
WEB_CONF_DIR=${PROJECT_NAME}-${po}-conf


[ "x$PROJECT_NAME" == "x" ] && echo "项目名不可以为空" && exit 1
[ "x$PORT" == "x" ] && echo "端口号不可以为空" && exit 1

#### test the ports is used or not
NUM=`lsof -i:$PORT | wc -l`
[ $NUM -ge 2 ]&& echo "PORT: $PORT is used by other process!!" && exit 1


[ -d $WEB_CONF_DIR ] && rm -rf $WEB_CONF_DIR
cp -r web-conf-template/ $WEB_CONF_DIR
mv $WEB_CONF_DIR/webapps/project-conf-template.xml $WEB_CONF_DIR/webapps/${PROJECT_NAME}.xml
sed -i.bak "s/PORT/${PORT}/g" $WEB_CONF_DIR/start.d/http.ini 
sed -i.bak "s/PROJECT_NAME/${PROJECT_NAME}/g" $WEB_CONF_DIR/webapps/${PROJECT_NAME}.xml

cp -r deploy/ jetty-deploy	
cp -r control/ jetty-control
mv jetty-control/jetty.sh jetty-control/${PROJECT_NAME}-${PORT}-control.sh	
mv jetty-deploy/web-build-template.sh jetty-deploy/${PROJECT_NAME}-${PORT}-deploy.sh	
sed -i.bak "s/WEB_CONF_DIR/${WEB_CONF_DIR}/g" jetty-control/${PROJECT_NAME}-${PORT}-control.sh 
sed -i.bak -e "s/CONTROL_SCRIPT_NAME/${PROJECT_NAME}-${PORT}-control/g"  -e "s/PROJECT_NAME_HOLDER/${PROJECT_NAME}/g" jetty-deploy/${PROJECT_NAME}-${PORT}-deploy.sh