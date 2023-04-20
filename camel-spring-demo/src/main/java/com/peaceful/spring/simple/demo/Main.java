package com.peaceful.spring.simple.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.engine.DefaultProducerTemplate;

/**
 * Created by wangjun38 on 2020/9/22.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();

        /*camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                *//**
                 file: 表示使用文件Component
                 from 表示从哪里获取数据，进行消费
                 to  表示将数据生产到哪里
                 *//*
                from("file:data/inbox?noop=true").to("file:data/outbox");
            }
        });*/

//        camelContext.getRegistry().bind("FirstBean", new MyBean());
//        camelContext.getRegistry().bind("SecondBean", new MyBean2());

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Send message to the bean endpoint
                // and invoke given method.
//                from("file:data/inbox?noop=true").to("bean:FirstBean");
//                from("file:data/outbox?noop=true").to("bean:SecondBean").to("bean:FirstBean");
                from("file:data/outbox?noop=true").bean(MyBean2.class);
            }
        });

        camelContext.start();


        Thread.sleep(100000);

        // stop the CamelContext
        camelContext.stop();
    }
}
