package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

import java.io.Console;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wangjun on 15/3/28.
 */
public class SystemTest {

    public static void main(String[] args) {
        Map env = System.getenv();
        Util.report(env);
        Properties properties = System.getProperties();
        Util.report(properties);
        Util.report("os.name:"+properties.get("os.name"));

//        Console console = System.console();

//        System.gc();

    }
}
