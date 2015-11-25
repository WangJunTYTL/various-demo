# Executor 并发调度模型

Executor是一种并发调度模式：它借助于队列和维护着一个用于调度任务的线程池来处理任务的调度。每一个Runnable对象都是一个任务单元，它是可以被Executor调度器调度的最小单元。
当开发者创建一个Runnable对象时，开发者需要把它提交到Executor调度器中，调度器首先会把它送入到任务队列中，等待调度。在线程池里处于空闲的线程就会从队列中获取可调用的任务对象，然后在此线程
中执行任务单元。

## 具体的调度执行过程像下面伪码描述这样

    创建一个Executor
    设置Executor用于存放任务单元的队列
    设置Executor用于执行任务单元的线程池，并初始化一批线程等待任务调度
    
    ...
    
    创建一个Runnable对象，即任务单元
    送入任务单元到Executor调度器中
    Executor收到任务然后把其送入到队列中
    
    ...
    
    线程池扫描到队列中有任务，取出任务，分配一个空闲的线程调用Runnable的run()方法
    任务单元计算完毕，线程重新在线程池中处于空闲对象
    

## 下面是一种简单的实现 

    class SerialExecutor implements Executor {
        
      // 存放runnable对象的队列  
      final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
      final Executor executor;
      Runnable active;
      
      // 实际的调度器
      SerialExecutor(Executor executor) {
        this.executor = executor;
      }
      
      // 开发者调度时送入Runnable到调度器中
      public synchronized void execute(final Runnable r) {
        tasks.offer(new Runnable() {
          public void run() {
            try {
              r.run();
            } finally {
              scheduleNext();
            }
          }
        });
        if (active == null) {
          scheduleNext();
        }
      }
      
      protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
          executor.execute(active);
        }
      }
    }}

## 优点

1. 方面控制并发的数量
1. 不会为每一个线程开辟一个新的线程，避免频繁创建线程的系统开销

## JDK已经实现的Executor调度器

上文已经说过，想要创建一个Executor，要满足两个条件：

1. 需要有一个存储task的队列
2. 需要有一个线程池调度task

这是Executor模型都需要满足的条件。而我们在使用时需要订制的Executor情况可能不同。

假如，你可能在调度任务时，需要支持优先级，那你就需要一个支持优先级的队列

如果你需要一个固定大小的线程池，那你的线程池就需要支持固定大小的线程池

...

最终归结为几种个性化的Executor总类

1.自动优化线程池大小

    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    
2.线程池固定大小

     ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);

## 任务队列




