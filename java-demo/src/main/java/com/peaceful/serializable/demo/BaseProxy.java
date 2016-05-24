package com.peaceful.serializable.demo;


/**
 * @author WangJun
 * @version 1.0 16/4/15
 */
public interface BaseProxy {


    public <T> T getInstance(Class<T> zClass);
}
