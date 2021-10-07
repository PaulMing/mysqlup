## 单表约束
> 保证数据完整性

### 主键约束
> 唯一标识[primary key 声明该列为主键]
> 将某列字段进行声明 -> 特点：唯一、非空、被引用[当前列作为一条记录的标识]
```sql
create database db1;
use db1;
create table person(
  id int primary key,
  username varchar(20)
)

// 主键自动增长：仅适合int、bigint类型 -> auto_increment
creat table person1(
  id int primary key auto_increment,
  username varchar,
);
insert into person1 values(null,'a');//赋值为null，其就会递增
insert into person1 values(10,'f');
```

### 唯一约束
> 声明字段值为唯一[unique]
```sql
create database db1;
use db1;
create table person(
  id int primary key,
  username varchar(20) unique
)
```

### 非空约束
> 非空约束：声明字段值不能为空[not null]
```sql
create database db1;
use db1;
create table person(
  id int primary key,
  username varchar(20) unique,
  email varchar(30) not null
)
```