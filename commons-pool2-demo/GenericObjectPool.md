## common-pool2 使用

common-pool2提供了3中对象池管理方式，它们的使用方式基本，这里以GenericObjectPool对象池为例介绍其使用方式，一般实现自己的对象池需要经过2个步骤

1. 实现PooledObjectFactory接口:该接口是一种工厂模式，实现其目的是让对象池通过该工厂模式创建管理的对象
1. 创建对象池(GenericObjectPool(PooledObjectFactory))实例


### 创建Conn对象池

我们假设Conn对象是一个建立TCP连接的对象，该对象的初始化时间平均为500ms，为了避免在程序中频繁创建Conn对象，我们需要借助对象池管理Conn对象实例
    
    import org.slf4j.LoggerFactory;
    
    /**
     * common-pool2 使用方式
     * <p/>
     * 假设这是一个建立TCP连接的对象，该对象的初始化时间平均为500ms，为了避免在程序中频繁创建Conn对象，我们需要借助对象池管理Conn对象实例
     *
     * @author WangJun <wangjuntytl@163.com>
     * @version 1.0 15/10/28
     * @since 1.6
     */
    
    public class Conn {
    
        /**
         * 记录对象的创建时间
         */
        private long createTime;
    
    
        /**
         * 初始化Conn对象，模拟创建Conn对象平均消耗500ms
         * @throws InterruptedException
         */
        public Conn() throws InterruptedException {
            Thread.sleep(500);
            createTime = System.currentTimeMillis();
            LoggerFactory.getLogger(getClass()).debug(" init conn suc... " + createTime);
        }
    
        /**
         * 报告Conn对象信息
         */
        public void report() {
            LoggerFactory.getLogger(getClass()).info("this is a available conn " + createTime);
        }
    }

### 利用工厂模式，使对象池通过该工厂模式创建管理的对象    
    
    package com.peaceful.pool.demo;
    
    import org.apache.commons.pool2.BasePooledObjectFactory;
    import org.apache.commons.pool2.PooledObject;
    import org.apache.commons.pool2.PooledObjectFactory;
    import org.apache.commons.pool2.impl.DefaultPooledObject;
    
    /**
     * common-pool2 使用方式
     * <p/>
     * 为了使用common-pool2对象池管理，我们必须实现{@link org.apache.commons.pool2.PooledObjectFactory}或者其子类
     * 这是一个工厂模式，告诉对象池怎样去创建要管理的对象
     * <p/>
     * BasePooledObjectFactory 是对{@link org.apache.commons.pool2.PooledObjectFactory}的一个基本实现，我们可以继承该类，减少一些方法的实现
     * <p/>
     * 在实现{@link org.apache.commons.pool2.PooledObjectFactory}接口时，我们一定要实现的接口方法是{@link PooledObjectFactory#makeObject()}方法。
     *
     * @author WangJun <wangjuntytl@163.com>
     * @version 1.0 15/10/28
     * @since 1.6
     */
    
    public class ConnFactory extends BasePooledObjectFactory<Conn> {
    
    
        /**
         * 间接实现{@link PooledObjectFactory#makeObject()}方法，表明怎样创建需要管理对象
         */
        @Override
        public Conn create() throws Exception {
            return new Conn();
        }
    
    
        /**
         * 在common-pool2中为了统计管理的对象的一些信息，比如调用次数，空闲时间，上次使用时间等，需要对管理的对象进行包装，然后在放入到对象池中
         *
         * @param obj 对象池要管理的对象
         * @return 返回包装后的PooledObject对象
         */
        @Override
        public PooledObject<Conn> wrap(Conn obj) {
            return new DefaultPooledObject<Conn>(obj);
        }
    
    }


### 为了模拟的更真实，ConnPool池可以让使用者个性化配置
    
    package com.peaceful.pool.demo;
    
    import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
    
    /**
     * common-pool2 使用方式
     * <p/>
     * {@link org.apache.commons.pool2.impl.GenericObjectPool}支持个性化配置，我们可以配置对象池中总共的对象数，最大、最小空闲对象数等等
     * 这边继承{@link GenericObjectPoolConfig}是为了ConnPool也可以进行个性化的配置
     *
     * @author WangJun <wangjuntytl@163.com>
     * @version 1.0 15/10/28
     * @since 1.6
     */
    
    public class ConnPoolConfig extends GenericObjectPoolConfig {
    
        public ConnPoolConfig() {
            // defaults to make your life with connection pool easier :)
            setMinIdle(5);
            setTestOnBorrow(true);
        }
    
    }
    

