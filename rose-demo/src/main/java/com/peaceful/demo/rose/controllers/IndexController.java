package com.peaceful.demo.rose.controllers;

/**
 * Created by wangjun on 14/12/8.
 *
 * rose 默认扫描的controller必须在包controllers下
 */

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

@Path("")
public class IndexController {

    @Get("")
    public String index() {
        return "index";
    }

    @Get("test")
    public String test() {
        return "test";
    }
}

