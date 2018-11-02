package com.peaceful.spring.boot.web.demo;

/**
 * Created by Jun on 2018/5/4.
 */
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Hello world!";
    }

}
