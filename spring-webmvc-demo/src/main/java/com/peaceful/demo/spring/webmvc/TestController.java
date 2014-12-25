package com.peaceful.demo.spring.webmvc;

import com.peaceful.common.util.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Date 14/11/1.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
@Controller
public class TestController {

    @RequestMapping({"test",""})
    @ResponseBody
    public String test(){
        Util.report("hello world");
        return "hello world";
    }

    @RequestMapping("jsp")
    public String testJsp(){
        return "test";
    }
    @RequestMapping("ftl")
    public String testFtl(){
        return "test.ftl";
    }

}
