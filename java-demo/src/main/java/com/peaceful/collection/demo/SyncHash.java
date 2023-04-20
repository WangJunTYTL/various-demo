package com.peaceful.collection.demo;

import com.peaceful.Util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/13
 * @since 1.6
 */

public class SyncHash {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String, String>();
        map = Collections.synchronizedMap(map);
        map.put("A","1");
        map.put("B","2");

        Map<String,String> syncMap = new ConcurrentHashMap<String, String>();
        syncMap.put("A","1");
        syncMap.put("B","2");

        Util.report("线程安全集合：Collections.synchronizedMap" + map);
        Util.report("线程安全集合：ConcurrentHashMap"+syncMap);
    }
}
