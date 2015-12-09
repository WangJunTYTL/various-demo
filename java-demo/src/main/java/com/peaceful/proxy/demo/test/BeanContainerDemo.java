package com.peaceful.proxy.demo.test;

import com.peaceful.proxy.demo.Say;
import com.peaceful.proxy.demo.Say2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:wangjuntytl@163.com">wangjun</a>
 * @version 1.0 15/12/5
 */
public class BeanContainerDemo {

    private static Map<Class, Object> cache = new ConcurrentHashMap<Class, Object>();

    public static <T> T getBean(Class clazz) {

        if (cache.containsKey(clazz)) {
            return (T) cache.get(clazz);
        } else if (clazz.equals(Say.class)) {
            cache.put(Say.class,new Say2());
            return (T) cache.get(Say.class);
        }
        return null;
    }
}
