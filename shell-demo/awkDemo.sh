#!/usr/bin/env bash
#---------------------------
#
# 处理模式： awk pattern {action01} {action02}
# NR   行号 print NR 会输出当前行号
# NF   每行字段个数
# $0    表示整行
# print 打印每一行
# 格式化输出 printf
#
# 参考文档：http://awk.readthedocs.org/en/latest/index.html
#-------------------------------------------------------

. ./lib/util.sh

reportTag "输出行号，和每行被切分的字段个数"
awk '{print "第"NR"行",NF"个字段"}' ./awkDemo.sh

reportTag "格式化输出"
awk '{printf("第%s行,%s个字段\n",NR,NF)}' ./awkDemo.sh

reportTag "选择输出"
awk '$1=="#" {print}' ./awkDemo.sh

reportTag '特殊模式 BEGIN 用于匹配第一个输入文件的第一行之前的位置， END 则用于匹配处理过的最后一个文件的最后一行之后的位置。这个程序使用 BEGIN 来输出一个标题：:'
awk 'BEGIN {print "行\t字段"}
    {print NR,"\t",NF}
    {size = size + NF}
    END {print "-------------\n","共计",size,"个字段"}
' ./awkDemo.sh
