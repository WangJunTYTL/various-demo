#!/usr/bin/python
# coding=utf-8
#===========================
#author wangjun
#email wangjuntytl@163.com
#date 2014-12-18
#============================
#desc 连接数据库demo
#======================================
import MySQLdb

conn = MySQLdb.Connect(
    host='localhost',
    port=3306,
    user='root',
    passwd='',
    db='test',
)
cursor = conn.cursor()

mylist=[]

#查询    
n = cursor.execute("select * from user")    
print n
for row in cursor.fetchall():   
    mylist.append("id:"+str(int(row[0]))+"\tname:"+row[1])


for ele in mylist:
	print ele