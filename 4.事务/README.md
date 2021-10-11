## 事务
> 多方参与具有连贯性的操作，用于保证数据的完整性、准确性 -> 其提出了'ACID原则'
> 例子：A给B转账200元，B收到A转账200元，两个操作要么同时发生，要么都不会发生，并不会仅发生其中某个，需要将两个操作放在事务中处理[一组事务]，保证数据的完整性

### ACID原则
> 原子性Atomicity:
> 事务的操作要么都成功，要么都失败

> 一致性Consistency:
> 事务前后的数据完整性保证一致

> 隔离性Isolation:
> 多用户并发访问时，数据库会为每个用户开启独立的事务，保证其不被其它事务所干扰

> 持久性Durability：
> 事务一旦提交就不可逆，事务没有提交则恢复到原状，事务已经提交则持久化到数据库

### 隔离性导致的问题
> 脏读：
> 一个事务读取了另一个事务未提交的数据

> 不可重复读：
> 某事务内读取表中的数据，多次读取结果不同 -> 不一定是错误，有可能是场合不对

> 虚读/幻读：
> 某事务读取到了其它事务插入的数据，导致前后读取不一致

### 测试事务实现转账 -> demo
```sql
/*
  执行事务：
    关闭mysql事务自动提交：默认开启
      set autocommit = 0;//关闭
      set autocommit = 1;//开启

    手动处理事务
      1.关闭自动提交: set autocommit = 0;
      2.事务开启：start transaction;//之后的sql都在同一个事务内
      3.xxx操作
      4.提交：commit;//提交成功就无法再更改 -> 持久化
        回滚：rollback;//回到提交前的数据状态
      5.事务结束：set autocommit = 1;//直接开启mysql自动提交即可

      -> 其它：
         savepoint 保存点名;//设置一个事务的保存点
         rollback to savepoint 保存点名;//回滚到保存点
         release savepoint 保存点名;//撤销保存点
*/

// 转账
create database shop;
use shop;

creat table account(
  id int not null auto_increment,
  name varchar(30) not null,
  money decimal(9,2) not null,
  primary key (id)//主键索引
) engine=innoob default chareset=utf8

insert into account(name,money) values('a',2000.00),('b',8000.00);

// 事务
set autocommit = 0;//关闭自动提交
start transaction;//开启一组事务

update account set money=money-500 where name = 'a';
update account set money=money+500 where name = 'b';

commit;//提交事务
rollback;//回滚

set autocommit = 1;//事务结束，恢复默认值
```