package com.peaceful.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.peaceful.common.util.Util;

import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * http://hot66hot.iteye.com/blog/2155036
 * https://github.com/WangJunTYTL/Hystrix
 *
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/12
 * @since 1.6
 */

public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
//        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"),6000); // 命令进行分组，可以把同一个服务下的所有命令放在一个组，方便监控、报警
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup")).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("hello world")).andCommandPropertiesDefaults( HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(6000)));
        this.name = name;
    }

    @Override
    protected String run() throws InterruptedException {
        Thread.sleep((long) (Math.random()*2000));
        return "Hello " + name + "!";
    }

    @Override
    protected String getFallback() {
        return "run fail，execute fallback";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (;;){
            long start = System.currentTimeMillis();
            CommandHelloWorld commandHelloWorld = new CommandHelloWorld("Bob");
            String s = commandHelloWorld.execute(); // 阻塞
            System.out.println("execute:" + (System.currentTimeMillis() - start) + "ms");
            Future<String> s2 = new CommandHelloWorld("Jerry").queue(); // 非阻塞
            System.out.println("queue:" + (System.currentTimeMillis() - start) + "ms");
            Util.report(s);
            Util.report(s2.get());
            System.out.println("1th:"+commandHelloWorld.getMetrics().getExecutionTimePercentile(1)+"ms");
            System.out.println("70th:"+commandHelloWorld.getMetrics().getExecutionTimePercentile(70)+"ms");
        }
    }
}