/**
 *
 */
package com.peaceful.redis.deploy.share;

import com.google.common.collect.Iterables;
import com.peaceful.common.util.CollectionUtils;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.*;

import java.util.*;
import java.util.Map.Entry;

/**
 * <h1> jedis 集群管理与连接池管理服务</h1>
 * <p>
 * 对于使用redis服务开发人员采用透明机制，开发人员不需要关心使用的redis服务位于redis集群的那个节点
 * 服务将会保证根据一个key-value对象的key和当前集群节点的数量hash到某个集群节点，并且各个节点被命中的概率相同，
 * 不要试图去找位于哪个节点，除非你知道所有的节点位置，并依次寻找
 * <p/>
 * <b>如果服务已启动，不要随意增添删除节点，这将影响到当前服务中已存在key的hash值</b>
 * </p>
 * <a href="mailto:wangjuntytl@163.com">邮件联系</a>
 * Created by wangjun on 15/1/22.
 */
public class PoolableJedis implements JedisCommands {

    private ShardedJedisPool pool;

    /**
     * @param pool
     */
    public PoolableJedis(ShardedJedisPool pool) {
        super();
        this.pool = pool;
    }

    private void returnResource(ShardedJedis resource, boolean success) {
        if (success) {
            if (resource != null) {
                pool.returnResourceObject(resource);
            }
        } else {
            if (resource != null) {
                pool.returnBrokenResource(resource);
            }
        }
    }

