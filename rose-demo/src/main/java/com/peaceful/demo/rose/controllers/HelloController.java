package com.peaceful.demo.rose.controllers;

/**
 * Created by wangjun on 14/12/8.
 *
 * rose 默认扫描的controller必须在包controllers下
 */

import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.var.Model;

@Path("/hello/")
public class HelloController {
    @Get("world")
    public String world() {
        return "@hello world，我爱你"; // 支持中文编码
    }

    @Get("{topicId:[0-9]+}") // 这里必须写正则表达式，好麻烦
    public String showTopic(@Param("topicId") int topicId) {
        return "@"+topicId;
    }


    @Get("aa/{topicId:[0-9]+}") // 这里必须写正则表达式，好麻烦
    public String aa(@Param("topicId") int topicId,Model model) {
        model.add("id",topicId);
        return "test";

    }






}

