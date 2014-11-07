#!/usr/bin/python
#coding=utf-8
#==========================
#author wangjun
#email wangjuntytl@163.com
#date 2014-11-07
#=============================
#desc python 异常处理
#=================================
f = file("test.txt")
try:
	print f.readline
except Exception, e:
	print "读取文件出现错误:",e
else:
	print "no Exception" #没有异常执行
finally:
	print f.close


#自定义异常，继承Exception
class ShortInputException(Exception): 
	def __init__(self, length, atleast):
		Exception.__init__(self) 
		self.length = length 
		self.atleast = atleast

# 插播 time模块
import time
print 'Good'
time.sleep(1)
print 'Good'
time.sleep(1)
print 'Study'
time.sleep(1)
