package com.peaceful.demo.spring.boot.common;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by wangjun on 16/7/23.
 */
//@Component
public class T2 implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.err.println("T2->"+event.toString());
    }
}
