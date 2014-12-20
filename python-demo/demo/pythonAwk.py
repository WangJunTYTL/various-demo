#!/usr/bin/python
# coding=utf-8
# ===========================
# author WangJun
# email wangjuntytl@163.com
# date 2014-12-18
# ============================
# desc 处理日志文件demo
# ======================================

# db
import MySQLdb

conn = MySQLdb.Connect(
    host='172.16.10.44',
    port=3306,
    user='write',
    passwd='write',
    db='db_order',
)
order_cursor = conn.cursor()

# 收集所有下单
orderCollection = {}


def container(collection, ele):
    if len(collection) == 0:
        return False
    else:
        for i in collection:
            if i == ele:
                return True
    return False


def append_order(app_key, booking_id):
    if container(orderCollection, app_key):
        booking_list = orderCollection[app_key]
        booking_list.append(booking_id)
        orderCollection[app_key] = booking_list
    else:
        orderCollection[app_key] = [booking_id]


def append_dict(dict_db, d_k, d_v):
    if container(dict_db, d_k):
        v_list = dict_db[d_k]
        v_list.append(d_v)
        dict_db[d_k] = v_list
    else:
        dict_db[d_k] = [d_v]


f = open("./test.txt")

total_count = 0
for each_line in f:
    if len(each_line) > 6:
        arr = each_line.split("|")
        bookingId = arr[1]
        appKey = arr[7]
        total_count += 1
        append_order(appKey, bookingId)
print "今日总下单量：" + str(total_count)
# print orderCollection

# 得到各个渠道分布的call_id
result_call_app_callID = {}
for k, v in orderCollection.iteritems():
    for e in v:
        sql = "select id from t_order_queue where callid = '" + e + "'"
        order_cursor.execute(sql)
        for row in order_cursor.fetchall():
            append_dict(result_call_app_callID, k, int(row[0]))

print "各渠道下单分布表", result_call_app_callID

# 得到各个call_id 分布的订单

sql = " select order_id from t_order_queue_map where queue_id = "

result_order_hash = {}
for k, v in result_call_app_callID.iteritems():
    for e in v:
        sql += str(e)
        try:
            order_cursor.execute(sql)
            for row in order_cursor.fetchall():
                append_dict(result_order_hash, k, int(row[0]))
        except MySQLdb.Warning, w:
            sqlWarning = "Warning:%s" % str(w)

print "各渠道订单分布表", result_order_hash

# 查询订单状态
sql = "select status from t_order where order_id = "
result_order_analyze = {}
for k, v in result_order_hash.iteritems():
    for e in v:
        sql += str(e)
        order_cursor.execute(sql)
        for row in order_cursor.fetchall():
            if row[0] is not None:
                if row[0] == 1:
                    if container(result_order_analyze, k):
                        result_order_analyze[k] += 1
                    else:
                        result_order_analyze[k] = 1

print "各渠道成单量分布表", result_order_analyze
