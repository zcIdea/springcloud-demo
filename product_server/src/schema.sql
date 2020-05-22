-- schema.sql
drop table user if exists;
create table user(
  id bigint generated by default as identity,
  name varchar(20),
  age int(3),
  money decimal(10,2),
  primary key(id)
);
