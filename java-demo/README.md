java 笔记

{
注解：利用反射拿到放在注解值，主要用于配置，利用注解可以取消xml的配置
}

{
动态代理：使用动态代理可以生成class文件，常见动态代理实现：Cglib
}

### thread
线程是一个程序可以执行的线程，jvm 允许有多个线程并发允许
每个线程都有一个优先级，高优先级的线程在执行上优于低优先级的线程，每个线程都可以标记为守护线程仅当创建它的线程是daemon线程。线程的初始优先级和创建它的线程优先级一样。
当jvm启动时，通常会创建一个单一的non-daemon线程（通常method被叫做main），jvm会一直运行直到下面的任何一种情况发生：

    <ul>
     * <li>The <code>exit</code> method of class <code>Runtime</code> has been
     *     called and the security manager has permitted the exit operation
     *     to take place.
     * <li>All threads that are not daemon threads have died, either by
     *     returning from the call to the <code>run</code> method or by
     *     throwing an exception that propagates beyond the <code>run</code>
     *     method.
     * </ul>


### 并发
线程个数的考虑

    频繁的创建线程和销毁会占用时间
    过多的线程会消耗尽cpu和内存资源
    大量线程的回收会给GC带来压力

### Executor 框架
ExecutorService exe = Executors.newCachedThreadPool()
任务先进入队列 由几个线程去消费


### classloader
载入定义class结构需要的数据，根据一个二进制的name搜索class或resource
classloader通过委托模式搜索class或resource，它先自己尝试去搜索，如果没有找到或委托给父classloader搜索
jvm内建classloader叫bootstrap class loader ，它没有父classloader，单可以作为其它classloader的父classloader
不同的classloader加载的类不能相互cast，即使加载的是一个相同的class


### 模式设计

1. 单例模式
1. 代理模式