### 有了创建对象的工厂，我们就可以创建一个对象池实例
    

    package com.peaceful.pool.demo;
    
    import org.apache.commons.pool2.impl.GenericObjectPool;
    
    /**
     * common-pool2 使用方式
     * <p/>
     * Conn对象管理池，这里利用GenericObjectPool作为对象池
     *
     * @author WangJun <wangjuntytl@163.com>
     * @version 1.0 15/10/28
     * @since 1.6
     */
    
    public class ConnPool extends GenericObjectPool<Conn> {
    
    
        /**
         * 调用{@link GenericObjectPool}的构造方法，构造ConnPool
         */
        public ConnPool() {
            super(new ConnFactory(), new ConnPoolConfig());
        }
    
        /**
         * 调用{@link GenericObjectPool}的构造方法，构造ConnPool
         */
        public ConnPool(ConnPoolConfig connPoolConfig) {
            super(new ConnFactory(), connPoolConfig);
        }
    
    }

### 这样一个就完成了整个ConnPool的编码，下面我们在写一个demo，演示使用ConnPool
    
    public class ConnDemo {
    
        public static void main(String[] args) throws Exception {
            ConnPoolConfig connPoolConfig = new ConnPoolConfig();
            connPoolConfig.setMinIdle(5);
            connPoolConfig.setMaxIdle(8);
            ConnPool connPool = new ConnPool(connPoolConfig);
            Conn conn1 = connPool.borrowObject();
            Conn conn2 = connPool.borrowObject();
            Conn conn3 = connPool.borrowObject();
            Conn conn4 = connPool.borrowObject();
            Conn conn5 = connPool.borrowObject();
            conn1.report();
            connPool.returnObject(conn1);
            conn2.report();
            connPool.returnObject(conn2);
            conn3.report();
            connPool.returnObject(conn3);
            conn4.report();
            connPool.returnObject(conn4);
            conn5.report();
            connPool.returnObject(conn5);
    
            conn5.report();
    
            // 被归还的对象的引用，不可以在次归还
            // java.lang.IllegalStateException: Object has already been retured to this pool or is invalid
            try {
                connPool.returnObject(conn5);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
### 下面是ConnDemo的运行结果
    
    [2015-10-28 14:56:06 DEBUG]  {com.peaceful.pool.demo.Conn:18}- init conn suc...
    [2015-10-28 14:56:07 DEBUG]  {com.peaceful.pool.demo.Conn:18}- init conn suc...
    [2015-10-28 14:56:07 DEBUG]  {com.peaceful.pool.demo.Conn:18}- init conn suc...
    [2015-10-28 14:56:08 DEBUG]  {com.peaceful.pool.demo.Conn:18}- init conn suc...
    [2015-10-28 14:56:08 DEBUG]  {com.peaceful.pool.demo.Conn:18}- init conn suc...
    [2015-10-28 14:56:08 INFO ]  {com.peaceful.pool.demo.Conn:22}-this is a available conn 1446015366746
    [2015-10-28 14:56:08 INFO ]  {com.peaceful.pool.demo.Conn:22}-this is a available conn 1446015367346
    [2015-10-28 14:56:08 INFO ]  {com.peaceful.pool.demo.Conn:22}-this is a available conn 1446015367853
    [2015-10-28 14:56:08 INFO ]  {com.peaceful.pool.demo.Conn:22}-this is a available conn 1446015368354
    [2015-10-28 14:56:08 INFO ]  {com.peaceful.pool.demo.Conn:22}-this is a available conn 1446015368860
    [2015-10-28 14:56:08 INFO ]  {com.peaceful.pool.demo.Conn:22}-this is a available conn 1446015368860
    java.lang.IllegalStateException: Object has already been retured to this pool or is invalid
    	at org.apache.commons.pool2.impl.GenericObjectPool.returnObject(GenericObjectPool.java:595)
    	at com.peaceful.pool.demo.ConnDemo.main(ConnDemo.java:37)
    	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
    	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    	at java.lang.reflect.Method.invoke(Method.java:606)
    	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:140)


    
    
