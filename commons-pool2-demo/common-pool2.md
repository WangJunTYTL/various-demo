# common-pool2

对象池技术，主要用于管理对象，做到对象可以重用，避免多次创建对象的开销。

common-pool2是apache开源下的对象池管理组件，是目前Java项目中使用到最广的对象池管理组件

在实际项目中，会有很多地方，常用的tcp连接池，比如db，redis连接的组件都会使用到common-pool2作为连接池管理

common-pool2基本构思思想就是：利用一个集合来管理创建的对象，这个集合就称之为`池`。当需要使用到对象时，从池中拿取一个空闲状态的对象，用完之后在放入到池中。另外可以让使用者个性化的配置池的策略，
比如池对象的大小，空闲对象大小，当池中对象都处于工作状态下对象池的增长策略，当去从对象池中拿取一个对象可以等待的时间等。


## 3种对象池管理方式

1. GenericObjectPool：可以设置池中最大的空闲个数，最大的对象实例化个数
1. GenericKeyedObjectPool：对于相同的key的对象池提供同样的池策略
1. SoftReferenceObjectPool：基于SoftReference实现，可以随着需要自动增加对象的个数，而对象的回收利用gc


## GenericObjectPool

GenericObjectPool池利用LinkedBlockingDeque作为对象管理的容器和一个名为`idle object eviction`的驱逐线程定时查看空闲的对象，来保证空闲的对象不会超过用户的设置。








