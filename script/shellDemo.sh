#!/bin/bash
#===============================================
#shell script study 
#date 2014-10-10
#author WangJun

echo '$#->'$#  

echo '$*->'$*

echo '$1->'$1

echo '$?->'$?

#gt >
if [ $1 -gt 1 ]; then
	echo "ok > "
fi

if test ! 221 == 1 ; then
	echo "!="
fi

NAME=''
EMPTY='qq'

if [ ${NAME}x == ${EMPTY}x ]; then 
	echo 'empty'
fi
echo $NAME

function printit(){
    echo  "Your choice is $1"     # 加上 -n 可以不断行继续在同一行显示
}


printit "123"

