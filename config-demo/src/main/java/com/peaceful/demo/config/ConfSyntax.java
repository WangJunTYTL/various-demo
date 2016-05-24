package com.peaceful.demo.config;

import com.peaceful.common.util.Util;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * @author WangJun
 * @version 1.0 16/5/23
 */
public class ConfSyntax {

    public static void main(String[] args) {
        Config config = ConfigFactory.load("conf.a");

        Util.report(config.getIntList("b"));
        Util.report(config.getString("c"));
        Util.report(config.getString("d"));
    }
}
