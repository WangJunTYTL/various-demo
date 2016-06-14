package com.peaceful.demo.spring.boot.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author WangJun
 * @version 1.0 16/6/4
 */
@Component
// 实现该方法会在启动时执行下面方法
public class AppPreStarted implements ApplicationRunner {

    // 直接注入spring的配置文件值
    @Value("${my.secret}")
    private String secret;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("MyApp->" + applicationArguments.toString());
        System.out.println("my.secret->" + secret);
    }
}
