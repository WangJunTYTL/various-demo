package com.peaceful.spring.boot.web.demo;

/**
 * Created by Jun on 2018/5/4.
 */

import com.peaceful.spring.boot.web.demo.mapper.DemoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private DemoMapper demoMapper;

    @RequestMapping("/index")
    public String index() {
        return "Table user row:<span style='color:red'>"+demoMapper.selectCount()+"</span>";
    }

}
