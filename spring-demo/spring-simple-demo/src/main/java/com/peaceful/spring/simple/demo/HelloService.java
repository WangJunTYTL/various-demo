package com.peaceful.spring.simple.demo;

import org.springframework.stereotype.Service;

/**
 * Created by wangjun38 on 2018/5/4.
 */
@Service
public class HelloService {

    public String say() {
        return "hello world!";
    }

}
