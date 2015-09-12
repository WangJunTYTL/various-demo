#!/usr/bin/python2.7
# coding=utf-8
# =============================
# install : pip install pandas
# http://pandas.pydata.org/pandas-docs/stable/10min.html
# http://www.open-open.com/lib/view/open1402477162868.html
# pandas 从数据库或文件系统读取数据和保存数据
# ==============================

# 这两个是常用的单独导出
from pandas import Series, DataFrame
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import MySQLdb
from utils import util

conn = MySQLdb.Connect(
    host='db.***.cn',
    port=3306,
    user='write',
    passwd='write',
    db='db_crm',
)

df = pd.read_sql("select phone , order_count from t_user_basic_info limit 1", conn)
# df = pd.read_sql("select * from t_user_basic_info limit 1", conn, index_col="phone")
print df

util.report_tag("保存数据到文件：支持csv,xls,xlsx,hdf,html,txt")
df.to_csv("aa.csv")
print("已保存到aa.csv")
# 需要下载xlwt 模块
df.to_excel("bb.xls", sheet_name='Sheet1')
print("已保存到bb.xls")
# 需要下载openpyxl 摸块
# df.to_excel("bb.xlsx", index_label='label', merge_cells=False)

util.report_tag("保存数据到json串中")
json = df.to_json()
print(json)

util.report_tag("保存数据到数据库")
# 保存数据到表里，aa是表名 ，需要安装 sqlalchemy 模块
from sqlalchemy import create_engine

engine = create_engine('mysql+mysqldb://write:write@db.***.cn/db_crm')
sql = df.to_sql("aa", engine, if_exists='append')
df = pd.read_sql_table("aa", engine)
print df

util.report_tag("生成html")
html = df.to_html
print(html)