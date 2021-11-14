package com.project.etsapi.entity;

import java.sql.*;

public class DBConnect {
    // 数据库URL，用户名，密码
    public static String DB_URL = "jdbc:mysql://1.117.164.153:3306/ets";
    public static String USER = "root";
    public static String PASSWORD = "password";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            // 1、注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、获取数据库连接
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("success!");
        } catch (Exception e) {
            System.out.println("failed!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Statement state, Connection connection){
        if(state != null){
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet, Statement state, Connection connection){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(state != null){
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

