package com.peaceful.spring.boot.web.demo;

/**
 * https://spring.io/guides/gs/spring-boot/
 *
 * Created by Jun on 2018/5/4.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/spring/applicationDataSource.xml")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
