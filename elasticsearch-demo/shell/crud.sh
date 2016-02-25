#!/usr/bin/env bash

curl -is '127.0.0.1:9200/test/users' -d '
{
   "name":"Du fu",
   "age": 66,
   "createTime":"2015-01-01"
}'


curl -is '127.0.0.1:9200/test/users/_search?q=du&pretty'


