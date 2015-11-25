#!/usr/bin/python
# coding=utf-8

from distutils.core import setup

# distutils 是python的打包工具
# python setup.py build 在当前目录构建
# python setup.py install 安装到本地系统库
# python setup.py sdist 打包
# python setup.py --help

setup(name='Hello',
      version='1.0',
      description='a simple example',
      author='WangJun',
      author_email='wangjuntytl@163.com',
      py_modules=['hello'])
