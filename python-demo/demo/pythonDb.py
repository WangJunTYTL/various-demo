#!/usr/bin/python
# coding=utf-8
# ===========================
# author WangJun
# email wangjuntytl@163.com
# date 2014-12-18
# ============================
# desc 连接数据库demo
#
# python 有一套操作数据库的接口 DB-API,有关数据库的开发知识可以了解下这些api
# MySQLdb 实现了DB-API
# ======================================

# DB-API 异常类
# Warning       警告异常基类
# Error         错误异常基类
# InterfaceError    数据库接口错误
# DatabaseError     数据库错误
# DataError      理数据时出错
# OperationalError 数据库执行命令时出错
# IntegrityError    数据完整性错误
# InternalError     数据库内部出错
# ProgrammingError  SQL 执行失败


# DB-API 规范建议但不强制接口程序的开发者为所有数据库接口模块编写异常类.
# 如果没有提 供异常类, 则假定该连接对象会引发一致的模块级异常. 一旦你完成了数据库连接,
# 并且关闭了 游标对象, 你应该执行 commit() 提交你的操作, 然后关闭这个连接.

# 连接对象方法
# close()       关闭数据库连接
# commit()      提交当前事务
# rollback()    取消当前事务
# cursor()      使用这个连接创建并返回一个游标或类游标的对象
# errorhandler (cxn, cur,
#               errcls, errval)


# 列举了游标对象拥有的属性和方法.
# arraysize     fechmany()方法一次取出多少条记录, 默认值为 1
# connectionn   创建此游标对象的连接(可选)
# description   返回游标活动状态(一个包含七个元素的元组): (name, type_code,
# display_size, internal_ size, precision, scale, null_ok); 只有 name 和 type_code 是必须提供的.
# lastrowid     返回最后更新行的id，如果数据库不支持行id，默认返回None
# rowcount      最后一次执行execute（）操作返回或受影响的行数
# callproc(func[,args]) 调用一个存储过程
# close()       关闭游标对象
# execute(op[,args]) 执行一个数据库查询或命令
# executemany(op,args) 类似 execute() 和 map() 的结合, 为给定的每一个参数准备并执行
# 表 21.6 游标对象属性(续)
# 对象属性            描述
# fetchone()        得到结果集的下一行
# fetchmany([size=cursor.arraysize]) 得到结果集的下几行
# fetchall()        返回结果集中的所有行
# __iter__()        创建一个迭代对象（可选，参阅：next（））
# messages          游标执行后数据库返回的信息列表（元组集合）（可选）
# next()            使用迭代对象得到结果集的下一行（可选，类似fetchone(),参阅__iter__()）
# nextset()         移到下一个结果集（如果支持的话）
# rownumber         当前结果集中游标的索引（以行为单位，从0开始）（可选）
# setinput- sizes(sizes) 设置输入最大值 (必须有, 但具体实现是可选的) setoutput- size(size[,col]) 设置大列的缓冲区大写(必须有, 但具体实现是可选的)
# 游标对象最重要的属性是 execute*() 和 fetch*() 方法. 所有对数据库服务器的请求都由它
# 们来完成.对 fetchmany()方法来说, 设置一个合理的 arraysize 属性会很有用. 当然, 在不需要时 关掉游标对象也是个好主意. 如果你的数据库支持存储过程, 你就可以使用 callproc() 方法.

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




