package com.peaceful.spring.simple.demo;

import org.apache.camel.Handler;

/**
 * Created by wangjun38 on 2020/9/24.
 */
public class PayService {

    @Handler
    public Order precess(Order order) {
        System.out.println("start precess order:->"+"pay...");
        return order;
    }
}
