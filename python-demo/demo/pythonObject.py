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


class Person():
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
