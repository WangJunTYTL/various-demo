package com.peaceful.spring.simple.demo;

/**
 * Created by wangjun38 on 2020/9/22.
 */
/*
public class MyRouteBuilder extends RouteBuilder {

    */
/**
     * Let's configure the Camel routing rules using Java code...
     *//*

    public void configure() {

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
        from("file:src/data?noop=true")
                .choice()
                .when(xpath("/person/city = 'London'"))
                .to("file:target/messages/uk")
                .otherwise()
                .to("file:target/messages/others");
    }

}*/
