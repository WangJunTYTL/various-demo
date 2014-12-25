#!/usr/bin/python
# coding=utf-8
# ===========================
# author WangJun
# email wangjuntytl@163.com
# date 2014-12-18
# ============================
# desc 连接数据库demo
# ======================================
import MySQLdb

conn = MySQLdb.Connect(
    host='localhost',
    port=3306,
    user='root',
    passwd='',
    db='test',
)
cursor = conn.cursor()

myList = []

insertSQL = "insert into user (`name`) values('wangjun')"
deleteSQL = "delete from user"
querySQL = "select * from user where name ='wangjun'"

print cursor.execute(deleteSQL)
print cursor.execute(insertSQL)
print cursor.execute(querySQL)
# print cursor.execute(deleteSQL)

# 查询
n = cursor.execute("select * from user")
print n
for row in cursor.fetchall():
    myList.append("id:" + str(int(row[0])) + "\tname:" + row[1])

for ele in myList:
    print ele


cursor.close
conn.commit()
conn.close




