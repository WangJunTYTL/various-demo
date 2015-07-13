#!/usr/bin/env bash
# mysql数据同步到elasticsearch
# 主要利用这么一个插件： https://github.com/WangJunTYTL/elasticsearch-river-jdbc
# 你可以指定要同步的数据表即可，他可以支持定时抓取数据表中的数据




# 删除index
# curl -XDELETE 'localhost:9200/_river/db_order/'
#curl -XDELETE 'localhost:9200/_river/my_jdbc_river/'
#exit 0
curl -XPUT 'localhost:9200/_river/db_order/_meta' -d '{
    "type" : "jdbc",
    "jdbc" : {
        "url" : "jdbc:mysql://db.edaijia.cn:3306/db_order",
        "user" : "write",
        "password" : "write",
        "sql" : "select * from t_order",
        "index":"db_order",
        "type" : "t_order",
        "timezone":"TimeZone.getDefault()",
        "flush_interval" : "5s",
        "index_settings" : {
            "index" : {
                "number_of_shards" : 5,
                "number_of_replicas": 0
            }
        }
    }
}'
