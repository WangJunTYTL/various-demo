package com.peaceful.redis.deploy.proxy;

/**
 * redis所有的节点配置在redisnodes.properties文件
 *
 * Created by wangjun on 15/2/6.
 */
public interface RedisNodeService {

    RedisNode getRedisNode(String name);


}
