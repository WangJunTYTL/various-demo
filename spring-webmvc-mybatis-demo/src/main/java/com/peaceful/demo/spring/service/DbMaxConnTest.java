package com.peaceful.demo.spring.service;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author WangJun
 * @version 1.0 16/4/22
 */
public class DbMaxConnTest {

    private final static String url = "jdbc:mysql://localhost:3306/test?"
            + "user=root&password=&useUnicode=true&characterEncoding=UTF8";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        for (int i = 0; i < 10000; i++) {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                DriverManager.getConnection(url);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
