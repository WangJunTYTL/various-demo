package com.peaceful.spring.simple.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jun on 2018/5/4.
 */
@Configuration
@ComponentScan
public class SimpleApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SimpleApplication.class);
        HelloService helloService = context.getBean(HelloService.class);
        System.out.println(helloService.say());
    }
}
