## 数据操作 -> 数据库/表创建后的基于'数据操作'
> 基于CMD的操作方式：
> 1. 打开mysql的安装目录bin文件夹：cd C:\mysql\mysql-5.7.35-winx64\bin
> 2. 登录：mysql -u root -p;//输入密码登录
> 3. 输入sql语句操作即可：例如create database name; -> 语句结尾必须添加分号，其会返回结果[语句结尾必须添加分号来表示语句的结束，mysql是非过程性语言(不依赖其它语句，一条语句返回一个结果)]
> -> 结束的时候输入exit;/quit;[必须添加分号]

### 插入数据
> insert into 表名 (字段1,字段2,字段3) values (值1,值2,值3);//向表中指定字段添加值[字段个数可选]
> insert into 表名 values (值1,值2,值3,,,);//向表中所有字段添加值
> -> 插入数据的类型和表字段类型要匹配、长度也在范围内、字符串和日期类型的数据需使用单引号括起来

> insert into user (name,age) values ('denry',18);
> insert into user values (1,'curry',19,'2021-10-08','2021-12-26',8000,'test')
### 查询
> select * from 表名

### 修改
> update 表名 set 字段1=值1, 字段2=值2 where 条件;//字段个数可选、若没有where默认修改所有记录，若有where则修改符合条件的记录

> update user set age 20;//所有记录的age都修改为20
> update user set sal = 6000 where username = 'curry';
> update user set sal = 8000, job='baidu' where username = 'curry';
> update user set sal = sal + 2000 where username = 'curry';//原有sal上增加2000

### 删除
> delete from 表名 where 条件;//若没有where默认删除所有记录，若有where则删除符合条件的记录

> 删除整张表：
> delete from 表;//删除整张表 -> 其支持事务操作[数据库重要特性],删除后可通过事务恢复数据
> truncate 表;//删除整张表，然后创建新表，但新表中没数据 -> 速度相比delete from xx 更快，但不支持事务，删除后无法恢复

> delete from user where username = 'curry';

> 基于事务的删除：
> start transaction;
> delete from user where id = 1;
> rollback;

> 基于truncate的删除：
> truncate user;