package com.peaceful.cglib.demo;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Date 14/11/3.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class CglibProxyHandler<T> extends BaseProxyHandler<T>
        implements MethodInterceptor {


    /**
     * Create a CGLib proxy instance.
     *
     * @param pooledObject  The object to wrap
     * @param usageTracking The instance, if any (usually the object pool) to
     *                      be provided with usage tracking information for this
     *                      wrapped object
     */
    CglibProxyHandler(T pooledObject, UsageTracking usageTracking) {
        super(pooledObject, usageTracking);
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        return doInvoke(method, args);
    }
}
