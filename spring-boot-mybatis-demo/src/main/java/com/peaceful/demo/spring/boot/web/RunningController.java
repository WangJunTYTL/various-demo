package com.peaceful.demo.spring.boot.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author WangJun
 * @version 1.0 16/6/4
 */
@RestController
public class RunningController {

    private long startDate = System.currentTimeMillis();
    private String appName = "Empty";
    private String state = "green";

    @RequestMapping("/running")
    public String info() {
        JSONObject info  = new JSONObject();
        info.put("appName",appName);
        info.put("startDate",startDate);
        info.put("state",state);
        return info.toJSONString();
    }


    @ResponseBody
    @RequestMapping("test/empty")
    public void responseEmpty() {

    }

    @ResponseBody
    @RequestMapping("test/error")
    public String responseError() {
        int x = 1 / 0;
        return "error";
    }

    @ResponseBody
    @RequestMapping("test/normal")
    public String responseNormal() {
        return "{\"id\":1,\"name\":\"JJ\",\"likes\":[\"PingBang\",\"PlayBox\"]}";
    }
}
