package com.peaceful.spring.simple.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.engine.DefaultFluentProducerTemplate;

/**
 * Created by wangjun38 on 2020/9/24.
 */
public class TransferInBMP {


    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // 工行流程：结算-》付款--》入账
                from("direct:axh").
                        bean(SettleService.class).
                        bean(PayService.class).
                        bean(AccountService.class);

                // 亿联流程：付款--》入账
                from("direct:dzh").bean(PayService.class).bean(AccountService.class);

                // 新网流程：付款--》申购--》入账
                from("direct:zzh").bean(PayService.class).bean(BuyService.class).bean(AccountService.class);

            }
        });

        camelContext.start();

        Order order = new Order();
        order.setId(1);
        order.setName("Test");
        System.out.println("--->start precess axh order");
        Order response = DefaultFluentProducerTemplate.on(camelContext).to("direct:axh").withHeader("name", "Test").withBody(order).request(Order.class);
        System.out.println("response->" + response);

        System.out.println("--->start precess zzh order");
        response = DefaultFluentProducerTemplate.on(camelContext).to("direct:zzh").withHeader("name", "Test").withBody(order).request(Order.class);
        System.out.println("response->" + response);

        System.out.println("--->start precess dzh order");
        response = DefaultFluentProducerTemplate.on(camelContext).to("direct:dzh").withHeader("name", "Test").withBody(order).request(Order.class);
        System.out.println("response->" + response);

        Thread.sleep(1000);

        // stop the CamelContext
        camelContext.stop();


    }
}
