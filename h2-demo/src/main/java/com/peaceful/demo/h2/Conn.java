package com.peaceful.demo.h2;

import com.peaceful.common.util.Util;

import java.sql.*;

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
        PreparedStatement preparedStatement0 = conn.prepareStatement("create TABLE IF NOT EXISTS  USER (id int ,name VARCHAR(20))");
        preparedStatement0.execute();
        String name = Math.random()*10+"";
        String sql = "insert into user (id,name) VALUES (1,'"+name+"')";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.execute();
        PreparedStatement preparedStatement1 = conn.prepareStatement("SELECT * from user");
//        PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT * from user");
//        PreparedStatement preparedStatement3 = conn.prepareStatement("SELECT * from user");
        ResultSet resultSet = preparedStatement1.executeQuery();
        while (resultSet.next()){
            Util.report(resultSet.getString(2));
        }


    }
}
