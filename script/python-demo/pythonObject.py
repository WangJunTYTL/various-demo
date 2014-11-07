#!/usr/bin/python
#coding=utf-8

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


class Person():
	pass
	
	
		
p = Person();

print p