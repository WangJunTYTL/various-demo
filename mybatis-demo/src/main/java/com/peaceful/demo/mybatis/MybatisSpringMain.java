package com.peaceful.demo.mybatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by wangjun38 on 2018/5/20.
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
@ImportResource("classpath:/spring-mybatis.xml")
public class MybatisSpringMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MybatisSpringMain.class);
        UserService userService = context.getBean(UserService.class);
        userService.selectById(1);
        userService.selectById(2);
    }
}
