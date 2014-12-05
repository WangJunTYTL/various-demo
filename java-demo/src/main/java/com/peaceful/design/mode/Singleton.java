package com.peaceful.design.mode;

import com.peaceful.common.util.Util;

/**
 * Date 14/10/20.
 * Author WangJun
 * Email wangjuntytl@163.com
 *
 * 单例
 */
public class Singleton {

    private Singleton() {
        Util.report("singleton is creating");
    }

    //使用内部静态类可以保证实例只会存在一个单例，且可以保证延迟加载
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


