#!/usr/bin/env bash
#--------------------------------------------
# author wangjun
# email wangjuntytl@163.com
# date 2015-05-24
#----------------------------------------------

#-------------------------------------------
# inner  util
#----------------------------------------------

report(){
    color=$2
    if [ ${color}"X" = "red""X" ]; then
        echo  -e "\033[41;30;1m$1 \033[0m"
    elif [ ${color}"X" = "greenX" ];then
        echo -e "\033[32m$1 \033[0m"
    else
        echo $1
    fi
}

reportDash(){
    report "------------------------------------------------" $1
}

reportTag(){
reportEnter
color=$2
if [ -z ${color} ]; then
    color=green
fi
report "$1" ${color}
reportDash ${color}
}

reportEnter(){
    report ""
}

function lastExeIsSuc(){
	[ $1 != 0 ] && echo $2 && exit 1
}
