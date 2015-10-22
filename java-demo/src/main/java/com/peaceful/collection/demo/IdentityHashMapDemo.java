package com.peaceful.collection.demo;

import com.peaceful.common.util.Util;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/24
 * @since 1.6
 */

public class IdentityHashMapDemo {

    public static void main(String[] args) {
        Map<String,String> map = new IdentityHashMap<String, String>();
        map.put("hello","world");
        map.put("hello","world");
        map.put("hello","world");
        map.put("hello","world");
        map.put("hello","world");
        for (;;)
        Util.report(map.get("hello"));//注意get方法判断key的时候是利用==
    }
}
