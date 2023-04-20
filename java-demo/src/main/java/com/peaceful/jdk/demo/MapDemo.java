package com.peaceful.jdk.demo;

import com.peaceful.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/4/11.
 */

public class MapDemo {

    //java map 提供3种collection view , keySet valueSet  entrySet[key-value]
    public static void main(String[] args) {
        Util.report("test mutable key");
        Util.dashed();
        MapKey mapKey1 = new MapKey(1);
        MapKey mapKey2 = new MapKey(2);
        Map<MapKey, String> mapKeyStringMap = new HashMap<MapKey, String>();
        mapKeyStringMap.put(mapKey1, "1");
        mapKeyStringMap.put(mapKey2, "2");
        Util.report(mapKey1 + "\t" + mapKey2);
        Util.report(mapKeyStringMap);
        mapKey2 = mapKey1; // 这个时候mapKey2的引用地址已经变了，但map里面保存的key还是之前的引用对象
        Util.report(mapKey1 + "\t" + mapKey2);
        Util.report(mapKeyStringMap); // map 里面还保存着之前的两次new MapKey() 对象
        Util.report(mapKeyStringMap.get(mapKey2)); // print  1
        Util.enter();

        // entrySet view   a map entry is key-value pair
        Util.report("test entrySet view");
        Util.dashed();
        Set<Map.Entry<MapKey, String>> set = mapKeyStringMap.entrySet();
        for (Map.Entry entry : set) {
            Util.report(entry.getKey() + ":" + entry.getValue());
        }


    }
}
