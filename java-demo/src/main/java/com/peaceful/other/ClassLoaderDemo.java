package com.peaceful.other;

import com.google.common.collect.Maps;

import java.util.ArrayList;

/**
 * Created by JunWang on 2016/12/13.
 */
public class ClassLoaderDemo {

    // classLoad ：用于jvm载入类的服务，可以从远程或本地载入二进制类文件
    // 在jvm运行期间会启动多个ClassLoad，之间存在继承关系，构成一颗树的结构，在载入class服务前，都会先检验是否在父class load已经载入
    public static void main(String[] args) {
        // app:  load:jdk 核心api
        System.out.println("String->"+String.class.getClassLoader());
        System.out.println("String->"+ArrayList.class.getClassLoader());

        // extension load:lib/ext


        // app class loader   load:classpath
        System.out.println("String->"+Maps.class.getClassLoader());
        System.out.println("ClassLoaderDemo->"+ClassLoaderDemo.class.getClassLoader());
    }
}
