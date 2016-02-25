package com.peaceful.demo.guice;

import com.google.inject.ImplementedBy;

/**
 * 这点设计有点反过来了，接口应该不知道实现类
 * Created by wangjun on 16/2/19.
 */
@ImplementedBy(TraceLog2Impl.class)
public interface TraceLogI2 {
    void log(String msg);
}
