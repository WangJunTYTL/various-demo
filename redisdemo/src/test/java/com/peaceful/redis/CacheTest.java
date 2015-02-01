package com.peaceful.redis;

import com.peaceful.common.util.StringUtils;
import com.peaceful.common.util.Util;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class CacheTest {

    Cache cache = null;

    @Before
    public void setUp() throws Exception {
        cache = JedisCacheManage.getCache("com");
    }

    @org.junit.Test
    public void testGet() throws Exception {
        Util.report(cache.get("hello", String.class));
    }

    @Test
    public void testPut() throws Exception {
        cache.put("hello", "world");
        cache.put("world", "hello");
    }

    @Test
    public void testPut1() throws Exception {

    }

    @Test
    public void testClear() throws Exception {

    }

    @Test
    public void testClearAll() throws Exception {
        cache.clearAll();
    }

    @Test
    public void testAllKeys() throws Exception {
        for (String s : getAllKeys())
            Util.report(s);
    }

    public String[] getAllKeys() {
        Jedis jedis = JedisUtil.getJedis();
        String keys = jedis.get("this_is_all_keys_com");
        if (StringUtils.isNotEmpty(keys))
            return keys.split("#");
        return null;
    }
}