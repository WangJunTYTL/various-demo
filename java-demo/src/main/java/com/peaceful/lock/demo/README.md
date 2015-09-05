AQS
----
Java lock的基础类是AQS 即AbstractQueuedSynchronizer。Semaphore、ReentrantLock、ReadWriteLock、CyclicBarrier、CountDownLatch都依赖于它

CAS
-----
无锁算法 compareAndSet，这些实现unSafe类

    public final int incrementAndGet() {
        for (;;) {
            int current = get();
            int next = current + 1;
            if (compareAndSet(current, next)) // 如果内存里还是current值，就设置为next值，如果不是，重新获取current值
                return next;
        }
    }
    
    public final boolean compareAndSet(int expect, int update) {
            return unsafe.compareAndSwapInt(this, valueOffset, expect, update); //这段实现是Native方法，要和具体的CPU架构相关了
    }
    
    首先可以看到他是通过一个无限循环(spin)直到increment成功为止.  
    循环的内容是
    1.取得当前值
    2.计算+1后的值
    3.如果当前值还有效(没有被)的话设置那个+1后的值
    4.如果设置没成功(当前值已经无效了即被别的线程改过了), 再从1开始.


CLH
----
自旋锁



