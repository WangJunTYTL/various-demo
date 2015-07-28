#!/usr/bin/env bash

curl 'esorder.edaijia.cn/db_order/t_order_order_ext/_count?pretty' -d '
 {
   "query" : {
        "filtered" : {
            "filter" : {
                "exists" : { "field" : "driver_ready_time" }
            }
        }
    }
 }'


# 查询成单，但driver_ready_time是null的
#curl 'esorder.edaijia.cn/db_order/t_order_order_ext/_count?pretty' -d '
curl 'esorder.edaijia.cn/db_order/t_order_order_ext/_count?pretty' -d '
 {
   "query" : {
        "filtered" : {
            "query" : {
               "match":{"status": "1"}
            },
            "filter": {
                "missing" : { "field" : "driver_ready_time" }
            }
        }
    }
 }'

 curl 'esorder.edaijia.cn/db_order/t_order_order_ext/_count?pretty' -d '
 {
   "query" : {
        "filtered" : {
            "query" : {
               "match":{"status": "1","driver_ready_time":"0"}
            }
        }
    }
 }'



 curl 'esorder.edaijia.cn/db_order/t_order_order_ext/_search?pretty' -d '
 {"query":{"bool":{"must":[{"term":{"t_order_order_ext.status":"1"}},{"range":{"t_order_order_ext.driver_ready_time":{"lte":"0"}}}],"must_not":[],"should":[]}},"from":0,"size":10,"sort":[],"facets":{}}'




curl -XDELETE '121.199.22.223:9200/db_order/t_order_order_ext/_query?pretty' -d '
{
  "query": { "match": { "phone": "15311192970" } }
}'



curl -XPOST 'localhost:9200/db_order/_update_by_query' -d '
{
    "query" : {
        "term" : {
            "phone" : "15652636152"
        }
    },
    "script" : "ctx._source.driver_ready_time = 1"
}'