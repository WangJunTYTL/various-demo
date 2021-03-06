#!/usr/bin/python
# coding=utf-8
# ======================
# author WangJun
# email wangjuntytl@163.com
# date 2014-11-7
# ============================
# 
# desc 测试python支持的数据结构和对面向对象的支持
# 数据结构是独立于语言之外的知识，这写知识特别重要，python已经实现的数据结构有list，不可变list(在python里叫元组)，数据字典（在java里叫map）
# 集合：
# list:不可变的称为元组
# db：（类似于java的map）
# 类：只有继承的概念
# 
# ==============================================

print "测试python list数据结构"
print '-------------------------------------------'
myList = [1, 2, 3, 4, 5, 6]
print myList
print '集合list长度：', len(myList)
print '遍历'
for x in myList:
    print x
# len  Return the number of items of a sequence or mapping.
print ("含有1的次数" + str(myList.count(1)))

####
myList.append(0)
print myList
myList.sort()
print myList
del myList[0]
print myList
print '返回索引从1到3的集合', myList[1:3]
print '''list 常用方法 'append', 'count', 'extend', 'index', 'insert', 'pop', 'remove', 'reverse', 'sort' '''

print "测试元组 tuple"
print '-------------------------------------------'
zoo = (1, 2, 3)
print zoo
print zoo[0]
# zoo[0]=2 不可以修改
info = ('wj', '英明神武')
print 'listen %s %s' % info

print "测试set"
print '-------------------------------------------'
aset = {1, 2, 3, 4, 5}
print aset
aset.add(6)
print(aset)

print "测试字典"
print '-------------------------------------------'
db = {"No.1": "Wo", "No.2": "Shi", "No.3": "Ni", "No.4": "Da", "No.5": "Ye"}
print db
print db['No.1']

db2 = {"a": {"suc": 1}}

print db2['a']['suc']

if db2.has_key('b'):
    print 'none'
# 'clear',      D.clear() -> None.  Remove all items from D.
# 'copy',       c = D.copy() 复制一份值给c
# 'fromkeys',
# 'get',  == c[index]
# 'has_key',
# 'items',
# 'iteritems',
# 'iterkeys',
# 'itervalues',
# 'keys',
# 'pop',
# 'popitem',
# 'setdefault',
# 'update',
# 'values',
# 'viewitems',
# 'viewkeys',
# 'viewvalues

# 其它通用函数
# len Return the number of items of a sequence or mapping.
# map Return a list of the results of applying the function to the items of the argument sequence(s).










