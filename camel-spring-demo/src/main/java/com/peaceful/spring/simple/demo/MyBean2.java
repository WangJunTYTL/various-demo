package com.peaceful.spring.simple.demo;

import org.apache.camel.Handler;

/**
 * Created by wangjun38 on 2020/9/24.
 */
public class MyBean2 {

    @Handler
    public String say(String msg) {
        System.out.println(this.getClass().getSimpleName() + "->" + msg);
        return msg;
    }

    public Order say2(Order msg) {
        System.out.println(this.getClass().getSimpleName() + "->->" + msg);
        return msg;
    }
}
