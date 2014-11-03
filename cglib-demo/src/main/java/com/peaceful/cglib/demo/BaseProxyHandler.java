package com.peaceful.cglib.demo;

import java.lang.reflect.Method;

/**
 * Date 14/11/3.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class BaseProxyHandler<T> {


    private T pooledObject;
    private final UsageTracking usageTracking;


    /**
     * Create a new wrapper for the given pooled object.
     *
     * @param pooledObject  The object to wrap
     * @param usageTracking The instance, if any (usually the object pool) to
     *                      be provided with usage tracking information for this
     *                      wrapped object
     */
    BaseProxyHandler(T pooledObject, UsageTracking usageTracking) {
        this.pooledObject = pooledObject;
        this.usageTracking = usageTracking;
    }


    /**
     * Obtain the wrapped, pooled object.
     *
     * @return the underlying pooled object
     */
    T getPooledObject() {
        return pooledObject;
    }


    /**
     * Disable the proxy wrapper. Called when the object has been returned to
     * the pool. Further use of the wrapper should result in an
     * {@link IllegalStateException}.
     *
     * @return the object that this proxy was wrapping
     */
    T disableProxy() {
        T result = pooledObject;
        pooledObject = null;
        return result;
    }


    /**
     * Check that the proxy is still valid (i.e. that {@link #disableProxy()}
     * has not been called).
     *
     * @throws IllegalStateException if {@link #disableProxy()} has been called
     */
    void validateProxiedObject() {
        if (pooledObject == null) {
            throw new IllegalStateException("This object may no longer be " +
                    "used as it has been returned to the Object Pool.");
        }
    }


    /**
     * Invoke the given method on the wrapped object.
     *
     * @param method The method to invoke
     * @param args   The arguments to the method
     * @return The result of the method call
     * @throws Throwable If the method invocation fails
     */
    Object doInvoke(Method method, Object[] args) throws Throwable {
        long now = System.currentTimeMillis();
        validateProxiedObject();
        T object = getPooledObject();
        method.invoke(object, args);
        if (usageTracking != null) {
            usageTracking.costTime(object.getClass(),method, System.currentTimeMillis() - now);
        }
        return object;
    }
}
