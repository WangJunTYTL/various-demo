#!/usr/bin/python
# coding=utf-8
# ======================
# author WangJun
# email wangjuntytl@163.com
# date 2014-11-7
# ============================
# 
# desc python 面向对象
# 面向对象三大特征：封装，继承，多态
#
# 类：与java相比，没有接口，只有继承的概念
# 
# ==============================================

print "测试对象"
print '-------------------------------------------'


class Person:
    version = 1  # 静态数据

    def __init__(self, name):  # 初始方法
        self.name = name
        print ("__init__ %s" % self.name)

    def __new__(cls, *args, **kwargs):
        print ("__new__ %s" % cls.name)

    def __del__(self):
        print("__del__ %s" % "解构器")

    def say(self):
        print self.name


p = Person("My name is WJ")
print p
p.say()
print dir(p)
print("类的名字：%s" % Person.__name__)
print("类的属性：%s" % Person.__dict__)
print("类所在模块：%s" % Person.__module__)

Person.__new__(p)


class Student(Person):  # 继承
    def __init__(self, name, add):
        Person.__init__(self, name)  # 调用父类的初始化方法
        self.add = add

    def say2(self):
        Person.say(self)
        print "I'm a studnt my address is", self.add


student = Student("WJ", "BJ")
student.say2()

print "o:", repr(student)
print "str:", str(student)


# Python 提供了 is 和 is not 运算符来测试两个变量是否指向同一个对象。象下面这样执行一个测试

# cmp()与java的equals()类似
# 内建函数 cmp()用于比较两个对象 obj1 和 obj2, 如果 obj1 小于 obj2, 则返回一个负整 数,
# 如果 obj1 大于 obj2 则返回一个正整数, 如果 obj1 等于 obj2, 则返回 0。它的行为非常
# 类似于 C 语言的 strcmp()函数。比较是在对象之间进行的,不管是标准类型对象还是用户自定 义对象。
# 如果是用户自定义对象, cmp()会调用该类的特殊方法__cmp__()。在第 13 章会详细 介绍类的这些特殊方法。
# 下面是几个使用 cmp()内建函数的对数值和字符串对象进行比较的例