package com.peaceful.mysql.dis;

import com.google.common.collect.Sets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by wangjun38 on 2018/1/16.
 */
public class T1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "110");
            statement = connection.prepareStatement("SELECT * FROM USER "); // 查询所有的表
            statement.executeQuery();
        } finally {
            statement.close();
            connection.close();
        }
    }


    private Set<String> getRouteTableList(String logicTable) { // 需要提前配置user表的分表信息
        Set<String> tables = Sets.newHashSet("user0", "user1", "user2");
        return tables;
    }
}
