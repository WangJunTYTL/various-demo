#!/usr/bin/python
# coding=utf-8
# =============================
# create by wangjun on 2015-05-26
# ==============================
# install : pip install pandas
# http://pandas.pydata.org/pandas-docs/dev/text.html
# 处理文本数据
# ==============================

# 这两个是常用的单独导出
from pandas import Series, DataFrame
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from utils import util

s = Series(['A', 'B', 'C', 'Aaba', 'Baca', np.nan, 'CABA', 'dog', 'cat'])
print s.str.lower()
print s.str.len()