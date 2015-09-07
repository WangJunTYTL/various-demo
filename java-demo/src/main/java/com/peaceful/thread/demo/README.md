线程
-----------

线程是操作系统可以进行运算调度的最小单位，它被包含在进程当中，是进程中的实际运算单位。我们可以通过它进行多处理器编程，可以使用`多线程对运算密集型任务提速`

### 线程和进程区别

进程是具有一定独立功能的程序关于某个数据集合上的一次运行活动,进程是`系统进行资源分配和调度的一个独立单位`.

线程是进程的一个实体,是CPU调度和分派的基本单位,它是比进程更小的能独立运行的基本单位.线程自己基本上不拥有系统资源,只拥有一点在运行中必不可少的资源(如程序计数器,一组寄存器和栈),但是它可与同属一个进程的其他的线程共享进程所拥有的全部资源.

进程和线程的主要差别在于它们是不同的操作系统资源管理方式。进程有独立的地址空间，一个进程崩溃后，在保护模式下不会对其它进程产生影响，而线程只是一个进程中的不同执行路径。线程有自己的堆栈和局部变量，但线程之间没有单独的地址空间，一个线程死掉就等于整个进程死掉，所以多进程的程序要比多线程的程序健壮，但在进程切换时，耗费资源较大，效率要差一些。但对于一些要求同时进行并且又要共享某些变量的并发操作，只能用线程，不能用进程。

### 主要线程操作原语

    Object
        wait
        notify
        notifyAll

    Thread
        start
        sleep
        interrupt
        join
        yield
        currentThread
    
    LockSupport
        park
        unpark
    
    已过时
    suspend
    resume
    stop


### sleep & wait 的区别

sleep()方法，我们首先要知道该方法是属于Thread类中的。`而wait()方法，则是属于Object类中的`。
sleep 让出cpu资源不会让出lock，监控状态依然保持者
wait 让出cpu资源也会让出lock，而当调用wait()方法的时候，线程会放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象调用notify()方法后本线程才进入对象锁定池准备

### interrupt

线程处于sleep转态，可以打断它，让它继续执行

### wait & notify & notifyAll

首先应该注意到wait时Object的方法不是Thread的方法
对象调用wait方法前`必须获得该对象的锁`,可以让当前线程让出锁和cpu资源，进入线程阻塞状态
当其它线程获取到该对象的锁，可以调用notify或notifyAll唤醒wait的线程

### LockSupport

用于替代:Thread.suspend() Thread.resume()

    park(): void        阻塞当前线程
    park(Object): void  
    parkNanos(long): void
    parkNanos(Object, long): void
    parkUntil(long): void
    parkUntil(Object, long): void
    setBlocker(Thread, Object): void
    unpark(Thread): void
    
### ThreadLocal    
    
### 使用多线程注意【粒度问题】

    频繁的创建线程和销毁会占用时间
    过多的线程会消耗尽cpu和内存资源
    大量线程的回收会给GC带来压力
    
    