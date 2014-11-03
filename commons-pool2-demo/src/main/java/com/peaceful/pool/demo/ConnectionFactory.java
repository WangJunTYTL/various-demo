package com.peaceful.pool.demo;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.sql.Connection;

/**
 * Date 14/11/1.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class ConnectionFactory extends BasePooledObjectFactory<Connection> {
    @Override
    public Connection create() throws Exception {
        Connection connection=null;
        return connection;
    }

    @Override
    public PooledObject<Connection> wrap(Connection obj) {
        return new  DefaultPooledObject<Connection>(obj);
    }


}
