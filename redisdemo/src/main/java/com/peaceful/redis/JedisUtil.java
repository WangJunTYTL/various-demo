package com.peaceful.redis;

import com.peaceful.common.util.AppConfigs;
import com.peaceful.common.util.impl.AppConfigsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.*;

/**
 * Date 14/10/23.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class JedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(JedisUtil.class);
    private static final AppConfigs redisConfig = AppConfigsImpl.getMyAppConfigs("app.properties");
    //最大分配的对象数
    public static final String redis_pool_maxActive = redisConfig.getString("redis.pool.maxActive");
    //最大能够保持idel状态的对象数
    public static final int redis_pool_maxIdle = redisConfig.getInt("redis.pool.maxIdle");
    //当池内没有返回对象时，最大等待时间
    public static final int redis_pool_maxWait = redisConfig.getInt("redis.pool.maxWait");
    //当调用borrow Object方法时，是否进行有效性检查
    public static final boolean redis_pool_testOnBorrow = redisConfig.getBoolean("redis.pool.testOnBorrow");
    //当调用return Object方法时，是否进行有效性检查
    public static final boolean redis_pool_testOnReturn = redisConfig.getBoolean("redis.pool.testOnReturn");
    //IP
    public static final String redis_ip = redisConfig.getString("redis.ip");
    //    Port
    public static final int redis_port = redisConfig.getInt("redis.port");

    /**
     * 私有构造器.
     */
    private JedisUtil() {

    }




    private static Map<String, JedisPool> maps = new HashMap<String, JedisPool>();

    /**
     * 获取连接池.
     *
     * @return 连接池实例
     */
    private static JedisPool getPool() {
        String key = redis_ip + ":" + redis_port;
        JedisPool pool = null;
        if (!maps.containsKey(key)) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(redis_pool_maxIdle);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            try {
                /**
                 *如果你遇到 java.net.SocketTimeoutException: Read timed out exception的异常信息
                 *请尝试在构造JedisPool的时候设置自己的超时值. JedisPool默认的超时时间是2秒(单位毫秒)
                 */
                pool = new JedisPool(config, redis_ip, redis_port, redis_pool_maxWait);
                maps.put(key, pool);
            } catch (Exception e) {
                logger.error("getPo0l:{}", e);
            }
        } else {
            pool = maps.get(key);
        }
        return pool;
    }

    private static ShardedJedisPool getShardedJedisPool(RedisClusterConfig redisClusterConfig) {
        try {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setTestWhileIdle(true);
            poolConfig.setMaxTotal(500);
            poolConfig.setMaxIdle(500);
            poolConfig.setBlockWhenExhausted(true);
            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(redisClusterConfig.getRedisNodeConfigList().size());
            for (RedisNodeConfig nodeConfig : redisClusterConfig.getRedisNodeConfigList()) {
                JedisShardInfo jsi = new JedisShardInfo(nodeConfig.getHost(), nodeConfig.getPort(),
                        0, nodeConfig.getHost() + ":" + nodeConfig.getPort());
                shards.add(jsi);
            }
            return new ShardedJedisPool(poolConfig, shards);
        } catch (Throwable e) {
            logger.error("getShardedJedisPool:{}", e);
            throw new RuntimeException(e);
        }
    }


    //jedis 集群
    public ShardedJedis createShardJedis(String name) {
        RedisClusterConfig config = null;
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(config.getRedisNodeConfigList().size());
        for (RedisNodeConfig nodeConfig : config.getRedisNodeConfigList()) {
            JedisShardInfo jsi = new JedisShardInfo(nodeConfig.getHost(), nodeConfig.getPort(), 0,
                    nodeConfig.getHost() + ":" + nodeConfig.getPort());
            shards.add(jsi);
        }
        return new ShardedJedis(shards);
    }

    public static Jedis getJedis() {
        return getPool().getResource();
    }

    //jedis官网用例
    public void clusterTest() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7379));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        jc.set("foo", "bar");
        String value = jc.get("foo");
    }




    //如果用到生成环境，不要直接使用单例jedis，A single Jedis instance is not threadsafe!
    public void jedisPoolTest(){
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");

    }



}
