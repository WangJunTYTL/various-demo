#!/usr/bin/env bash
#-----------------------------------
# 导入另一个shell
# 包括变量，函数
#-----------------------------------

#import env
if [ -f ././lib/ENV ] ; then
    source ././lib/ENV
fi

#import function lib
if [ -f ././lib/util.sh ] ; then
    . ././lib/util.sh
fi

report "My name is ${Name},you can contact me , email is ${Email}" green

report "this is a test" red
report "this is a test" green

report  red


report "$CurrentDir"