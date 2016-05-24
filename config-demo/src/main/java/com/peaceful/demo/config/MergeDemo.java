package com.peaceful.demo.config;

import com.peaceful.common.util.Util;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * @author WangJun
 * @version 1.0 16/5/12
 */
public class MergeDemo {

    public static void main(String[] args) {
        System.setProperty("merge.a","59");
        Config config = ConfigFactory.load("merge.a");
        Config config2 = ConfigFactory.load("merge.b");
        // a覆盖b
        Config mergeConfig = config.withFallback(config2);
        Util.report(mergeConfig.getInt("merge.a"));
        Util.report(mergeConfig.getInt("merge.b"));
    }
}
