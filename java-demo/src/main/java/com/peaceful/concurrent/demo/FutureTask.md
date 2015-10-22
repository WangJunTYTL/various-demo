# FutureTask

future模式：一种异步计算模式，并支持返回计算结果，在调用get()获取到计算结果前可以阻塞调用者线程

### FutureTask设计原理

FutureTask是JDK针对与future模式的一种实现,它除了支持future特有的特点，还支持task的一些操作，比如取消，打断。
一个FutureTask就是一个任务的计算单元，是调度的最小单位，它的调度借助于JDK的Executor任务调度模型。需要开发人员创建好FutureTask对象后，并送入到Executor去等待调度

具体的执行过程，像下面是一段FutureTask的伪码描述
    
    创建一个futureTask对象task
    提交task到调度器executor等待调度
    
    等待调度中...
    
    如果此时currentThread调取执行结果task.get(),会有几种情况
        
        if task 还没有被executor调度或正在执行中
            阻塞当前线程，并加入到一个阻塞链表中waitNode
        else if task被其它Thread取消，并取消成功 或task处于打断状态
            throw exception
        else if task执行完毕，返回执行结果，或执行存在异常，返回异常信息
            
                
    如果此时有另外一个线程调用task.get()
            
        执行过程同上
           
`注意`:executor在执行FutureTask前，会先判断是否被取消，如果取消就不在执行，但执行后就不可以在取消了            
      
## FutureTask 核心部分代码        

### 在futureTask定义task的转态有：

    private volatile int state;
    private static final int NEW          = 0; // 创建
    private static final int COMPLETING   = 1; // 完成
    private static final int NORMAL       = 2; // 
    private static final int EXCEPTIONAL  = 3; // invoke task 出现异常
    private static final int CANCELLED    = 4; // cancel task 
    private static final int INTERRUPTING = 5; // interrupting task 
    private static final int INTERRUPTED  = 6;


### 创建一个FutureTask

创建futureTask只需要需要一个callable对象或runnable对象的参数，并在创建时设置状态为NEW
    
    public FutureTask(Callable<V> callable) {
            if (callable == null)
                throw new NullPointerException();
            this.callable = callable;
            this.state = NEW;       // ensure visibility of callable
    }


### 调用get()方法获取执行结果方法


    private int awaitDone(boolean timed, long nanos)
            throws InterruptedException {
            final long deadline = timed ? System.nanoTime() + nanos : 0L;
            WaitNode q = null;
            boolean queued = false;
            for (;;) {
                if (Thread.interrupted()) {
                    removeWaiter(q);
                    throw new InterruptedException();
                }
    
                int s = state;
                if (s > COMPLETING) {
                    if (q != null)
                        q.thread = null;
                    return s;
                }
                else if (s == COMPLETING) // cannot time out yet
                    Thread.yield();
                else if (q == null)
                    q = new WaitNode();
                else if (!queued)
                    queued = UNSAFE.compareAndSwapObject(this, waitersOffset,
                                                         q.next = waiters, q);
                else if (timed) {
                    nanos = deadline - System.nanoTime();
                    if (nanos <= 0L) {
                        removeWaiter(q);
                        return state;
                    }
                    LockSupport.parkNanos(this, nanos);
                }
                else
                    LockSupport.park(this);
            }
    }
    
### executor 调度是执行的方法
    
    public void run() {
            if (state != NEW ||
                !UNSAFE.compareAndSwapObject(this, runnerOffset,
                                             null, Thread.currentThread()))
                return;
            try {
                Callable<V> c = callable;
                if (c != null && state == NEW) {
                    V result;
                    boolean ran;
                    try {
                        result = c.call();
                        ran = true;
                    } catch (Throwable ex) {
                        result = null;
                        ran = false;
                        setException(ex);
                    }
                    if (ran)
                        set(result);
                }
            } finally {
                // runner must be non-null until state is settled to
                // prevent concurrent calls to run()
                runner = null;
                // state must be re-read after nulling runner to prevent
                // leaked interrupts
                int s = state;
                if (s >= INTERRUPTING)
                    handlePossibleCancellationInterrupt(s);
            }
        }
                    