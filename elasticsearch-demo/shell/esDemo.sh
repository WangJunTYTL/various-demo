#!/usr/bin/env bash

#curl -i -XGET 'localhost:9200/'
#curl -i -XGET 'localhost:9200/                
# http://localhost:9200/_cluster/health?pretty'


#
#curl -XPUT 'localhost:9200/_river/my_jdbc_river/_meta' -d '{
#    "type" : "jdbc",
#    "jdbc" : {
#        "url" : "jdbc:mysql://db.edaijia.cn:3306/db_crm",
#        "user" : "write",
#        "password" : "write",
#        "sql" : "select * from t_user_basic_info"
#    }
#}'



#curl -XPUT 'localhost:9200/_river/my_jdbc_river2/_meta' -d '{
#    "type" : "jdbc",
#    "jdbc" : {
#        "url" : "jdbc:mysql://db.edaijia.cn:3306/db_crm",
#        "user" : "write",
#        "password" : "write",
#        "sql" : "select * from t_user_basic_info",
#        "index" : "db_crm",
#        "type" : "user_basic_info",
#        "timezone":"Asia/Shanghai"
#    }
#}'

# 删除index
# curl -XDELETE '121.199.22.223:9200/db_order/'
#curl -XDELETE '121.199.22.223:9200/_river/db_order/'
#
#exit 0

curl -XPUT '121.199.22.223:9200/_river/db_order/_meta' -d '{
    "type" : "jdbc",
    "jdbc" : {
        "url" : "jdbc:mysql://10.241.221.106:3306/db_order",
        "user" : "edaijia",
        "password" : "XpMfGYWFbAvaYQyb",
        "sql" :[{
            "statement" :"SELECT t_order.* ,t_order.order_id as _id , t_order_ext.driver_ready_time as driver_ready_time from t_order  LEFT JOIN t_order_ext on t_order.order_id = t_order_ext.order_id  "
        }],
        "index" : "db_order",
        "type" : "t_order_order_ext",
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


#curl -XPUT 'localhost:9200/_river/db_order/_meta' -d '{
#    "type" : "jdbc",
#    "jdbc" : {
#        "url" : "jdbc:mysql://db.edaijia.cn:3306/db_crm",
#        "user" : "write",
#        "password" : "write",
#        "sql" :[{
#            "statement" :"select * from t_user_basic_info  where  UNIX_TIMESTAMP(create_time) >  (UNIX_TIMESTAMP(?)+28800)",
#            "parameter" : [ "$river.state.last_active_begin" ]
#        }],
#        timezone:"TimeZone.getTimeZone(System.getProperty(\"user.timezone\"))",
#        "index" : "db_order",
#        "type" : "t_order",
#        "flush_interval" : "5s",
#        "schedule" : "1 * * ? * *"
#    }
#}'
#
#exit 0

#curl -XPUT 'localhost:9200/_river/db_order/_meta' -d '{
#    "type" : "jdbc",
#    "jdbc" : {
#        "url" : "jdbc:mysql://db.edaijia.cn:3306/db_order",
#        "user" : "write",
#        "password" : "write",
#        "sql" :[{
#            "statement" :"SELECT t_order.* ,t_order.order_id as _id , t_order_ext.driver_ready_time as driver_ready_time from t_order  LEFT JOIN t_order_ext on t_order.order_id = t_order_ext.order_id "
#        }],
#        "index" : "db_order",
#        "type" : "t_order_order_ext",
#        "timezone":"TimeZone.getDefault()",
#        "flush_interval" : "5s"
#    }
#}'

#curl -XDELETE 'esorder.edaijia.cn/_river/db_order/'
#curl -XDELETE 'esorder.edaijia.cn/db_order/'
exit 0
curl -XPUT 'esorder.edaijia.cn/_river/db_order/_meta' -d '{
    "type" : "jdbc",
    "jdbc" : {
        "url" : "jdbc:mysql://10.157.128.16:3306/db_order",
        "user" : "edaijia_order_ro",
        "password" : "EorderDaijia125ro",
        "sql" :[{
            "statement" :"SELECT t_order.* ,t_order.order_id as _id , t_order_ext.driver_ready_time as driver_ready_time from t_order  LEFT JOIN t_order_ext on t_order.order_id = t_order_ext.order_id where t_order.booking_time between 1434988800 and 1435161600"
        }],
        "index" : "db_order",
        "type" : "t_order_order_ext",
        "timezone":"TimeZone.getDefault()",
        "flush_interval" : "5s",
        "index_settings" : {
            "index" : {
                "number_of_shards" : 24,
                "number_of_replicas": 1
            }
        }
    }
}'



#curl 'localhost:9200/_river/jdbc/*/_state?pretty'


#curl -XGET 'localhost:9200/jdbc/jdbc/AU36YZ42Hbeo4csIH9qC'

#curl -XGET 'localhost:9200/jdbc/jdbc/_search'
#curl -XGET 'localhost:9200/jdbc/jdbc/_search?q=id:1'