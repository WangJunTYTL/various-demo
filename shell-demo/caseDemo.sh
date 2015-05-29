#!/usr/bin/env bash
#--------------------------
# case语句适用于需要进行多重分支的应用情况。

#        case分支语句的格式如下：

#            case $变量名 in

#                模式1）

#            命令序列1

#            ;;

#                模式2）

#            命令序列2

#         ;;

#                *）

#            默认执行的命令序列     ;;

#            esac

#        case语句结构特点如下：

#        case行尾必须为单词“in”，每一个模式必须以右括号“）”结束。

#        双分号“;;”表示命令序列结束。

#        匹配模式中可是使用方括号表示一个连续的范围，如[0-9]；使用竖杠符号“|”表示或。

#        最后的“*）”表示默认模式，当使用前面的各种模式均无法匹配该变量时，将执行“*）”后

#    的命令序列。


#----------------------------

#case语句实例：由用户从键盘输入一个字符，并判断该字符是否为字母、数字或者其他字符,并输出相应的提示信息。

#!/bin/bash
read -p "press some key ,then press return :" KEY
case ${KEY} in
[a-z]|[A-Z])
echo "It's a letter."
;;
[0-9])
echo "It's a digit."
;;
*)
echo "It's function keys、Spacebar or other ksys."
esac

