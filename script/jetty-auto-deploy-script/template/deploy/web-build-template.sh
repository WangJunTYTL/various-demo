#!/bin/sh
#========================================
#   date   2014-10-12					 
#	Author WangJun						 
#	Email  wangjuntytl@163.com 		     
##=======================================
#   desc: 
# 		从vcs上获取代码到构建机器上进行构建  
#	params:
#	 	PROJECT_NAME#=$1 #项目名
#		BRANCH=$2; #部#署分支
#		ENVIRONMENT=$3 #mvn -P 参数
#	exit status：
# 		exits 0 on success, and >0 if an error occurs.
#=========================================================
PROJECT_NAME="PROJECT_NAME_HOLDER" #项目名
BRANCH=$1; #部#署分支
ENVIRONMENT=$2 #mvn -P 参数 
CODE_DOWN=BUILD_HOME/${PROJECT_NAME} # 代码下载根目录
CODE_BUILD=WEBAPP_HOME # 代码构建目录
CONTROL_SCRIPT=CONTROL_HOME/CONTROL_SCRIPT_NAME.sh # 控制脚本位置
EMPTY=''
USAGE="sh $# branch env"

#初始化
[ "x$PROJECT_NAME" == "x" ] && echo "项目名为空"&& exit 1
[ "x$BRANCH" == "x" ] && echo "项目分支为空,USAGE:${USAGE}"&& exit 1
[ "x$ENVIRONMENT" == "x" ] && echo "mvn 编译要求的 -P 参数为空,USAGE:${USAGE}"&& exit 1

function valid(){
	if test ! $1 == 0 ;then
		echo $2
		exit 1
	fi 
}

#准备目录
echo "==================================================================="
if test  -d ${CODE_DOWN}/ ;then
	rm -rf  ${CODE_DOWN}/
fi
mkdir ${CODE_DOWN}
valid $? "创建代码存放目录失败"
echo "准备部署的项目是${PROJECT_NAME}，分支为${BRANCH}，编译环境为$ENVIRONMENT"

#下载代码
echo "==================================================================="
echo "git clone ：git@git.s.hehua.com:${PROJECT_NAME}.git"
/usr/local/bin/git clone -b ${BRANCH} git@git.s.hehua.com:${PROJECT_NAME}.git ${CODE_DOWN}
valid $? "代码下载失败"

#切到项目根目录
cd ${CODE_DOWN}

#编译
mvn clean -P${ENVIRONMENT} compile  war:exploded  -U -Dmaven.test.skip=true -T 2.0C
valid $?

#同步构建后的文件到正式目录
rsync -ztrlvC --delete ${CODE_DOWN}/target/${PROJECT_NAME}*/ ${CODE_BUILD}/${PROJECT_NAME}
valid $? "同步失败"

echo "==================================================================="
echo "构建成功>>>------->>>开始部署>>>"
echo "==================================================================="

sh "${CONTROL_SCRIPT}" restart 
valid $? "部署失败..."
exit 0 
