package com.peaceful.redis;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.peaceful.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.Set;

/**
 * Date 14/10/29.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public abstract class JedisCache implements Cache {

    private static final Logger LOGGER = LoggerFactory.getLogger(JedisCache.class);

    private Jedis jedis;
    private String namespace;

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Jedis getJedis() {
        return jedis;
    }

    public String getNamespace() {
        return namespace;
    }


    @Override
    public <T> T get(String key, Class<T> requiredType) {
        if (StringUtils.isEmpty(key))
            return null;
        String result = jedis.get(getNamespace() + key);
        if (StringUtils.isNotEmpty(result))
            return JSON.parseObject(result, requiredType);
        else
            return null;
    }

    @Override
    public void put(String key, Object object) {
        Preconditions.checkArgument((StringUtils.isNotEmpty(key) || object == null), "key or value is null");
        String result = JSON.toJSONString(object);
        jedis.set(getNamespace() + key, result);
    }

    @Override
    public void put(String key, Object object, int expire) {
        Preconditions.checkArgument((StringUtils.isNotEmpty(key) || object == null), "key or value is null");
        String result = JSON.toJSONString(object);
        jedis.setex(getNamespace() + key, expire, result);
    }

    @Override
    public void clear(String key) {
        if (StringUtils.isNotEmpty(key)) {
            jedis.del(getNamespace() + key);
        }
    }

    @Override
    public void clearAll() {
        Set<String> keys = jedis.keys(getNamespace() + "*");
        String[] strings = new String[keys.size()];
        jedis.del(keys.toArray(strings));
    }
}
