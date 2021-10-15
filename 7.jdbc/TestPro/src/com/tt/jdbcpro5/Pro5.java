/*
    数据库连接池：
        -> 连接池提供了数据源，基于数据源操作
*/
package com.tt.jdbcpro5;

import com.tt.jdbcpro5.utils.JdbcUtils_DBCP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pro5 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = JdbcUtils_DBCP.getConnection();
            // 关闭数据库的自动提交，此时会自动开启事务
            conn.setAutoCommit(false);

            // 执行sql
            String sql1 = "update account set money = money-100 where name = 'A'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();

            String sql2 = "update account set money = money+100 where name = 'B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

            // 提交事务
            conn.commit();
            System.out.println("成功");
        } catch (SQLException e) {
            // 若失败则回滚事务 -> 此处可显示定义，也可以不写，默认就是'失败自动回滚'
            try {
                conn.rollback();
            } catch(SQLException ee) {
                ee.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtils_DBCP.release(conn,st,null);
        }
    }
}