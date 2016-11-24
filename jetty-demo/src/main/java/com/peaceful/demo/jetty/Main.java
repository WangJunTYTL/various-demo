package com.peaceful.demo.jetty;

/**
 * Created by wangjun on 2016/11/12.
 */
public class Main {

    public static void main(String[] args) {
        Hello hello = Proxy.getProxy(Hello.class);
        for (int i =0 ;i<10000;i++)
        System.out.println(hello.say("Good"));
    }
}
