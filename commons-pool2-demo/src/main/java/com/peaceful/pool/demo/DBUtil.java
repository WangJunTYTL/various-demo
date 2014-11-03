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

    public String excSQL() throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();
            return "exc suc";
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connectionObjectPool.returnObject(connection);
        }
        return "exc fail";
    }


}
