#!/usr/bin/env bash
HOST="localhost"
PORT=3306
USER=""
PASSWORD=""

DB="test"

query="select * from t_order where order_id='-id'"
# 变量一般采用字符串的方式替换
query=${query//'-id'/10000}
echo "Query sql: ${query}"
mysql -h${HOST} -P${PORT} -u${USER} -p${PASSWORD} -D${DB} -e "$query"

