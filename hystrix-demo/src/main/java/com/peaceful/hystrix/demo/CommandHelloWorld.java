package com.peaceful.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.peaceful.common.util.Util;

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
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        return "Hello " + name + "!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String s = new CommandHelloWorld("Bob").execute();
        Future<String> s2 = new CommandHelloWorld("Bob").queue();
        Util.report(s);
        Util.report(s2.get());
    }
}