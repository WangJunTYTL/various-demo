package com.peaceful.thrift.demo;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;

import java.util.concurrent.TimeUnit;

import service.demo.HelloServer;

/**
 * Created by Jun on 2018-12-02.
 */
public class TClientPool3 {

    public static void main(String[] args) {
        // 生成环境需要使用连接池限制连接个数
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMinIdle(2);
        poolConfig.setMaxIdle(2);
//        poolConfig.setTestOnBorrow(true);
//        poolConfig.setTestOnReturn(true);
//        poolConfig.setMaxWaitMillis(10000);
        GenericObjectPool<HelloServer.Iface> objectPool = new GenericObjectPool<HelloServer.Iface>(new TClientPoolFactory(), poolConfig);

        new Thread(() -> {
            for (; ; ) {
                System.out.println("NumActive：" + objectPool.getNumActive());
                System.out.println("NumIdle：" + objectPool.getNumIdle());
                System.out.println("NumWaiters：" + objectPool.getNumWaiters());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 200; i++) {
//        for (;;) {
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            new Thread(() -> {
                try {
                    HelloServer.Iface helloServer = objectPool.borrowObject();
                    try {
                        StopWatch watch = new Log4JStopWatch("Thrift");
                        watch.start();
                        System.out.println(helloServer.helloString("23"));
//                        System.out.println(helloServer.helloBoolean(true));
                        watch.stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                        objectPool.invalidateObject(helloServer);
                    }
                    objectPool.returnObject(helloServer);
                } catch (Exception e) {


                    e.printStackTrace();

                }
            }).start();
        }
    }
}
