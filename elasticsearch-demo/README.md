# elasticsearch

构建与lucene之上的搜索引擎。但隐藏了lucene复杂的api，可以帮助我们快速构建一个搜索引擎。

## 特点

最大的特点就是：`快`。其次自动化的分布式管理，是我见过在分片管理和数据自动备份做的即简单又稳定的一款工具。

## 基本原理



## 交互方式

es的通过提供restful的风格的api进行进行，支持tcp和http两种协议，默认端口分别是9300和9200。所以最简单的交互方式通过curl命令就可以完成。

常用语言像php、java、python都有客户端版本支持

## 入门

1. 下载安装...
1. 集群与节点
1. 面向文档

## 集群工作方式

### 添加索引
### 添加第二个节点
### 故障转移


## 分片

## 文档路由（或分片路由）

## 分析器

确切值和全文文本

## DSL

### Query
 
### Filter 

## 分布式搜索方式

## 结果排序

### 相关性计算

## 深分页问题


## 缺点

1. 耗内存、cpu，如果机器性能达不到那也根本发挥不出来es的快速检索数据的性能
1. 使用场景主要用于数据检索，不太建议干其它的事情


## 倒排索引

分词

倒排索引列表  基于btree索引（ ？）


## 主要问题

1. 和mysql或mongo对比
    和mongo相比：es也可以作为NoSQL风格的分布式文件存储器，我们可以将一个JSON文档扔给Elasticsearch,也可以根据ID检索它们。
    但Elasticsearch真正强大之处在于可以从 混乱的数据中找出有意义的信息——从大数据到全面的信息。
1. 关于相关性
1. 关于排序
1. 获取大量的数据
1. 分词
1. 性能与优化
