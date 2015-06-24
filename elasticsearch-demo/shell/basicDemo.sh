#!/usr/bin/env bash
# shell 支持的api
# api文档： https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html


# cat api
# 查看主节点信息
# curl -XGET http://localhost:9200/_cat/master\?v

# 查看状况
# curl 'localhost:9200/_cat/health?v'

# 查看索引信息
# http://localhost:9200/db_order  or http://localhost:9200/* or http://localhost:9200/twitter,db_order/
# 查看所有的索引信息
# curl 'localhost:9200/_cat/indices?v'
# 创建索引
# curl -XPUT 'localhost:9200/customer?pretty'
# 删除索引
# curl -XDELETE 'localhost:9200/customer?pretty'

#放入数据
#curl -XPUT 'localhost:9200/customer/external/1?pretty' -d '
#{
#  "name": "John Doe"
#}'
# 查看数据
# curl -XGET 'localhost:9200/customer/external/1?pretty'

# 更新数据
#curl -XPUT 'localhost:9200/customer/external/1?pretty' -d '
#{
#  "name": "John Doe"
#}'

# 放入数据 不指定id
#curl -XPOST 'localhost:9200/customer/external?pretty' -d '
#{
#  "name": "Jane Doe"
#}'

# 更新数据
#curl -XPOST 'localhost:9200/customer/external/1/_update?pretty' -d '
#{
#  "doc": { "name": "Jane Doe" }
#}'

#更新数据
#curl -XPOST 'localhost:9200/customer/external/1/_update?pretty' -d '
#{
#  "script" : "ctx._source.age += 5"
#}'

# 删除数据
#curl -XDELETE 'localhost:9200/customer/external/2?pretty'

# 批量删除数据
#curl -XDELETE 'localhost:9200/customer/external/_query?pretty' -d '
#{
#  "query": { "match": { "name": "John" } }
#}'
# 批量删除数据
# curl -XDELETE 'http://121.199.22.223:9200/db_order/t_order_order_ext/_query?q=phone:18518551024'


