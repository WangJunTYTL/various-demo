package com.peaceful.pool.demo;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * common-pool2 使用方式
 * <p/>
 * {@link org.apache.commons.pool2.impl.GenericObjectPool}支持个性化配置，我们可以配置对象池中总共的对象数，最大、最小空闲对象数等等
 * 这边继承{@link GenericObjectPoolConfig}是为了ConnPool也可以进行个性化的配置
 *
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/28
 * @since 1.6
 */

public class ConnPoolConfig extends GenericObjectPoolConfig {

    public ConnPoolConfig() {
        // defaults to make your life with connection pool easier :)
        setMinIdle(5);
        setTestOnBorrow(true);
    }

}
