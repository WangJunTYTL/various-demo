#!/usr/bin/python
# coding=utf-8

import json

myList = [1, 2, 3, 4, 5]
# 对象转json
jsonStr = json.dumps(myList)
print jsonStr
# json 转对象
myList2 = json.loads(jsonStr)
print(myList2[1:3])