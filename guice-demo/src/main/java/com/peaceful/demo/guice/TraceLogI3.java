package com.peaceful.demo.guice;

import com.google.inject.ProvidedBy;

/**
 * 这点设计有点反过来了，接口应该不知道实现类
 * Created by wangjun on 16/2/19.
 */
@ProvidedBy(TraceLog3Provider.class)
public interface TraceLogI3 {
    void log(String msg);
}
