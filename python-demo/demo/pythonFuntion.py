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


def fetch_bigtable_rows(big_table, keys, other_silly_variable=None):
    """Fetches rows from a Bigtable.

    Retrieves rows pertaining to the given keys from the Table instance
    represented by big_table.  Silly things may happen if
    other_silly_variable is not None.

    Args:
        big_table: An open Bigtable Table instance.
        keys: A sequence of strings representing the key of each table row
            to fetch.
        other_silly_variable: Another optional variable, that has a much
            longer name than the other args, and which does nothing.

    Returns:
        A dict mapping keys to the corresponding table row data
        fetched. Each row is represented as a tuple of strings. For
        example:

        {'Serak': ('Rigel VII', 'Preparer'),
         'Zim': ('Irk', 'Invader'),
         'Lrrr': ('Omicron Persei 8', 'Emperor')}

        If a key from the keys argument is missing from the dictionary,
        then that row was not found in the table.

    Raises:
        IOError: An error occurred accessing the bigtable.Table object.
    """
    pass

print say.__doc__

if __name__ == '__main__':
    print 'This program is being run by itself'
else:
    print 'I am being imported from another module'

import pythonModule

print pythonModule.__name__

# 可以使用内建的dir函数来列出模块定义的标识符。标识符有函数、类和变量。
print dir(pythonModule)
