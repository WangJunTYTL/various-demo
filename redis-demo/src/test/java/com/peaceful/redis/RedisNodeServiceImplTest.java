package com.peaceful.redis;

import com.peaceful.common.util.Util;
import org.junit.*;

import static org.junit.Assert.*;

public class RedisNodeServiceImplTest {

    @org.junit.Test
    public void testGetRedisNode() throws Exception {
        RedisNodeService redisNodeService = RedisNodeServiceImpl.getRedisNodeService();
        RedisNode redisNode  = redisNodeService.getRedisNode("default");
        if (redisNode != null)
            Util.report(redisNode.getHost());
    }
}