package com.peaceful.pool.demo;

import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * common-pool2 使用方式
 * <p/>
 * Conn对象管理池，这里利用GenericObjectPool作为对象池
 *
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/28
 * @since 1.6
 */

public class ConnPool extends GenericObjectPool<Conn> {


    /**
     * 调用{@link GenericObjectPool}的构造方法，构造ConnPool
     */
    public ConnPool() {
        super(new ConnFactory(), new ConnPoolConfig());
    }

    /**
     * 调用{@link GenericObjectPool}的构造方法，构造ConnPool
     */
    public ConnPool(ConnPoolConfig connPoolConfig) {
        super(new ConnFactory(), connPoolConfig);
    }

}
