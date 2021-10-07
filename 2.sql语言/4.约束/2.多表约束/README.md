## 多表约束 -> 外键
> 保证多表结构中数据的完整性

```sql
// 部门
create table dept(
  did int primary key auto_increment,
  dname varchar(30)
);

// 员工
create table emp(
  eid int primary key auto_increment,
  ename varchar(30),
  sal dobule,
  dno int,
  foreign key (dno) references dept (did) // 外键
);

insert into dept values(1,'研发部');
insert into dept values(null,'运营部');// 自动递增

insert into emp values(1,'a',1.5,1);
insert into emp values(2,'b',1.5,1);

// 删除：仅能先删除子表，再删除依赖表
delete from emp;
delete from dept;
```