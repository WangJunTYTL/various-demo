package com.peaceful.pool.demo;

import org.apache.commons.pool2.ObjectPool;

import java.sql.Connection;

/**
 * Date 14/11/1.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class DBUtil {

    private ObjectPool<Connection> connectionObjectPool;


    public DBUtil(ObjectPool<Connection> connectionObjectPool) {
        this.connectionObjectPool = connectionObjectPool;
    }


    public Connection getConnection() throws Exception {
        return connectionObjectPool.borrowObject();
    }

    public String excSQL() {
        Connection connection = null;
        Connection connection1 = null;
        Connection connection2 = null;
        try {
            connection = getConnection();
            connection1 = getConnection();
            connection2 = getConnection();
            return "exc suc";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionObjectPool.returnObject(connection);
                connectionObjectPool.returnObject(connection1);
                connectionObjectPool.returnObject(connection2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "exc fail";
    }

    public int getIdleConnectionNum() {
        return connectionObjectPool.getNumIdle();
    }


}
