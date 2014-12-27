#!/usr/bin/python
# coding=utf-8
# ==========================
# author WangJun
# email wangjuntytl@163.com
# date 2014-11-07
# =============================
# desc python 操作文件
# =================================


def say(mes, *args):  # 可变参数(与java一样)
    '''Test function.

    hello world.'''
    print mes, args



print say.__doc__

if __name__ == '__main__':
    print 'This program is being run by itself'
else:
    print 'I am being imported from another module'

import pythonModule

print pythonModule.__name__

# 可以使用内建的dir函数来列出模块定义的标识符。标识符有函数、类和变量。
print dir(pythonModule)
