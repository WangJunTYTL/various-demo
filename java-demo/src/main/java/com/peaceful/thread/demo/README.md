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

### 线程状态

在java.lang.Thread.State对象中描述了5种状态：
    
    NEW：创建还没有调用start方法启动
    Block：两个线程同时进入一个sync块，必定有一个线程处于block状态，注意和wait状态的区别
    waiting:调用wait、join、LockSupport.park方法线程处于等待状态
    timed_waiting:调用sleep、wait(long)、join(）...、LockSupport。parkNanos(...)、或LockSupport.parkUntil
    terminated:终止


### interrupt

线程处于sleep转态，可以打断它，让它继续执行

### wait & notify & notifyAll

首先应该注意到wait是Object的方法不是Thread的方法，这些方法都需要获得锁才可以调用，否则包无效的监控对象异常，同一个线程可以多次获得对象的锁
对象调用wait方法前`必须获得该对象的锁`,可以让当前线程让出锁和cpu资源，进入线程阻塞状态
当其它线程获取到该对象的锁，可以调用notify或notifyAll唤醒wait的线程


### join

join是当前线程`等待(在一定时间)指定线程完成`，如果被等待线程完成了，则当前现在才会继续运行，否则阻塞。

### yield
也许该方法永远也不会被调用，没啥作用
使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中




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
    
用来存取Thread运行上下文信息的地方，每个Thread实例都会携带一个map集合，该集合没有被暴漏出来，需要借助ThreadLocal来操作该集合，这是一种利用空间换时间的做法
    
### 使用多线程注意【粒度问题】

    频繁的创建线程和销毁会占用时间
    过多的线程会消耗尽cpu和内存资源
    大量线程的回收会给GC带来压力
    


### 同步

Java并发也是通过锁控制共享的资源.在Java中每个对象都会有有个内部监控锁,这也是wait\notify\notifyall这些方法是object对象所拥有的方法的原因