package com.peaceful.demo.spring.service;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by wang on 2017/4/10.
 */
public class CacheService implements Cache {

    private String id;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public CacheService(String id) {
        logger.info("cache service:id->{}",id);
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        logger.info("cache service:put->key:{},value:{}",o,o1);
    }

    @Override
    public Object getObject(Object o) {
        logger.info("cache service:get->key:{}",o);
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        logger.info("cache service:remove->key:{}",o);
        return null;
    }

    @Override
    public void clear() {
        logger.info("cache service:clear");
    }

    @Override
    public int getSize() {
        logger.info("cache service:size");
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
