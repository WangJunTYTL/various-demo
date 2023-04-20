package com.peaceful.spring.simple.demo;

import org.apache.camel.Handler;

/**
 * Created by wangjun38 on 2020/9/24.
 */
public class MyBean3 {

    @Handler
    public Order say(Order msg) {
        System.out.println(this.getClass().getSimpleName() + "->" + msg);
        return msg;
    }

}
