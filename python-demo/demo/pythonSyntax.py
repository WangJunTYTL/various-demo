#!/usr/bin/python
# coding=utf-8
# ==========================
# author WangJun
# email wangjuntytl@163.com
# date 2014-11-07
# =============================
# desc python 基本语法学习
#
# repl:
# 支持repl模式：输入一条语句，立马可以在命令行看到执行结果
#
# 如果不懂某个语法,可以使用help(object)
# 查看模块所有的属性dir(object) [dir():查看当前]
# 
# 一门语言组成部分：
# 变量：字符串，整数...
# 控制结构:if for while...
# 函数或方法定义:
# 学习语言的过程：
# 语言易学，知道怎么定义变量，怎么使用控制结构，然后知道怎么定义方法，这是最基本的，其它的语言也都是这样，
# 然后学习语言的高级部分：比如面向对象，如果知道面向对象的概念，那其它语言也都是一样
# 所以如果有一门语言功底，要学会这两点，只需要瞟一眼就可以了,
# 用学习python举个例子：你现在做java开发的，那么要学会上面两点的知识时，可以利用2个小时就可以完成学习任务，因为大同小异。
# 那么接下来你就要学习的是这们语言的特点，python是一门脚本语言，那么重心放入的是要学习与它特点有关的东东。
#
# =================================

x = 5  # 定义变量
y = "hello world"  # 单引号与双引号一样 （‘’‘）换行使用  (\)转义字符
z = '''welcome  
everyone'''
s = y + z
ss = y, z  # (,)组成元组 元组概念将会在pythonCollection文件介绍
print s
print ss

print 'if'
print '----------------'
if x == 5:
    print "x", x  # 严格的缩进规则，通过缩进判断语法块的开始和结束，所以不用像java用‘;’来标识结束

if x == 5: print "x=5"  # 如果只有一条语句，可以在同一行

print 'while'
print '-------------'
while x > 3:
    print "loop", x
    x = x - 1
else:  # 注意else，在java中else是和if配合使用，在python里可以和while，try...catch配合使用
    print "done"

print 'for continue break 不在介绍（与java一样）'
print '----------------------'

print '方法'
print '-------------'


def say(mes):  # 定义函数
    print "say", mes


print "调用say(mes)->",
say("hello")  # (,)可以不用换行

print '可变参数'


def say(mes, *args):  # 可变参数(与java一样)
    print mes, args


say("A", "B", "C", "D", "E")

print "模块"
print '------------'
# 一个模块就相当于java的一个类文件,如果要看模块里地所有属性dir(module)
# 使用前先导入
import sys  # 使用模块 sys模块包含了与Python解释器和它的环境有关的函数（可以在使用的位置导入，在头部导入不是必须的）
import pythonModule  as test  # 导入自定义的模块，模块导入类似与java的类导入 ,as 为模块起别名

for i in sys.argv:
    print '调用参数', i

print 'sys.path:当前环境的path变量->', sys.path

test.say()

print 'pythonModule version is ', test.version

print '基本语法学习完毕，接下来学习python支持的数据结构->pythonCollection'
