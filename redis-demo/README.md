redis
=============

key-value 型的内存数据库
学习文档 http://redisdoc.com


## 支持数据结构

1. string   

    foo bar
    
1. hash
     
     order  price $2 type tool
     
1. list 
     
     
     
## 常见用途
     
1. 作为集中式缓存使用
     
1. 作为锁来使用

    我喜欢用getSet命令 getSet 存取一个key-value，并返回old value,如果是第一次存取，便返回null

1. 用作队列


## java语言支持的客户端 

1. jedis  当前使用版本2.6 
1. shardJedis 一种集群算法 ，根据当前的机器节点和key，计算key应该hash到某个节点，考虑如果某个节点宕机的话，应该怎样快速恢复
1. 还见过一种集群方式，利用haproxy和twitter中间件进行集群
1. 集群后应该考虑某些命令不可以使用，比如keys







