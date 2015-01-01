#!/usr/bin/python
# coding=utf-8
# ======================
# author WangJun
# email wangjuntytl@163.com
# date 2014-11-7
# ============================
#
# desc 测试python import 其它模块
#
# 
# ==============================================

print "测试python import 其它模块"
print '-------------------------------------------'

if __name__ == '__main__' and __package__ is None:
    from os import sys, path

    sys.path.append(path.dirname(path.dirname(path.abspath(__file__))))

from module import module_util

print("module_util version is %s" % module_util.version)
