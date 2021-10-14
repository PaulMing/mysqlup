## sql注入
> 应用程序使用拼接SQL的技术而成为黑客攻击数据库的方式，所有数据库都有此安全问题，因为其与sql语句强相关
> 解决方案：过滤参数即可

### 三种sql注入方式
> 方式1：基于1=1总为真
> UserId: 100 OR 1=1
> select * from users where userid = 100 or 1=1;//userid=100 or 1=1 结果总为true，此时就等同select * from users where true -> 其会查询出数据库所有数据

> 方式2: 基于""=""总为真
> UserName: "or ""="
> PassWord: "or ""="
> select * from users where name = ""or ""="" and password = ""or ""="";//字符串和用户输入内容进行拼接，结果总为true

> 方式3：基于批处理sql[多条sql语句]
> UserId: 100; drop table users;
> select * from users where userid = 100; drop table users;

### 解决方案
> 本质：过滤参数即可

> 方案1：使用sql参数


> 方案2：jdbc连接中使用PreparedStatement类[Statement类无法防止sql注入]
```java
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
```