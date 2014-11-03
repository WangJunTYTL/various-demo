package com.peaceful.cglib.demo;

/**
 * Date 14/11/3.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
interface ProxySource<T> {

    /**
     * Create a new proxy object, wrapping the given object.
     *
     * @param object        The object to wrap
     * @param usageTracking The instance, if any  to
     *                      be provided with usage tracking information for this
     *                      wrapped object
     * @return the new proxy object
     */
    T createProxy(T object, UsageTracking usageTracking);

    /**
     * Obtain the wrapped object from the given proxy.
     *
     * @param proxy The proxy object
     * @return The object wrapped by the given proxy
     */
    T resolveProxy(T proxy);
}
