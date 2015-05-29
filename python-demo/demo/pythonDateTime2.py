#!/usr/bin/python
# coding=utf-8
# ===========================
# author WangJun
# email wangjuntytl@163.com
# date 2014-12-18
# ============================

import calendar as cal
import time


def get_timestamp_every_month(year):
    format = "%d-%d-%d"
    result = []
    for m in range(1, 13):
        d = cal.monthrange(year, m)
        # month = format % (year, m, 1, year, m, d[1])
        month = format % (year, m, 1)
        result.append(get_timestamp_by_date(month))
        month = format()
    return result


def get_timestamp_by_date(date):
    print(date)
    timeArray = time.strptime(date, "%Y-%m-%d")
    timestamp = int(time.mktime(timeArray))
    return timestamp


print get_timestamp_every_month(2013)