    @Override
    public String set(String key, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.set(key, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String set(String key, String value, String nxxx, String expx, long time) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.set(key, value, nxxx, expx, time);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String get(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.get(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Boolean exists(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Boolean result = resource.exists(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long persist(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.persist(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String type(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.type(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.expire(key, seconds);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.expireAt(key, unixTime);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long ttl(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.ttl(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Boolean result = resource.setbit(key, offset, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Boolean result = resource.setbit(key, offset, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Boolean getbit(String key, long offset) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Boolean result = resource.getbit(key, offset);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.setrange(key, offset, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.getrange(key, startOffset, endOffset);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String getSet(String key, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.getSet(key, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long setnx(String key, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.setnx(key, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String setex(String key, int seconds, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.setex(key, seconds, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long decrBy(String key, long integer) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.decrBy(key, integer);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long decr(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.decr(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long incrBy(String key, long integer) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.incrBy(key, integer);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long incr(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.incr(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long append(String key, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.append(key, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String substr(String key, int start, int end) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.substr(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long hset(String key, String field, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.hset(key, field, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String hget(String key, String field) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.set(key, field);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.hsetnx(key, field, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.hmset(key, hash);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            List<String> result = resource.hmget(key, fields);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.hincrBy(key, field, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Boolean hexists(String key, String field) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Boolean result = resource.hexists(key, field);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long hdel(String key, String... field) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.hdel(key, field);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long hlen(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.hlen(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> hkeys(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.hkeys(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public List<String> hvals(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            List<String> result = resource.hvals(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Map<String, String> result = resource.hgetAll(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long rpush(String key, String... string) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.rpush(key, string);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long lpush(String key, String... string) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.lpush(key, string);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long llen(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.llen(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            List<String> result = resource.lrange(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String ltrim(String key, long start, long end) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.ltrim(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String lindex(String key, long index) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.lindex(key, index);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String lset(String key, long index, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.lset(key, index, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long lrem(String key, long count, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.lrem(key, count, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String lpop(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.lpop(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String rpop(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.rpop(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long sadd(String key, String... member) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.sadd(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> smembers(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.smembers(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long srem(String key, String... member) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.srem(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String spop(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.spop(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long scard(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.scard(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Boolean sismember(String key, String member) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Boolean result = resource.sismember(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String srandmember(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.srandmember(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public List<String> srandmember(String s, int i) {
        return null;
    }

    @Override
    public Long strlen(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.strlen(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zadd(String key, double score, String member) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.zadd(key, score, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.zadd(key, scoreMembers);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.zrange(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zrem(String key, String... member) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.zrem(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Double result = resource.zincrby(key, score, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zrank(String key, String member) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.zrank(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zrevrank(String key, String member) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.zrevrank(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.zrevrange(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<Tuple> result = resource.zrangeWithScores(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<Tuple> result = resource.zrevrangeWithScores(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zcard(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.zcard(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Double zscore(String key, String member) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Double result = resource.zscore(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public List<String> sort(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            List<String> result = resource.sort(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            List<String> result = resource.sort(key, sortingParameters);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zcount(String key, double min, double max) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.zcount(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zcount(String key, String min, String max) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.zcount(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.zrangeByScore(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.zrangeByScore(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.zrevrangeByScore(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.zrangeByScore(key, min, max, offset, count);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.zrevrangeByScore(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.zrangeByScore(key, min, max, offset, count);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.zrevrangeByScore(key, min, max, offset, count);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<Tuple> result = resource.zrangeByScoreWithScores(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<Tuple> result = resource.zrevrangeByScoreWithScores(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset,
                                              int count) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<Tuple> result = resource.zrangeByScoreWithScores(key, min, max, offset, count);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<String> result = resource.zrevrangeByScore(key, min, max, offset, count);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<Tuple> result = resource.zrangeByScoreWithScores(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<Tuple> result = resource.zrevrangeByScoreWithScores(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset,
                                              int count) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<Tuple> result = resource.zrangeByScoreWithScores(key, min, max, offset, count);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset,
                                                 int count) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<Tuple> result = resource.zrevrangeByScoreWithScores(key, min, max, offset, count);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset,
                                                 int count) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Set<Tuple> result = resource.zrevrangeByScoreWithScores(key, min, max, offset, count);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.zremrangeByRank(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zremrangeByScore(String key, double start, double end) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.zremrangeByScore(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.zremrangeByScore(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long zlexcount(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Set<String> zrangeByLex(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Set<String> zrangeByLex(String s, String s1, String s2, int i, int i1) {
        return null;
    }

    @Override
    public Long zremrangeByLex(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.linsert(key, where, pivot, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long lpushx(String key, String... string) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.lpushx(key, string);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long rpushx(String key, String... string) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.rpushx(key, string);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public List<String> blpop(String arg) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            List<String> result = resource.blpop(arg);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public List<String> blpop(int i, String s) {
        return null;
    }

    @Override
    public List<String> brpop(String arg) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            List<String> result = resource.brpop(arg);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long del(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.del(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public String echo(String string) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            String result = resource.echo(string);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long move(String key, int dbIndex) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.move(key, dbIndex);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long bitcount(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.bitcount(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.bitcount(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public ScanResult<Entry<String, String>> hscan(String key, int cursor) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            ScanResult<Entry<String, String>> result = resource.hscan(key, cursor);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public ScanResult<String> sscan(String key, int cursor) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            ScanResult<String> result = resource.sscan(key, cursor);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public ScanResult<Tuple> zscan(String key, int cursor) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            ScanResult<Tuple> result = resource.zscan(key, cursor);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            ScanResult<Entry<String, String>> result = resource.hscan(key, cursor);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            ScanResult<String> result = resource.sscan(key, cursor);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            ScanResult<Tuple> result = resource.zscan(key, cursor);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public Long pfadd(String key, String... elements) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            Long result = resource.pfadd(key, elements);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    @Override
    public long pfcount(String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            long result = resource.pfcount(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public List<String> brpop(int timeout, String key) {
        ShardedJedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            List<String> result = resource.getShard(key).brpop(timeout, key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Map<String, String> mget(Collection<String> keys) {
        Map<String, String> result = new HashMap<String, String>();
        if (CollectionUtils.isNotEmpty(keys)) {
            for (List<String> list : Iterables.partition(keys, PARTITION_SIZE)) {
                ShardedJedis shardedJedis = pool.getResource();
                boolean success = false;
                try {
                    ShardedJedisPipeline pipeline = shardedJedis.pipelined();
                    Map<String, Response<String>> responseMap = new HashMap<String, Response<String>>(list.size());
                    for (String entry : list) {
                        responseMap.put(entry, pipeline.get(entry));
                    }
                    pipeline.sync();
                    success = true;
                    for (Entry<String, Response<String>> entry : responseMap.entrySet()) {
                        if (entry.getValue().get() != null) {
                            result.put(entry.getKey(), entry.getValue().get());
                        }
                    }
                } finally {
                    returnResource(shardedJedis, success);
                }
            }
        }
        return result;
    }

    public static final int PARTITION_SIZE = 20;

    public Map<String, Map<String, String>> hmget(Collection<String> keys) {
        Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
        if (CollectionUtils.isNotEmpty(keys)) {

            for (List<String> list : Iterables.partition(keys, PARTITION_SIZE)) {
                ShardedJedis shardedJedis = pool.getResource();
                boolean success = false;
                try {
                    ShardedJedisPipeline pipeline = shardedJedis.pipelined();
                    Map<String, Response<Map<String, String>>> responseMap = new HashMap<String, Response<Map<String, String>>>(
                            list.size());
                    for (String entry : list) {
                        responseMap.put(entry, pipeline.hgetAll(entry));
                    }
                    pipeline.sync();
                    success = true;
                    for (Entry<String, Response<Map<String, String>>> entry : responseMap
                            .entrySet()) {
                        if (entry.getValue().get() != null) {
                            result.put(entry.getKey(), entry.getValue().get());
                        }
                    }
                } finally {
                    returnResource(shardedJedis, success);
                }
            }
        }
        return result;
    }

    public void mset(Map<String, String> dataMap) {
        mset(dataMap, 0);
    }

    public void mset(Map<String, String> dataMap, int expireSeconds) {
        if (CollectionUtils.isNotEmpty(dataMap)) {
            for (List<Entry<String, String>> list : Iterables.partition(dataMap.entrySet(),
                    PARTITION_SIZE)) {
                ShardedJedis shardedJedis = pool.getResource();
                boolean success = false;
                try {
                    ShardedJedisPipeline pipeline = shardedJedis.pipelined();
                    for (Entry<String, String> entry : list) {
                        if (entry.getKey() != null && entry.getValue() != null) {
                            if (expireSeconds > 0) {
                                pipeline.setex(entry.getKey(), expireSeconds, entry.getValue());
                            } else {
                                pipeline.set(entry.getKey(), entry.getValue());
                            }
                        }
                    }
                    pipeline.sync();
                    success = true;
                } finally {
                    returnResource(shardedJedis, success);
                }
            }
        }
    }

    public void mdel(Collection<String> keys) {
        if (CollectionUtils.isNotEmpty(keys)) {
            for (List<String> list : Iterables.partition(keys, PARTITION_SIZE)) {
                ShardedJedis shardedJedis = pool.getResource();
                boolean success = false;
                try {
                    ShardedJedisPipeline pipeline = shardedJedis.pipelined();
                    for (String entry : list) {
                        if (entry != null) {
                            pipeline.del(entry);
                        }
                    }
                    pipeline.sync();
                    success = true;
                } finally {
                    returnResource(shardedJedis, success);
                }
            }
        }
    }

    public void subscribe(JedisPubSub pubSub, String channel) {
        boolean success = false;
        ShardedJedis shardedJedis = pool.getResource();
        try {
            Jedis shard = shardedJedis.getShard(channel);
            shard.subscribe(pubSub, channel);
            success = true;
        } finally {
            returnResource(shardedJedis, success);
        }
    }

    public void psubscribe(JedisPubSub pubSub, String channel) {
        boolean success = false;
        ShardedJedis shardedJedis = pool.getResource();
        try {
            Jedis shard = shardedJedis.getShard(channel);
            shard.psubscribe(pubSub, channel);
            success = true;
        } finally {
            returnResource(shardedJedis, success);
        }
    }

    public void publish(String channel, String message) {
        boolean success = false;
        ShardedJedis shardedJedis = pool.getResource();
        try {
            Jedis shard = shardedJedis.getShard(channel);
            shard.publish(channel, message);
            success = true;
        } finally {
            returnResource(shardedJedis, success);
        }
    }


}
