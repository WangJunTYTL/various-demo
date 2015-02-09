package com.peaceful.redis.deploy.proxy;

import redis.clients.jedis.JedisPool;

/**
 * Created by wangjun on 15/2/6.
 */
public interface JedisPoolService {

    JedisPool getJedisPoolByName(String name);




}
