package com.peaceful.spring.simple.demo;

import org.springframework.stereotype.Component;

/**
 * Created by Jun on 2018/5/4.
 */
@Component
public class HelloService {

    public String say() {
        return "hello world!";
    }

}
