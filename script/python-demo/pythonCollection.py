#!/usr/bin/python
#coding=utf-8
#======================
#author WangJun
#email wangjuntytl@163.com
#date 2014-11-7
#============================
# 
#desc 测试python支持的数据结构和对面向对象的支持
#     
# 	集合：
# 		list:不可变的称为元组
# 		db：（类似于java的map）
# 	类：只有继承的概念
# 
#==============================================

print "测试python list数据结构"
print '-------------------------------------------'
mylist=[1,2,3,4,5,6]
print mylist
print '集合list长度：',len(mylist)
print '遍历'
for x in mylist:
	print x

####
mylist.append(0)
print mylist
mylist.sort()
print mylist
del mylist[0]
print mylist
print mylist[1:3]

print "测试元组"
print '-------------------------------------------'
zoo=(1,2,3)
print zoo
print zoo[0]
# zoo[0]=2 不可以修改
info=('wj','英明神武')
print 'listen %s %s' %info

print "测试字典"
print '-------------------------------------------'
db={"No.1":"Wo","No.2":"Shi","No.3":"Ni","No.4":"Da","No.5":"Ye"}
print db
print db['No.1']


print "测试对象"
print '-------------------------------------------'
class Person():
		def __init__(self,name):#初始方法
			self.name=name

		def say(self):
			print self.name

p = Person("My name is WJ");
print p
p.say()

class Student(Person):#继承
	def __init__(self,name,add):
		Person.__init__(self,name) #调用父类的初始化方法
		self.add=add

	def say2(self):
		Person.say(self)
		print "I'm a studnt my address is",self.add


student = Student("WJ","BJ")
student.say2()






