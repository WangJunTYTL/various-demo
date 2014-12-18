#!/usr/bin/python
# coding=utf-8
#===========================
#author wangjun
#email wangjuntytl@163.com
#date 2014-12-18
#============================
#desc 处理日志文件demo
#======================================


f = open("/Users/wangjun/log.txt")

total_count=0
for each_line in f:
	if each_line.find("01050035|success")>-1:
		arr = each_line.split("|")
	 	print arr[1]
	 	total_count=total_count+1;
print "共"+str(total_count)+"条"

