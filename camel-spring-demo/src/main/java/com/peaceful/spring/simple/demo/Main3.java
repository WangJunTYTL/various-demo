package com.peaceful.spring.simple.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.engine.DefaultFluentProducerTemplate;

/**
 * Created by wangjun38 on 2020/9/22.
 */
public class Main3 {

    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();


        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Send message to the bean endpoint
                // and invoke given method.
//                from("file:data/inbox?noop=true").to("bean:FirstBean");
//                from("file:data/outbox?noop=true").to("bean:SecondBean").to("bean:FirstBean");
                from("direct:start").bean(MyBean3.class);
            }
        });

        camelContext.start();

        Order order = new Order();
        order.setId(1);
        order.setName("Test");
        Order response = DefaultFluentProducerTemplate.on(camelContext).to("direct:start").withHeader("name", "Test").withBody(order).request(Order.class);
        System.out.println("response->" + response);

        Thread.sleep(1000);

        // stop the CamelContext
        camelContext.stop();
    }
}
