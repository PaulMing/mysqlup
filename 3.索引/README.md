## 索引
> 其可提升数据检索速度，直接使用即可，mysql底层会进行优化来提升查询速度，实际就是数据结构层面的优化
> 索引原则：索引并非越多越好，不要对进程变动数据添加索引，一般都加在普通查询字段上 -> 数据量较少不明显，500万以上数据量优势明显

### 索引分类
> 主键索引 PRIMARY KEY
> 只能有一个列作为主键，不可重复

> 唯一索引 UNIQUE KEY
> 可多个列标识为唯一索引，列不能重复，但列中数据可重复

> 常规/普通索引 KEY/INDEX
> 默认值

> 全文索引 FULLTEXT
> 快速定位数据，特定数据库引擎才有，MYISAM -> 现在基本所有数据库都支持

### 索引使用
> 创建表或更改表时添加索引即可

```sql
// 创建时添加索引
create table mytable(
  id int not null,
  username varchar(16) not null,
  email varchar(16) not null,
  primary key 'id' ('id),//主键索引  索引名 列名
  unique key 'email' ('email(16)')//唯一索引 索引名 列名
  index 'username' ('username(16)'), //普通索引  索引名 列名
  key 'username' ('username(16)'), //普通索引  索引名 列名
)

// 更改时添加索引
alter table mytable add unique key 'email' ('email(16)')
alter table mytable add fulltext index 'address' ('address')

// 显示所有索引信息
show index from mytable

// 分析sql执行状况 -> 使用explain关键字[其的用法就是 explain + sql语句]
explain select * from mytable;
explain select * from student where match(username) against('a')
```