package com.peaceful.demo.h2;

import com.peaceful.common.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by wangjun on 15/2/4.
 */
public class Conn {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        String url = "jdbc:h2:~/test;CIPHER=AES";
        String user = "sa";
        String pwds = "filepwd userpwd";
        Connection conn = DriverManager.
                getConnection(url, user, pwds);
        Util.report(conn);
//        PreparedStatement preparedStatement0 = conn.prepareStatement("create TABLE USER (id int ,name VARCHAR(20))");
        PreparedStatement preparedStatement1 = conn.prepareStatement("SELECT * from user");
//        PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT * from user");
//        PreparedStatement preparedStatement3 = conn.prepareStatement("SELECT * from user");
        preparedStatement1.execute();

    }
}
