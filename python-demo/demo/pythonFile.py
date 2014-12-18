#!/usr/bin/python
# coding=utf-8
# ==========================
# author WangJun
# email wangjuntytl@163.com
# date 2014-11-07
# =============================
# desc python 操作文件
# =================================
print "写入文件"
print "---------------------"
f = file('poem.txt', 'w')  # open for 'w'riting  ‘d’ 追加模式 默热 r
f.write("Hi...MM")  # write text to file
f.close()  # close the file
print "写入完毕"

print "读入"
print "----------"
f = file('poem.txt')
while True:
    line = f.readline()
    if len(line) == 0:
        break
    print line
f.close
print "读取完毕"

