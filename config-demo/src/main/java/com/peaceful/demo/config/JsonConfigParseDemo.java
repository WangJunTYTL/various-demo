package com.peaceful.demo.config;

import com.peaceful.common.util.Util;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by wangjun on 15/2/8.
 */
public class JsonConfigParseDemo {

    public static void main(String[] args) {
//        The convenience method ConfigFactory.load() loads the following (first-listed are higher priority):
//
//        system properties
//        application.conf (all resources on classpath with this name)
//        application.json (all resources on classpath with this name)
//        application.properties (all resources on classpath with this name)
//        reference.conf (all resources on classpath with this name)
        Config conf = ConfigFactory.load("application.json");
        int bar1 = conf.getInt("foo.bar");
        Util.report(bar1);
        Config foo = conf.getConfig("foo");
        int bar2 = foo.getInt("baz");
        Util.report(bar2);
    }
}
