#!/usr/bin/env bash

c=0
until [ $c -eq 5 ];
do
curl -is '127.0.0.1:9200/test/users' -d '
{
  "name":"wj",
  "age":28
}'
let c++
done

curl -i '127.0.0.1:9200/test/users' -d '
{
   "name":"Wang Ming",
   "age":28,
   "sex":0,
   "job":"Java Developer",
   "address":"ChaoYang BeiJing"

}'