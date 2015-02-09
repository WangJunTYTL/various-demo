package com.peaceful.redis.deploy.proxy;

import com.peaceful.common.util.AppConfigs;
import com.peaceful.common.util.impl.AppConfigsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangjun on 15/2/6.
 */
public class JedisPoolServiceImpl implements JedisPoolService {

    private static final Logger logger = LoggerFactory.getLogger(JedisPoolServiceImpl.class);
    private static final AppConfigs redisPoolConfig = AppConfigsImpl.getMyAppConfigs("jedispool.properties");
    //最大分配的对象数
    public static final String redis_pool_maxActive = redisPoolConfig.getString("redis.pool.maxActive");
    //最大能够保持idel状态的对象数
    public static final int redis_pool_maxIdle = redisPoolConfig.getInt("redis.pool.maxIdle");
    //当池内没有返回对象时，最大等待时间
    public static final int redis_pool_maxWait = redisPoolConfig.getInt("redis.pool.maxWait");
    //当调用borrow Object方法时，是否进行有效性检查
    public static final boolean redis_pool_testOnBorrow = redisPoolConfig.getBoolean("redis.pool.testOnBorrow");
    //当调用return Object方法时，是否进行有效性检查
    public static final boolean redis_pool_testOnReturn = redisPoolConfig.getBoolean("redis.pool.testOnReturn");

    RedisNodeService redisNodeService = RedisNodeServiceImpl.getRedisNodeService();

    private static Map<String, JedisPool> jedisPoolMap = new ConcurrentHashMap<String, JedisPool>();

    private JedisPoolServiceImpl() {
    }

    private static class SingletonHolder {
        private static JedisPoolService jedisPoolService = new JedisPoolServiceImpl();
    }

    public static JedisPoolService getJedisPoolService() {
        return SingletonHolder.jedisPoolService;
    }

    @Override
    public JedisPool getJedisPoolByName(String name) {
        if (jedisPoolMap.containsKey(name))
            return jedisPoolMap.get(name);
        RedisNode redisNode = redisNodeService.getRedisNode(name);
        String redis_ip = redisNode.getHost();
        int redis_port = redisNode.getPort();
        JedisPool pool = null;
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
            jedisPoolMap.put(name, pool);
        } catch (Exception e) {
            logger.error("getPool:{}", e);
        }
        return pool;
    }
}
