## 优化版
> 提取配置项、封装工具库函数

```properties
// 配置文件 db.properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/information_schema?useUnicode=true&characterEncoding=utf8&useSSL=true
username=root
password=123456
```

```java
// jdbcUtils.java
package com.tt.jdbcpro2.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
  private static String driver = null;
  private static String url = null;
  private static String username = null;
  private static String password = null;

  // 静态构造快：完成驱动加载[仅加载一次即可]
  static {
    try {
      // src下的资源均可通过反射拿到
      InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
      Properties properties = new Properties();
      properties.load(in);

      driver = properties.getProperty("driver");
      url = properties.getProperty("url");
      username = properties.getProperty("username");
      password = properties.getProperty("password");

      Class.forName(driver);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  // 获取连接
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url,username,password);
  }

  // 释放连接
  public static void release(Connection connection, Statement statement, ResultSet resultSet) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if(statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if(connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
```

```java
// 测试demo
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
```