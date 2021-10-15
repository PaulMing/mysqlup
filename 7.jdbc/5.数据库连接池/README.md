## 数据库连接池
> 背景：数据库连接、释放很占用系统资源，JDBC仅实现连接技术，实际是基于'单次连接' -> 数据库连接池是'池化技术',定义连接池，每次连接后并不释放[连接复用]，当所有资源连接完成后再释放资源

> 常用配置名称
> 最小连接数：10个 -> 常用10个连接数，最小连接数就设置成10即可，也就是池子中10次连接后才释放
> 最大连接数：15个 -> 业务最高承载上限
> 等待超时：100ms  -> 若连接数超过10个，但小于15个，后面的连接需排队等待，设置等待超时时间[阀值]

### 常用连接池/数据源
> DBCP[开源组织]、C3P0[开源组织]、Druid[阿里]

> DBCP
> 1. idea创建普通项目
> 2. 导入jar包
>    [1].下载jar包
>        commons-dbcp2-2.9.0.jar  ->  http://commons.apache.org/proper/commons-dbcp/download_dbcp.cgi
>        commons-pool2-2.11.1.jar -> http://commons.apache.org/proper/commons-pool/download_pool.cgi
>    [2].创建lib文件夹，将jar包放入lib目录下
>    [3].lib目录右键add as library -> 这样才算真正导入进来了[其会解压缩jar包]
> 3. 准备数据：创建数据库、表数据等
> 4. 编写相关代码便可操作数据库了
>    [1].创建数据源
>    [2].基于数据源操作
> -> 实际所有'第三方服务'使用流程都是如此：引入jar包、调用接口

```properties
// dbcpconfig.properties
# 驱动名
driverClassName=com.mysql.cj.jdbc.Driver
# url
url=jdbc:mysql://localhost:3306/information_schema?useUnicode=true&characterEncoding=utf8&useSSL=true
# 用户名
username=xxxxxx
# 密码
password=xxxxxx

# 初始化连接
initialSize=10
# 最大连接数
maxTotal=50
# 最大空闲连接数
maxIdle=10
# 最小空闲连接数
minIdle=5
# 超时等待时间(毫秒)
maxWait=60000
maxWaitMillis=1000
# 程序中的连接不使用后是否被连接池回收(该版本要使用removeAbandonedOnMaintenance和removeAbandonedOnBorrow)
# removeAbandoned=true
removeAbandonedOnMaintenance=true
removeAbandonedOnBorrow=true
# 连接在所指定的秒数内未使用才会被删除(秒)(为配合测试程序才配置为1秒)
removeAbandonedTimeout=1
```

```java
// JdbcUtils_DBCP.java
package com.tt.jdbcpro5.utils;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils_DBCP {
  private static DataSource dataSource = null;

  static {
    try {
      // src下的资源均可通过反射拿到
      InputStream in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
      Properties properties = new Properties();
      properties.load(in);

      // 创建数据源 -> 创建数据源
      DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);//'配置项的流'作为参数
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  // 获取连接
  public static Connection getConnection() throws SQLException {
    return dataSource.getConnection();//数据源中自带连接
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

// 测试demo
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
```