package com.peaceful.redis;

/**
 * Date 14/10/30.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public interface HttpSession  {

    public  <T> T get(String key,Class<T> requiredType);

    void put(String key, Object object);

    void clear(String key);

    void clearAll();
}
