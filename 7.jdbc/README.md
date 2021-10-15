## jdbc
> 背景：SUN公司提供了JAVA操作数据库规范，也就是JDBC，这些规范由具体厂商实现
> 其作为应用程序与数据库的'连接媒介'，需要满足所有的sql操作，也就是根据sql完成对数据的操作
![](assets/数据库连接.png)

> 需要的包：
> 1. java.sql
> 2. javax.sql
> 3. 数据库驱动包 -> 官方下载即可https://dev.mysql.com/downloads/connector/j/
![](assets/jar包.png)

### 未来发展
> 接下来会学习框架mybytis/mybytis plus，其在jdbc、数据库连接池的基础上进行封装处理，开发者可极简操作数据库 -> 聚焦：1.引入jar包、2.配置文件初始化、3.调用接口 