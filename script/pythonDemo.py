#!/usr/bin/python
#coding=utf-8

import sys  #使用模块 sys模块包含了与Python解释器和它的环境有关的函数
import myModule
x  = 5  #定义变量
y = "hello world"
z = '''welcome \
everyone'''

print y,z  

if x==5:
	print "x",x

while x > 3:
	print "loop",x
	x=x-1
else:
	print "done"

def say(mes):
	print "say",mes
say("hello")

print say("hello")

for i in sys.argv:
	print i

print sys.copyright

print sys.path

print "hello world"

print sys.argv #使用sys模块的argv变量

print sys.path[0]

myModule.say()

print 'myModule version is ',myModule.version
