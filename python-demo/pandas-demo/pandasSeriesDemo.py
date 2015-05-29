#!/usr/bin/python2.7
# coding=utf-8
# =============================
# create by wangjun on 2015-05-26
# =============================
# install : pip install pandas
# http://pandas.pydata.org/pandas-docs/stable/10min.html
# http://www.open-open.com/lib/view/open1402477162868.html
#
# 数据结构 Series
# Series 很像一个dict ，它是这样的一种结构
#   A    1
#   B    3
#   C    5
#   D    6
#   E    8
# 其中a,b,c...e是索引 1,3,5...8 value  构建这样的结构可以通过两种方式
# 第一种通过list构造：s = Series([1,3,5,6,8],index=['A','B'.'C'.'D'.'E'])
# 第二种通过dict构造：s = Series({'A':1,'B':3,'C':5,'D':6,'E':8})
#
# 常用方法
# 获取某处索引值：s[index](假如index不存在会报KeyError) s.get(index) s.get(index,default)
# s.values  返回list结构的所有值
# 过滤查询  判断是否存在index index in s   查询符合某个条件的集合返回Series s[s > 2]
# 直接参与计算  s[ s+1 ] 每个value都进行+1操作
# ==============================

# 这两个是常用的单独导出
from pandas import Series
from utils import util

dateList = [1, 3, 5, 6, 8]
db = {"No.1": "Wo", "No.2": "Shi", "No.3": "Ni", "No.4": "Da", "No.5": "Ye"}
# Creating a Series by passing a list of values, letting pandas create a default integer index
s = Series(dateList, index=["A", "B", "C", "D", "E"])  # 索引的长度必须和list的长度一样，否则为[0, ..., len(data) - 1]
util.report_tag("Series 处理list结构数据")
print "Series data structures is \n", s
print "index is ", s.index
print "values is ", s.values
print "the fist element is ", s[0]
print "0~3 element is \n", s[:3]
print ">3 element is \n", s[s > 3]
# print "通过 s[6] 会报错", s[6]
print "通过 s.get(6) return None", s.get(6)
print "查看前2行\n", s.head(2)
print "查看最后2行\n", s.tail(2)

util.report_tag("Series 处理dict数据")
s = Series(db)
print "Series data structures is \n", s
# Series更像是一个dict ，可以直接通过 s[index] 取值 ，判断是否存在index,假如通过s[index]取一个不存在的索引，将会报 KeyError,而用
# s.get(index) 不会报错 s.get(index,"default")这时取不到时可以用一个默认值代替
print "No.1 is ", s['No.1']
print "No.1 is exist", 'No.1' in s
print "No.1 & No.2 is \n", s[['No.1', 'No.2']]

util.report_tag("Series 过滤查询")
s = Series(dateList)
print "data > 5 is \n", s[s > 5]

util.report_tag("Series 参与计算")
print s + s
print(s * 2)
print s[1:] + s[:-1]

util.report_tag("Series can also have a name attribute:")
print s.name
s = Series(db, name="db")
print(s.name)
