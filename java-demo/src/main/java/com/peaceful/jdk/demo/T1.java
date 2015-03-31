package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/3/29.
 */

public class T1 {

    public static void main(String[] args) throws IOException {
        Enumeration<URL> enumeration = T1.class.getClassLoader().getResources("com/peaceful/jdk/demo/");
//        Enumeration<URL> enumeration = T1.class.getClassLoader().getResources("file:/Users/wangjun/ideaWorkSpace/github/myapp/java-demo/com/peaceful/jdk/demo/");
        while (enumeration.hasMoreElements()){
            Util.report(enumeration.nextElement().getFile());
        }

//        URL url = T1.class.getClassLoader().getResource("file:/Users/wangjun/ideaWorkSpace/github/myapp/java-demo/target/classes/com/peaceful/jdk/demo/T1.class");
//        URL url = T1.class.getClassLoader().getResource("log4j.properties");
        //URL url = T1.class.getClassLoader().getResource("log4j.properties");
        URL url = T1.class.getClassLoader().getResource("com/peaceful/jdk/demo/T1.class");

        Util.report(url);
    }
}
