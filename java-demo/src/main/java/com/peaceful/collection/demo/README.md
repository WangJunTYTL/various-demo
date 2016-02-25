# Tree

http://blog.csdn.net/bjyfb/article/details/7519360

## List

ArrayList
LinkedList
Vector：利用sync
CopyOnWriteList:get操作不加锁，add操作通过重入锁和数组拷贝完成


## Map

HashTable：线程安全。利用sync关键字
HashMap：
LinkedHashMapC:继承HashMap同时在Entry对象上维护了一条双向链记录插入顺序
TreeMap： 红黑树
ConcurrentHashMap：与HashTable的主要区别：采用重入锁和分段Segment加锁的（segment相当于是一个HashTable，有几段Segment对象集合，
先通过对key进行hash定位是哪一个segment，put操作利用重入锁），get方法不需要加锁，Entry对象的value使用volatile关键字





