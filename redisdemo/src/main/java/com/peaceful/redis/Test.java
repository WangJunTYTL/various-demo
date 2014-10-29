package com.peaceful.redis;

import com.peaceful.util.Util;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Date 14/10/23.
 * Author WangJun
 * Email wangjuntytl@163.com
 * <p/>
 * this is a simple test
 */
public class Test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        Util.report(jedis.get("foo"));
    }

    //redis 集群
    public void cluster() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 6379));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        jc.set("foo", "bar");
        Util.report(jc.get("foo"));
    }
}
