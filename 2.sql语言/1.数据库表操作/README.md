## 数据库、表操作
> 基于CMD的操作方式：
> 1. 打开mysql的安装目录bin文件夹：cd C:\mysql\mysql-5.7.35-winx64\bin
> 2. 登录：mysql -u root -p;//输入密码登录
> 3. 输入sql语句操作即可：例如create database name; -> 语句结尾必须添加分号，其会返回结果，结束的时候输入exit;[必须添加分号]

### 数据库操作
> 创建
> create database name;//基本写法
> create database name character set 编码 collate 校对规则;//完整写法 -> 开发中直接使用基本语法即可，存在默认值

> 查看
> show databases;//查看所有库
> use name;//切换数据库
> show create database name;//查看当前数据库的创建信息
> select database();//查询当前正在使用的数据库

> 修改
> alter database name character set 'gbk' collate '校对规则';//仅能修改编码及校对规则 -> 很少使用

> 删除
> drop database name;//数据库中的表、数据均会被删除



### 表操作








create database mydb1;
create database mydb2 charactor set 'utf8';// 仅能写utf8, 不能写utf-8
show databases;
show create database mydb2;// 查看创建该数据库的信息  数据库创建信息
select database(); 查询当前正在使用的数据库

### 表结构
create table 表名 (
  name varchar(255);
)



create table employee (
  id int,
  name varchar(30),
  gender char(5),
  birthday date,
  entry_date date,
  job varchar(50),
  salary double,
  resume text
)


desc tablename;
show tables;//



drop table 表名