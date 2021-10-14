package com.tt.jdbcpro2;
import com.tt.jdbcpro2.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pro2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM ENGINES";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println("id=" + rs.getObject("engine"));
                System.out.println("name=" + rs.getObject("support"));
                System.out.println("countryCode=" + rs.getObject("comment"));
                System.out.println("district=" + rs.getObject("transactions"));
                System.out.println("population=" + rs.getObject("xa"));
                System.out.println("population=" + rs.getObject("savepoints"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}