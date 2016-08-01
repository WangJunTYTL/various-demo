#!/usr/bin/env bash
#--------------------
# shell 处理命令行参数
# 命令行参数分为三种：
#   1、 简单命令行 直接通过$num 获取
#   2、 -h 短命令行 getopts 获取
#   3、 --help 长命令行  getopt获取
# create by WangJun on 20160709
#--------------------------------
# 简单命令含参数处理
echo 'args list:'$*
echo 'args length:'$#
echo 'args pos[01]:'$1

#
while getopts "a:bc" arg
do
    case $arg in
        a)
            echo $arg'->'$OPTARG
            ;;
        b)
            echo $arg
            ;;
         /?)
            echo "null"
            ;;
    esac
done