#!/usr/bin/python
# coding=utf-8
# ==========================
# author WangJun
# email wangjuntytl@163.com
# date 2014-11-07
# =============================
# desc 自定义python模块，介绍常用模块
# python模块和正常的python文件没有区别
# =================================
version = '1.0'


def say():
    print "hello world,this is a python module"

# 常用模块：
# sys
# argv
# os：这个模块包含普遍的操作系统功能。如果你希望你的程序能够与平台无关的话,这个模块是尤 为重要的
# os.name字符串指示你正在使用的平台。比如对于Windows,它是'nt',而对于Linux/Unix 用户,它是'posix'。
# ● os.getcwd()函数得到当前工作目录,即当前Python脚本工作的目录路径。
# ● os.getenv()和os.putenv()函数分别用来读取和设置环境变量。
# ● os.listdir()返回指定目录下的所有文件和目录名。
# ● os.remove()函数用来删除一个文件。
# ● os.system()函数用来运行shell命令。
# ● os.linesep字符串给出当前平台使用的行终止符。例如,Windows使用'\r\n',Linux使
# 用'\n'而Mac使用'\r'。
# ● os.path.split()函数返回一个路径的目录名和文件名。
# >>> os.path.split('/home/swaroop/byte/code/poem.txt')
# ('/home/swaroop/byte/code', 'poem.txt')
# ● os.path.isfile()和os.path.isdir()函数分别检验给出的路径是一个文件还是目录。类似地,os.
# path.exists()函数用来检验给出的路径是否真地存在。
