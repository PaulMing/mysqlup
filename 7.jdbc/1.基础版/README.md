## 基础版
> jdbc实现的demo

### 步骤
> 1. idea创建普通项目
> 2. 导入jdbc驱动jar包
>    [1].下载jar包 -> https://dev.mysql.com/downloads/connector/j/
>    [2].创建lib文件夹，将jar包放入lib目录下
>    [3].lib目录右键add as library -> 这样才算真正导入进来了
> 3. 准备数据：创建数据库、表数据等
> 4. 编写jdbc相关代码便可操作数据库了

```java
package com.tt.jdbc;

import java.sql.*;

public class Demo1 {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    /*
      1.加载驱动：
        -> 直接使用'程序驱动类'即可
        com.mysql.jdbc.Driver;//已废弃
        com.mysql.cj.jdbc.Driver;//推荐使用
    */
    Class.forName("com.mysql.cj.jdbc.Driver");

    /*
      2.连接数据库
        -> 需要主机mysql地址、username、password
        url: jdbc:mysql://主机地址：端口/数据库名?参数1=值&参数2=值&参数3=值
      3.连接成功，获取到数据库对象xx
        -> 其封装了事务操作
        xx.commit();//提交
        xx.rollback();//回滚
        xx.setAutoCommit();//自动提交
    */
    String url = "jdbc:mysql://localhost:3306/information_schema?useUnicode=true&characterEncoding=utf8&useSSL=true";
    String username = "root";
    String password = "123456";
    // 数据库对象
    Connection connection = DriverManager.getConnection(url,username,password);

    /*
      4.执行sql的对象
        -> 其提供了两个执行sql的对象
        Statement: 会发生sql注入
        PrepareStatement: 屏蔽了sql注入

      5.执行sql
        -> 该对象提供了sql语句操作，返回结果集resultSet
        statement.executeQuery(sql);//查询
        statement.executeUpdate(sql);//更新、插入、删除
        statement.execute(sql);//执行任何sql

        // 获取指定数据类型
        resultSet.getObject();// 不清楚列类型的情况下使用，若清楚具体类型就使用指定类型
        resultSet.getInt();
        resultSet.getFloat();
        resultSet.getDate();
        resultSet.getString();

        // 遍历/指针
        resultSet.beforeFirst();// 移动到最前面
        resultSet.afterLast();// 移动到最后面
        resultSet.next();// 移动到下一个数据
        resultSet.previous();// 移动到前一行
        resultSet.absolute(row);// 移动到指定行
    */
    Statement statement = connection.createStatement();
    String sql = "SELECT * FROM ENGINES";
    ResultSet resultSet = statement.executeQuery(sql);
    while(resultSet.next()) {
      System.out.println("id=" + resultSet.getObject("engine"));
      System.out.println("name=" + resultSet.getObject("support"));
      System.out.println("countryCode=" + resultSet.getObject("comment"));
      System.out.println("district=" + resultSet.getObject("transactions"));
      System.out.println("population=" + resultSet.getObject("xa"));
      System.out.println("population=" + resultSet.getObject("savepoints"));
    }

    /*
      6.释放连接 -> 后开的先关
    */
    resultSet.close();
    statement.close();
    connection.close();
  }
}
```