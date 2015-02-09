package com.peaceful.demo.config;

import com.peaceful.common.util.Util;
import com.typesafe.config.*;

import java.util.List;
import java.util.Set;

/**
 * Created by wangjun on 15/2/8.
 */
public class SpecifyLocationDemo {

    public static void main(String[] args) {
        Config config = ConfigFactory.load("test");
        String appName = config.getString("app.name");
        Util.report(appName);

        ConfigList configList = config.getList("test03");
        Util.report(configList.size());

        ConfigObject configObject = config.getObject("cluster");
        Util.report(configObject.size());
        Set<String> keys = configObject.keySet();
        for (String key:keys){
            Util.report(key);
            ConfigObject a= (ConfigObject) configObject.get(key);
            Util.report(a.toConfig().root().render());
            Util.report(a.toConfig().getString("ip"));
        }
//        List configList1 = config.getConfigList("cluster");

//        Util.report(configList1.size());
        Config config1 = ConfigFactory.systemEnvironment();
    }
}