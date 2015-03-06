package com.peaceful.demo.config;

import com.peaceful.common.util.Util;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 直接解析字符中
 * Created by wangjun on 15/2/8.
 */
public class ParseStringDemo {
    public static void main(String[] args) throws MalformedURLException {

        String str = "{\n" +
                "  \"foo\" : {\n" +
                "    \"bar\" : 42,\n" +
                "    \"baz\" : 43\n" +
                "  }\n" +
                "}";
        Config config = ConfigFactory.parseString(str);
        Util.report(config.getString("foo.bar"));
        Util.report(config.getString("foo.bar"));
        Util.report(config.root().render(ConfigRenderOptions.concise())); // 用简明的方式打印
        Util.report(config.root().render(ConfigRenderOptions.defaults()));
//        config = ConfigFactory.parseFileAnySyntax(new File("app.properties"));
        //从资源文件里载入
        config = ConfigFactory.parseResources("app.properties");
        Util.report(config.getString("redis.pool.maxActive"));
        Util.report(config.root().render(ConfigRenderOptions.concise()));
        //要用绝对路径
        config = ConfigFactory.parseFile(new File("/Users/wangjun/ideaWorkSpace/github/myapp/config-demo/src/main/resources/application.json"));
        Util.report(config.root().render(ConfigRenderOptions.concise()));

        config = ConfigFactory.parseURL(new URL("http", "open.d.api.edaijia.cn", 80, "running"));

        Util.report(config.root().render(ConfigRenderOptions.defaults()));


    }
}
