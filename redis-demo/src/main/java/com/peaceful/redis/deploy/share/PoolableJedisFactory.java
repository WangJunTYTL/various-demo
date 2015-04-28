/**
 *
 */
package com.peaceful.redis.deploy.share;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * jedis 集群管理Factory
 * <p/>
 * Created by wangjun on 15/1/22.
 */
public class PoolableJedisFactory {

    private static final PoolableJedisFactory instance = new PoolableJedisFactory();

    private PoolableJedisFactory() {
    }

    public static PoolableJedisFactory getInstance() {
        return instance;
    }

    private static final int POOL_TOTAL_MAX_COUNT = 888;
    private static final int POOL_MAX_COUNT = 8;
    private static final Logger logger = LoggerFactory.getLogger(PoolableJedisFactory.class);



    public PoolableJedis get(String redisClusterConfigName) {
        JedisClusterConfigNode config = JedisClusterConfigCenter.getClusterConfig(redisClusterConfigName);
        if (config == null) {
            throw new RuntimeException("JedisClusterConfigNode: " + redisClusterConfigName + " is not exist");
        }
        ShardedJedisPool shardedJedisPool = appendToPool(config);
        return new PoolableJedis(shardedJedisPool);
    }

    /**
     * 初始化redis 集群
     *
     * @param config
     * @return
     */
    private ShardedJedisPool appendToPool(JedisClusterConfigNode config) {
        try {
            //每个redis节点池配置
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setTestWhileIdle(true);
            //最大连接数, 默认888个
            poolConfig.setMaxTotal(POOL_TOTAL_MAX_COUNT);
            //最大空闲连接数, 默认8个
            poolConfig.setMaxIdle(POOL_MAX_COUNT);
            //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
            poolConfig.setBlockWhenExhausted(true);
            //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
            poolConfig.setMaxWaitMillis(3000);
            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(config.getNodes().size());
            for (HostAndPort nodeConfig : config.getNodes()) {
                JedisShardInfo jsi = new JedisShardInfo(nodeConfig.getHost(), nodeConfig.getPort(),
                        0, nodeConfig.getHost() + ":" + nodeConfig.getPort());
                shards.add(jsi);
                jsi.setTimeout(3000); //超时
//                jsi.setPassword("k74FkBwb7252FsbNk2M7");
            }
            return new ShardedJedisPool(poolConfig, shards);
        } catch (Throwable e) {
            logger.error("Ops.", e);
            throw new RuntimeException(e);
        }
    }
}