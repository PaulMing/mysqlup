/*
    jdbc测试demo：
       PreparedStatement 防止sql注入
*/
package com.tt.jdbcpro3;

import com.tt.jdbcpro2.utils.JdbcUtils;
import java.sql.*;

public class Pro3 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();
            /*
              PreparedStatement：
                -> 可防止sql注入
                -> 本质：将传递的参数当做字符处理，若存在转义字符会直接进行转义
                -> 使用：其采用预编译的机制，先将sql的值定义为?，之后再手动设置值
            */
            String sql = "insert into users(id,name,password,email,birthday) values(?,?,?,?,?)";
            st = conn.prepareStatement(sql);//预编译sql,先写sql，后面再手动设置，然后执行
            // 手动设置值
            st.setInt(1,4);// 参数：(第几个参数，值)
            st.setString(2,"name");
            st.setString(3,"12345");
            st.setString(4,"xxx@qq.com");
            st.setDate(5,new java.sql.Date(new Date().getTime()));
            // 执行
            int i = st.executeUpdate();
            if(i > 0) {
                System.out.println("插入成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,null);
        }
    }
}