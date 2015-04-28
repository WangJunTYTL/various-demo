package com.peaceful.redis;

import com.peaceful.common.util.Util;
import org.junit.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import static org.junit.Assert.*;

public class JedisPoolServiceImplTest {

    @org.junit.Test
    public void testGetJedisPoolByName() throws Exception {
        JedisPoolService jedisPoolService = JedisPoolServiceImpl.getJedisPoolService();
        JedisPool jedisPool = jedisPoolService.getJedisPoolByName("test01");
        if (jedisPool != null) {
            Util.report("get suc");
            Jedis jedis = jedisPool.getResource();
            String v = jedisPool.getResource().get("foo");
            Util.report(v);
            jedisPool.returnResource(jedis);
        }
    }
}