# AQS

AQS:`java.util.concurrent.locks.AbstractQueuedSynchronizer`，它是JUC的locks包很重要的一个类，我们长见到的关于锁的工具类基本都是它提供的，
比如读写锁(ReadWriteLock)、重入锁(ReentrantLock)、信号量(Semaphore)...,如果你了解了AQS，你对这些工具类不用学习也会掌握其原理，同时你也可以根据需要自己实现
自己的锁机制

在了解这个之前，先了解一些必备的前提知识

### 自旋锁



 
 
