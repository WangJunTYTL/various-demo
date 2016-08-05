package com.peaceful.guava.demo;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.Service;

/**
 * Created by wangjun on 16/8/5.
 */
public class ExecutorThreadServiceDemo extends AbstractExecutionThreadService {

    // http://ifeve.com/google-guava-serviceexplained/
    public static void main(String[] args) {
        ExecutorThreadServiceDemo serviceDemo = new ExecutorThreadServiceDemo();
        Service service = serviceDemo.startAsync();
        // serviceDemo.startAsync(); 启动后不可以再次启动
        // service.addListener(); 还可以加入listener 对服务的各个状态进行监听
        System.out.println(service.state());
        System.out.println(service.toString());
    }

    @Override
    protected void run() throws Exception {
        System.out.println("hello world!");
    }
}
