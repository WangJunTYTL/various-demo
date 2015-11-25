package com.peaceful.demo.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by wangjun on 15/11/23.
 */
public class CreateTableDemo {


    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionDemo.getConn();
        String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            statement.close();
        }

    }


}
