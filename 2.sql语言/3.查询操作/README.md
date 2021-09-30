## 查询操作
> 查询表中数据，实际就是基于表字段查询数据 -> 归属于'数据操作',但其操作方式较多，所以独立出来数据查询语言DQL[数据操作语言DML]

### 基本查询
> select * | 字段1,字段2 from 表名 where 过滤条件;

> select * from 表名;
> select 字段1,字段2,字段3 from 表名;//字段数量可选
> select distinct math from stu;//distinct关键字：过滤掉重复值
> select 字段1,字段2 as xx from 表名;//取别名

> where条件
> 1. 常用符号： * > < >= <= = <>不等于[sql用<>符号表示不等于，并非!=]
> 2. in: 表示范围
>        select * from stu where math in (70,80,90);//math值为70、80、90的都输出
> 3. like: 模糊查询 -> 条件必须使用单引号
>        _占位符：仅表示一位，select * from stu where username like '张_';//仅查找张x
>        %占位符：代表多个位置，select * from stu where username like '张%';//可查找张xx,多少位均可，保证张开头即可
>        -> 占位符写在前后均可：_张_、_张、%张%、张%
> 4. and/between: 与
>        select username,math from stu where math > 60 and math < 100; 
>        select username,math from stu where math > 60 between math < 100; 
> 5. or: 或
>        select username,math from stu where math > 70 or math < 90;
> 6. not: 非
>        select username,math from stu where math not 90;

> demo
```sql
// 创建表
create table stu(
  id int,
  username varchar(20),
  math int,
  english int,
  chinese int
);
// 查看表详情
desc stu;

// 插入数据
insert into stu values (1,'a',79,80,90);
insert into stu values (2,'b',60,70,80);
insert into stu values (3,'c',75,85,98);
insert into stu values (4,'d',62,82,93);
insert into stu values (5,'e',76,83,91);
insert into stu values (6,'f',71,82,96);

// 查询
select * from stu;
select username,english from stu;
select username,distinct english from stu;
select username,math+10,english+10,chinese+10 from stu;//math、english、chinese值均增加10 -> 仅当前查询有效，并不影响原数据
select username,(math+english+chinese) from stu;//加和
select username,(math+english+chinese) as sum from stu;//取别名

select * from stu where math in (70,80,90);//math值为70、80、90的都输出
select * from stu where username like '张_';//仅查找张x
select * from stu where username like '张%';//可查找张xx,多少位均可，保证张开头即可
select username,math from stu where math > 60 and math < 100;
select username,math from stu where math > 60 between math < 100;
select username,math from stu where math > 70 or math < 90;
select username,math from stu where math not 90;
select username,(math+english+chinese) from stu where (math+english+chinese) > 150; 
select username,(math+english+chinese) as sum from stu where sum > 150;//错误，where后的sum作用域与前面的sum不同，无法获取值
```

### 排序
> order by 字段 asc | desc;//asc: 升序、desc: 降序 -> 可省略，默认为升序

> 使用格式：
> select * | 字段1,字段2 from 表名 order by 字段 asc | desc;
> select * | 字段1,字段2 from 表名 where 过滤条件 order by 字段 asc | desc;

> demo
```sql
// 创建表
create table stu(
  id int,
  username varchar(20),
  math int,
  english int,
  chinese int
);
// 查看表详情
desc stu;

// 插入数据
insert into stu values (1,'a',79,80,90);
insert into stu values (2,'b',60,70,80);
insert into stu values (3,'c',75,85,98);
insert into stu values (4,'d',62,82,93);
insert into stu values (5,'e',76,83,91);
insert into stu values (6,'f',71,82,96);

// 查询
select * from stu;
select username,math from stu order by math;//按math值升序排列，默认就是asc升序
select username,(math+english+chinese) from stu order by (math+english+chinese) desc;//总分降序排列
select username,(math+english+chinese) as sum from stu order by (math+english+chinese) desc;//总分降序排列
select username,(math+english+chinese) as sum from stu order by sum desc;//总分降序排列 -> order by 后可直接使用别名，其与where不同

select username,english,math from stu order by english desc,math desc;//先按english值降序排序，english值相同再按照math降序排序

select username,chinese from stu where username like '张%' order by chinese asc;
```

### 聚焦函数
> 聚焦操作某一列的数据
> count();//数量
> sum();//求和
> avg();//平均值
> max();//最大值
> min();//最小值

> 使用格式：
> select func(*) | func(列名) from stu;

```sql
// count
select count(*) from stu;
select count(id) from stu;

select count(math) from stu where math > 60;

```






### 分组查询
> 


```sql
```




### 



```sql
```