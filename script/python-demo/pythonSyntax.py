#!/usr/bin/python
#coding=utf-8
#==========================
#author wangjun
#email wangjuntytl@163.com
#date 2014-11-07
#=============================
#desc python 基本语法学习
#    
#	repl:
#	 	支持repl模式，
# 	
# 	如果不懂某个语法,可以使用help(object)
# 	查看模块所有的属性dir(object) [dir():查看当前]	
#=================================
import sys  #使用模块 sys模块包含了与Python解释器和它的环境有关的函数（可以在使用的位置导入，在头部导入不是必须的）
import pythonModule #导入自定义的模块，模块导入类似与java的类导入
x  = 5  #定义变量
y = "hello world"
z = '''welcome \
everyone'''

print y,z  

if x==5:
	print "x",x #严格的缩进规则，通过缩进判断语法块的开始和结束，所以不用像java用‘;’来标识结束

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

pythonModule.say()

print 'pythonModule version is ',pythonModule.version
