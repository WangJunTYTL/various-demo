#!/usr/bin/python2.7
# coding=utf-8
# =============================
# install : pip install pandas
# http://pandas.pydata.org/pandas-docs/stable/10min.html
# http://www.open-open.com/lib/view/open1402477162868.html
# 数据结构  DataFrame
# dataFrame 是pandas里面最为常用的数据结构，你会发现有了它，你在操作数据时变的轻松起来
# 如果说Series是1D（一维空间数据）结构数据，那DataFrame就是2D（二维空间数据）结构
#
# ==============================

# 这两个是常用的单独导出
from pandas import Series, DataFrame
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

if __name__ == '__main__' and __package__ is None:
    from os import sys, path

    sys.path.append(path.dirname(path.dirname(path.abspath(__file__))))
    print path.dirname(path.dirname(path.abspath(__file__)))
from utils import util

dateList = [1, 3, 5, 6, 8]
db = {"No.1": "Wo", "No.2": "Shi", "No.3": "Ni", "No.4": "Da", "No.5": "Ye"}
# Creating a Series by passing a list of values, letting pandas create a default integer index
s = Series(dateList, index=["A", "B", "C", "D", "E"])  # 索引的长度必须和list的长度一样，否则为[0, ..., len(data) - 1]
print "Series data structures is \n", s
print "index is ", s.index
print "values is ", s.values
print "the fist element is ", s[0]
print "0~3 element is \n", s[:3]
print ">3 element is \n", s[s > 3]

s = Series(db)
print "No.1 is ", s['No.1']
print "Wo is in db", 'No.1' in s

# Creating a DataFrame by passing a numpy array, with a datetime index and labeled columns:
dates = pd.date_range('20130101', periods=6)
df = pd.DataFrame(np.random.randn(6, 4), index=dates, columns=list('ABCD'))
print dates
print df
print df.T

s = Series(db)
util.report_tag("dict to Series:")
print(s)

util.report_tag("dict to DataFrame")
df = DataFrame(db, index=[1, 2, 3, 4, 5])
print df




