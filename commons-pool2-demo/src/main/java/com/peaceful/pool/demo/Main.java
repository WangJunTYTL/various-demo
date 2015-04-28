package com.peaceful.pool.demo;

import com.peaceful.common.util.Util;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.sql.Connection;

/**
 * Date 14/11/1.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
//        config.setMaxIdle(8);
        config.setMinIdle(8);
        DBUtil dbUtil = new DBUtil(new GenericObjectPool<Connection>(new ConnectionFactory(),config));
        Util.report(dbUtil.excSQL());
        Thread.sleep(1000);
        Util.report(dbUtil.getIdleConnectionNum());
    }

}
