#!/usr/bin/env bash

## 查询语句
curl -is '127.0.0.1:9200/test/_search?pretty' -d '{query:{match_all:{}},from:0,size:2}'

curl -is '127.0.0.1:9200/test/_search?pretty' -d '{query:{match:{name:"wj"}},from:0,size:2}'

# 过滤语句
curl -is '127.0.0.1:9200/test/_search?pretty' -d '{filter:{term:{name:"wj"}},from:0,size:2}'

# 解释语句
curl -is '127.0.0.1:9200/test/_validate/query?pretty' -d '{filter:{term:{name:"wj"}},from:0,size:2}'

curl -is '127.0.0.1:9200/test/_validate/query?explain&pretty' -d '{filter:{term:{name:"wj"}},from:0,size:2}'