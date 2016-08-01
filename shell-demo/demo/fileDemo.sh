#!/usr/bin/env bash


# 读取文件
# 也可以利用awk命令
cat ./fileDemo.sh | while read line
do
    echo $line
done


#写入内容
echo "# `date`">file.txt
echo "">>file.txt
echo "hello world">>file.txt

cat file.txt