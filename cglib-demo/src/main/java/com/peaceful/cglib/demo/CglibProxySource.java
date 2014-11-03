package com.peaceful.cglib.demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Factory;

/**
 * Date 14/11/3.
 * Author WangJun
 * Email wangjuntytl@163.com
 * <p/>
 * Provides proxy objects using CGLib.
 */
public class CglibProxySource<T> implements ProxySource<T> {

    private final Class<? extends T> superclass;

    /**
     * Create a new proxy source for the given class.
     *
     * @param superclass The class to proxy
     */
    public CglibProxySource(Class<? extends T> superclass) {
        this.superclass = superclass;
    }


    @Override
    public T createProxy(T pooledObject, UsageTracking usageTracking) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(superclass);

        CglibProxyHandler<T> proxyInterceptor =
                new CglibProxyHandler<T>(pooledObject, usageTracking);
        enhancer.setCallback(proxyInterceptor);

        @SuppressWarnings("unchecked")
        T proxy = (T) enhancer.create();

        return proxy;
    }


    @Override
    public T resolveProxy(T proxy) {
        @SuppressWarnings("unchecked")
        CglibProxyHandler<T> cglibProxyHandler =
                (CglibProxyHandler<T>) ((Factory) proxy).getCallback(0);
        T pooledObject = cglibProxyHandler.disableProxy();
        return pooledObject;
    }
}
