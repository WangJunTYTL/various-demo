package com.peaceful.spring.simple.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by wangjun38 on 2020/9/22.
 */
public class MyBean implements Processor {

    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange);
    }
}
