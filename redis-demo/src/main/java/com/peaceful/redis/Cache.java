package com.peaceful.redis;

/**
 * Date 14/10/29.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public interface Cache {

    public  <T> T get(String key,Class<T> requiredType);

    void put(String key, Object object);

    void put(String key, Object object, int expire);

    void clear(String key);

    void clearAll();
}
