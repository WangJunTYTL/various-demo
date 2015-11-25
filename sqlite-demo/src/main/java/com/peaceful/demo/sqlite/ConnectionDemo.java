package com.peaceful.demo.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by wangjun on 15/11/23.
 */
public class ConnectionDemo {

    public static Connection getConn() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // 默认在当前目录下创建
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Opened database successfully");
        return c;
    }
}
