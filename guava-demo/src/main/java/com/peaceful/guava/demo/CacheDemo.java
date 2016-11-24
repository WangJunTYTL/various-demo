package com.peaceful.guava.demo;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 16/8/5.
 */
public class CacheDemo {

    public static void main(String[] args) throws ExecutionException {
        //
        Cache cache = CacheBuilder.newBuilder().
                expireAfterAccess(5, TimeUnit.SECONDS). // 可以设置过期时间:访问多久后过期 写入后多久过期
                maximumSize(6). // 为了防止内存不足 可以设置 可以容纳的最多数量  最大占用空间 weak引用让GC回收
                build();
        cache.put("A1", 123);
        System.out.println(
                cache.get("A2", new Callable() {
                    @Override
                    public Object call() throws Exception {
                        return "Empty";
                    }
                })
        );
        System.out.println(cache.asMap().get("A1"));
        cache.invalidate("A1"); // clear key
        System.out.println(cache.asMap().get("A1"));

        LoadingCache loadCache = CacheBuilder.newBuilder().
                expireAfterWrite(5,TimeUnit.SECONDS).
                build(new CacheLoader<Object, Object>() {
                    @Override
                    public Object load(Object key) throws Exception {
                        return  Optional.absent(); // Note : 不可以返回null
                    }
                });
        loadCache.put("A1",123);
        System.out.println(loadCache.get("A1"));
        System.out.println(loadCache.get("A2"));
    }
}
