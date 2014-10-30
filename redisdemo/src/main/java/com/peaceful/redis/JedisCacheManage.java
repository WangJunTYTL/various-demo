package com.peaceful.redis;

/**
 * Date 14/10/29.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class JedisCacheManage extends JedisCache {


   public static Cache getCache(String namespace) {
        JedisCacheManage cache = new JedisCacheManage();
        cache.setNamespace(namespace);
        return cache;
    }

    private JedisCacheManage(){
        setJedis(JedisUtil.getJedis());
    }


}
