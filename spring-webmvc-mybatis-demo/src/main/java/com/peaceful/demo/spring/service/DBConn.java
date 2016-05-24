package com.peaceful.demo.spring.service;

import com.peaceful.common.util.Util;

import java.sql.Connection;

/**
 * Created by wangjun on 16/3/2.
 */
public class DBConn {


    public DBConn(){
        Util.report("init conn...");
    }

    public Connection getConn(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Util.report("return conn");
        return null;
    }
}
