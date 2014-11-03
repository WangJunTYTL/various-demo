package com.peaceful.pool.demo;

import org.apache.commons.pool2.impl.GenericObjectPool;

import java.sql.Connection;

/**
 * Date 14/11/1.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class Test {
    DBUtil dbUtil = new DBUtil(new GenericObjectPool<Connection>(new ConnectionFactory()));

}
