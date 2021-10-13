/*
    jdbc
*/
package com.tt.jdbc;

import java.sql.*;

public class Demo1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 连接数据库
        // jdbc:mysql://主机地址：端口号/数据库名?参数1&参数2&参数3
        String url = "jdbc:mysql://localhost:3306/information_schema?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "123456";
        // 连接成功，数据库对象
        Connection connection = DriverManager.getConnection(url,username,password);// connection就是数据库

        // 执行sql的对象
        Statement statement = connection.createStatement();

        // 执行sql
        String sql = "SELECT * FROM ENGINES";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            System.out.println("id=" + resultSet.getObject("engine"));
            System.out.println("name=" + resultSet.getObject("support"));
            System.out.println("countryCode=" + resultSet.getObject("comment"));
            System.out.println("district=" + resultSet.getObject("transactions"));
            System.out.println("population=" + resultSet.getObject("xa"));
            System.out.println("population=" + resultSet.getObject("savepoints"));
//            System.out.println("id=" + resultSet.getObject("ID"));
//            System.out.println("name=" + resultSet.getObject("Name"));
//            System.out.println("countryCode=" + resultSet.getObject("CountryCode"));
//            System.out.println("district=" + resultSet.getObject("District"));
//            System.out.println("population=" + resultSet.getObject("Population"));
        }

        // 释放连接 -> 后开的先关
        resultSet.close();
        statement.close();
        connection.close();
    }
}