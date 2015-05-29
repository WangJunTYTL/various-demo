#!/usr/bin/python
# coding=utf-8
# ===========================
# author WangJun
# email wangjuntytl@163.com
# date 2014-12-18
# ============================

import calendar as cal

FORMAT = "%d-%d-%d\t%d-%d-%d"
year = 2013
for m in range(1, 13):
    d = cal.monthrange(year, m)
    print FORMAT % (year, m, 1, year, m, d[1])

import time

a = "2013-1-1"
timeArray = time.strptime(a, "%Y-%m-%d")
# timeArray = time.strptime(a, "%Y-%m-%d%H:%M:%S")
timeStamp = int(time.mktime(timeArray))
print(timeStamp)

import calendar

print calendar.monthrange(2015, 2)


def get_timestamp_every_month(year):
    FORMAT = "%d-%d-%d\t%d-%d-%d"
    for m in range(1, 13):
        d = cal.monthrange(year, m)
        month = FORMAT % (year, m, 1, year, m, d[1])


def get_timestamp_by_date(date):
    timeArray = time.strptime(date, "%Y-%m-%d")
    timestamp = int(time.mktime(timeArray))
    return timestamp