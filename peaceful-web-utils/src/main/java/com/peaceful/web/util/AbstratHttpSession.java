package com.peaceful.web.util;

import com.alibaba.fastjson.JSON;
import com.hehua.framework.cache.AbstractRedisCache;
import com.hehua.framework.jedis.PoolableJedis;
import com.hehua.framework.jedis.PoolableJedisManager;

import java.util.concurrent.TimeUnit;

/**
 * Date 14/10/22.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public  abstract class AbstratHttpSession extends AbstractRedisCache {


    public AbstratHttpSession() {
        super(PoolableJedisManager.getDefaultCacheJedis());
    }


    public String encode(String object) {
        return JSON.toJSONString(object);
    }

    public String decode(String object) {
        return JSON.parseObject(object, String.class);
    }

    public String buildKey(String key) {
        return "hehua_session:" + key;
    }

    public int getExpire() {
        return (int) TimeUnit.SECONDS.toSeconds(6);
    }

}
