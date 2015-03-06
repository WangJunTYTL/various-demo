package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangjun on 15/2/23.
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap();
        //当没有值时设置
        String a = concurrentHashMap.putIfAbsent("123", "12");
        Util.report(a);
        Util.report(concurrentHashMap.get("123"));
        a = concurrentHashMap.putIfAbsent("123", "123");
        Util.report(a);
        Util.report(concurrentHashMap.get("123"));
        a = concurrentHashMap.putIfAbsent("123", "1234");
        Util.report(a);
        Util.report(concurrentHashMap.get("123"));

    }
}
