package com.peaceful.redis;

import java.util.List;

/**
 * Date 14/10/29.
 * Author WangJun
 * Email wangjuntytl@163.com
 * <p/>
 * <h2>redis 集群配置信息</h2>
 * <p>集群分布：集群名字：对应的redis 节点</p>
 */
public class RedisClusterConfig {


    /**
     * cluster name
     */
    private String name;

    /**
     * redis service nodes
     */
    private List<RedisNodeConfig> redisNodeConfigList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RedisNodeConfig> getRedisNodeConfigList() {
        return redisNodeConfigList;
    }

    public void setRedisNodeConfigList(List<RedisNodeConfig> redisNodeConfigList) {
        this.redisNodeConfigList = redisNodeConfigList;
    }

    @Override
    public String toString() {
        return "JedisClusterConfig [name=" + name + ", redisNodeConfigList=" + redisNodeConfigList + "]";
    }

}
