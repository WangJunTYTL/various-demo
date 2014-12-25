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
    host='10.157.128.16',
    port=3306,
    user='edaijia_order_ro',
    passwd='EorderDaijia125ro',
    db='db_order',
)
order_cursor = conn.cursor()

conn2 = MySQLdb.connect(
    host='10.161.240.88',
    port=3306,
    user='root',
    passwd='D5ydZ4yGtCtNrc4x2yUj',
    db='db_inner_report'
)

report_cursor = conn2.cursor()

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


import datetime

now_time = datetime.datetime.now()
yes_time = now_time + datetime.timedelta(days=-1)
date = yes_time.strftime('%Y-%m-%d')
filename = "/data/logs/edaijia/api/statistics-log-file.log." + str(date) + ".log"

import os
import sys

print '......................................'
if os.path.exists(filename):
    print str(date)
    print "================================"
else:
    print str(date) + " log不存在, 统计结束"
    sys.exit(0)

f = open(filename)

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


result_order_hash = {}
for k, v in result_call_app_callID.iteritems():
    for e in v:
        sql = "select order_id from t_order_queue_map where queue_id = "
        sql += str(e)
        # print sql
        try:
            order_cursor.execute(sql)
            for row in order_cursor.fetchall():
                append_dict(result_order_hash, k, int(row[0]))
        except MySQLdb.Warning, w:
            sqlWarning = "Warning:%s" % str(w)

print "各渠道订单分布表", result_order_hash

# 查询订单状态
result_order_analyze = {}
for k, v in result_order_hash.iteritems():
    for e in v:
        if container(result_order_analyze, k):
            result_order_analyze[k]['order'] += 1
        else:
            result_order_analyze[k] = {"order": 1, "suc": 0}
        sql = "select status from t_order where order_id = "
        sql += str(e)
        order_cursor.execute(sql)
        for row in order_cursor.fetchall():
            if row[0] is not None:
                if row[0] == 1:
                    result_order_analyze[k]['suc'] += 1
print "各渠道成单量分布表", result_order_analyze

for k, v in result_order_analyze.iteritems():
    # 获得渠道中文名称
    sql = "select count(channel_value) from t_channel_map where channel_key = '" + k + "'"
    report_cursor.execute(sql)
    count = 0
    for row in report_cursor.fetchall():
        count = row[0]
    sql = "select channel_value from t_channel_map where channel_key = '" + k + "'"
    report_cursor.execute(sql)

    if count != 0:
        for row in report_cursor.fetchall():
            if row[0] is not None:
                k = row[0]

    # 两台机器数据汇总
    sql = "select count(*) from t_partner_order_report where report_date = '" + str(
        date) + "' and source = '" + k + "'"
    print sql
    report_cursor.execute(sql)
    order = 0
    suc = 0
    sql = "select order_num,complete_order_num from t_partner_order_report where report_date = '" + str(
        date) + "' and source = '" + k + "'"
    report_cursor.execute(sql)
    for row3 in report_cursor.fetchall():
        order = row3[0]
        suc = row3[1]

    # 数据入库
    sql = "delete from  t_partner_order_report where report_date = '" + str(
        date) + "' and source = '" + k + "'"
    # report_cursor.execute(sql)
    print sql

    sql = "insert into t_partner_order_report (source,call_num,order_num,complete_order_num,report_date) values ('" + k + "',0,'" + \
          str(v['order'] + order) + "','" + str(v['suc'] + suc) + "','" + str(date) + "')"
    print sql
    # report_cursor.execute(sql)
print sql
report_cursor.close()
conn2.commit()
conn2.close
order_cursor.close()
conn.close()