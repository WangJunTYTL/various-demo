#!/usr/bin/python
#coding=utf-8
#==========================
#author wangjun
#email wangjuntytl@163.com
#date 2014-11-07
#=============================
#desc python最后总结
#=================================

#特殊方法
	# 一些特殊的方法
	# ￼￼￼￼￼￼￼￼￼￼￼￼￼￼
	# 名称
	# 说明
	# __init__(self,...)
	# 这个方法在新建对象恰好要被返回使用之前被调用。（=java的构造方法）
	# __del__(self)（=java的回收方法）
	# 恰好在对象要被删除之前调用。
	# __str__(self)（=java toString方法）
	# 在我们对对象使用print语句或是使用str()的时候调用。
	# __lt__(self,other)
	# 当使用 小于 运算符(<)的时候调用。类似地,对于所有的运算符 (+,>等等)都有特殊的方法。
	# __getitem__(self, key)
	# 使用x[key]索引操作符的时候调用。
	# __len__(self)
	# 对序列对象使用内建的len()函数的时候调用。


# exec和eval语句
	# exec语句用来执行储存在字符串或文件中的Python语句。例如,我们可以在运行时生成一个包 含Python代码的字符串,然后使用exec语句执行这些语句。下面是一个简单的例子。
	# >>> exec 'print "Hello World"' Hello World
	# eval语句用来计算存储在字符串中的有效Python表达式。下面是一个简单的例子。
	# >>> eval('2*3') 6

