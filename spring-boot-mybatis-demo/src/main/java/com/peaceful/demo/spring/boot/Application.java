package com.peaceful.demo.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication //该包下面的所有类都会被扫描
@EnableScheduling // 可以执行定时任务
// 开发指南： http://spring.io/guides
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
