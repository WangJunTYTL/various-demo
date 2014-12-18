#!/usr/bin/python
# coding=utf-8
# ==========================
# author WangJun
# email wangjuntytl@163.com
# date 2014-11-07
# =============================
# desc 存储器：
# Python提供一个标准的模块,称为pickle。使用它你可以在一个文件中储存任何Python对象,之 后你又可以把它完整无缺地取出来。这被称为 持久地 储存对象。
# =================================

import cPickle as p  # import pickle as p

shoplistfile = 'shoplist.data'
# the name of the file where we will store the object
shoplist = ['apple', 'mango', 'carrot']
# Write to the file
f = file(shoplistfile, 'w')
p.dump(shoplist, f)  # dump the object to a file f.close()
del shoplist  # remove the shoplist
# Read back from the storage 
f = file(shoplistfile)
storedlist = p.load(f)
print storedlist