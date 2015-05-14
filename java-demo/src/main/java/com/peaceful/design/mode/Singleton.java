package com.peaceful.design.mode;

import com.peaceful.common.util.Util;

/**
 * Date 14/10/20.
 * Author WangJun
 * Email wangjuntytl@163.com
 * <p/>
 * 单例模式,
 */
public class Singleton {

    private Singleton() {
        Util.report("singleton is creating");
    }

    //使用内部静态类可以保证延迟加载，在类被jvm载入时不会初始化单例类
    private static class SingletonHolder {

        private static Singleton singleton = new Singleton();

    }

    public static Singleton getInstance() {
        return SingletonHolder.singleton;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}


