#!/usr/bin/env bash
#---------------------
# elasticsearch
# 基本 shell api
# create by wangjun on 2015-09-14
#---------------------------



function exe_cmd(){
    cmd="$1"
    echo "${cmd}"
    curl  ${cmd}
}

# 创建索引
cmd=""" -XPUT  'localhost:9200/customer?pretty' """

# 查看所有索引
cmd='localhost:9200/_cat/indices?v'

# 添加数据
cmd='-XPUT \'127.0.0.1:9200/customer/external/1?pretty\' -d \'
{
    \"name\":\"John Doe\"
}\'
'

exe_cmd "${cmd}"




