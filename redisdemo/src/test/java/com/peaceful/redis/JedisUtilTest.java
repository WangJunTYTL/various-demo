package com.peaceful.redis;

import com.peaceful.common.util.Util;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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

    @Test
    public void initPoolTest() {
    }

    @Test
    public void transactionTest() {
        Transaction t = jedis.multi();
        t.set("fool", "bar");
        Response<String> result1 = t.get("fool");
        t.zadd("foo", 1, "barowitch");
        t.zadd("foo", 0, "barinsky");
        t.zadd("foo", 0, "barikoviev");
        Response<Set<String>> sose = t.zrange("foo", 0, -1);   // get the entire sortedset
        List<Object> allResults = t.exec();
        Util.report(result1.get());
        Util.report(sose.get().toString());
    }

    @Test
    public void pipelineTest() {
        Pipeline p = jedis.pipelined();
        p.set("fool", "bar");
        p.zadd("foo", 1, "barowitch");
        p.zadd("foo", 0, "barinsky");
        p.zadd("foo", 0, "barikoviev");
        Response<String> pipeString = p.get("fool");
        Response<Set<String>> sose = p.zrange("foo", 0, -1);
        p.sync();
        Util.report(pipeString.get());
        Util.report(sose.get().size());
        Util.report(sose.get());
    }


    //redis 发布订阅
    @Test
    public void subscribeTest() {

        MyListener l = new MyListener();

        jedis.subscribe(l, "foo");
    }

    @Test
    public void publishTest() {
        jedis.publish("foo", "aaaa");
    }

    @Test
    public void fileTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/wangjun/Downloads/FOXUSER.FPT");
        int remain = fileInputStream.available();
        String result = null;
        while (remain > 0) {
            byte[] bytes = new byte[1024];
            fileInputStream.read(bytes);
            result += new String(bytes, CharTest.UTF_8);
            remain = fileInputStream.available();
            Util.report(remain);
        }
        Util.report(result);
    }

    class MyListener extends JedisPubSub {
        public void onMessage(String channel, String message) {
            Util.report("onMessage->：message=" + message + "\tchannel=" + channel);
        }

        public void onPMessage(String pattern, String channel,
                               String message) {
            Util.report("onPSubscribe->pattern=" + pattern + "\tchannel=" + channel + "\tmessage" + message);


        }

        public void onSubscribe(String channel, int subscribedChannels) {
            Util.report("onSubscribe->channel=" + channel + "\tsubscribedChannels=" + subscribedChannels);

        }

        public void onUnsubscribe(String channel, int subscribedChannels) {
            Util.report("onUnsubscribe->channel=" + channel + "\tsubscribedChannels=" + subscribedChannels);

        }

        public void onPSubscribe(String pattern, int subscribedChannels) {
            Util.report("onPSubscribe->pattern=" + pattern + "\tsubscribedChannels=" + subscribedChannels);

        }

        public void onPUnsubscribe(String pattern, int subscribedChannels) {
            Util.report("onPUnsubscribe->pattern=" + pattern + "\tsubscribedChannels=" + subscribedChannels);


        }


    }

}