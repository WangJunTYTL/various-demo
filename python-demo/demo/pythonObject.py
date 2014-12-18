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
    def __init__(self, name):  # 初始方法
        self.name = name

    def say(self):
        print self.name


p = Person("My name is WJ");
print p
p.say()


class Student(Person):  # 继承
    def __init__(self, name, add):
        Person.__init__(self, name)  # 调用父类的初始化方法
        self.add = add

    def say2(self):
        Person.say(self)
        print "I'm a studnt my address is", self.add


student = Student("WJ", "BJ")
student.say2()
