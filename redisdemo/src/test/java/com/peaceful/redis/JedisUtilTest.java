package com.peaceful.redis;

import com.peaceful.util.Util;
import org.junit.*;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

import static org.junit.Assert.*;

public class JedisUtilTest {
    Jedis jedis = null;

    @Before
    public void setUp() throws Exception {
        jedis = JedisUtil.getJedis();
    }

    @org.junit.Test
    public void test() {
        Util.report(jedis.get("name"));
        Util.report(jedis.ttl("name"));
    }

    @Test
    public void setAndGet() {
        // STRING 操作
        //SET key value将字符串值value关联到key。
        jedis.set("name", "hello");
        //SETEX key seconds value将值value关联到key，并将key的生存时间设为seconds(以秒为单位)。
        jedis.setex("foo", 5, "haha");
        //MSET key value [key value ...]同时设置一个或多个key-value对。
        jedis.mset("haha", "111", "xixi", "222");
        //jedis.flushAll();清空所有的key
        System.out.println(jedis.dbSize());//dbSize是多少个key的个数
        //APPEND key value如果key已经存在并且是一个字符串，APPEND命令将value追加到key原来的值之后。
        Util.report(jedis.append("foo", "00"));//如果key已经存在并且是一个字符串，APPEND命令将value追加到key原来的值之后。
        //GET key 返回key所关联的字符串值
        Util.report(jedis.get("foo"));
        //MGET key [key ...] 返回所有(一个或多个)给定key的值
        List list = jedis.mget("haha", "xixi");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}