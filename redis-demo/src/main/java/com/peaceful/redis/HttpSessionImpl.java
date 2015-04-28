package com.peaceful.redis;

import com.alibaba.fastjson.JSON;
import com.peaceful.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Date 14/10/30.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class HttpSessionImpl implements HttpSession {

    final static private Logger LOGGER = LoggerFactory.getLogger(HttpSessionImpl.class);
    private final static String namespace = "/redis/http/session/";
    private String sessionId;
    private Jedis jedis = JedisUtil.getJedis();
    private int timeout;

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    public static HttpSession getInstance(String sessionId, int timeout) {
        HttpSessionImpl httpSession = SingleHolder.httpSession;
        httpSession.sessionId = sessionId;
        httpSession.timeout = timeout;
        LOGGER.info("success get httpSession,sessionId->{},timeout->{}minutes",sessionId,timeout);
        return httpSession;
    }

    private HttpSessionImpl() {

    }

    private static class SingleHolder {
        static HttpSessionImpl httpSession = new HttpSessionImpl();
    }

    private String decorateKey(String key) {
        return namespace + sessionId + "/" + key;
    }

    @Override
    public <T> T get(String key, Class<T> requiredType) {
        if (StringUtils.isEmpty(key))
            return null;
        String value = jedis.get(decorateKey(key));
        if (StringUtils.isNotEmpty(value)) {
            Set<String> keys = jedis.keys(namespace + sessionId + "*");
            for (String s : keys)
                jedis.expire(s, timeout);
        }
        return JSON.parseObject(value, requiredType);
    }

    @Override
    public void put(String key, Object object) {
        if (StringUtils.isEmpty(key))
            return;
        jedis.setex(decorateKey(key), timeout, JSON.toJSONString(object));
    }


    @Override
    public void clear(String key) {
        jedis.del(decorateKey(key));
    }

    @Override
    public void clearAll() {
        Set<String> keys = jedis.keys(namespace + sessionId + "*");
        String[] strings = new String[keys.size()];
        jedis.del(keys.toArray(strings));
    }
}
