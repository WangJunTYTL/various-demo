# 批量 FutureTask

在Future模式的讲解中，说到future模式是一种支持异步计算并可以返回计算结果，并在返回结果前可以阻塞调用者的线程对象模型。
批量Future模式是在对Future模式的扩展。比如有，一批FutureTask，我要把这批task打包到一起，等待整个任务包执行完后，在返回给调用者，或在规定时间内返回计算结果。

在Jdk中实现的方法是：


    // 支持超时的批量任务模式
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                      long timeout, TimeUnit unit)
            throws InterruptedException;
            
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException;


## JDK的一种简单实现方式
 
Jdk在实现这种模式时，用了一个ArrayList存放task包，每个task还是单独的送入到调度器中，然后在等待每个task的计算结果，等到所有的task计算完成后在返回包含每个task的计算结果集合，否则阻塞当前调用线程
 
下面是jdk的实现源码： 

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException {
            if (tasks == null)
                throw new NullPointerException();
                
            // 存放task的集合    
            List<Future<T>> futures = new ArrayList<Future<T>>(tasks.size());
            boolean done = false;
            try {
            
                // 每个task都送入到调度器
                for (Callable<T> t : tasks) {
                    RunnableFuture<T> f = newTaskFor(t);
                    futures.add(f);
                    execute(f);
                }
                
                // 等待每一个task返回计算结果
                for (Future<T> f : futures) {
                    if (!f.isDone()) {
                        try {
                            f.get();
                        } catch (CancellationException ignore) {
                            // 如果其中有task被取消了，则忽略
                        } catch (ExecutionException ignore) {
                        }
                    }
                }
                done = true;
                return futures;
            } finally {
                if (!done)
                    for (Future<T> f : futures)
                        f.cancel(true);
            }
        }

