create table spring(
id int auto_increment primary key,
category varchar(20),
title varchar(255),
content mediumtext,
deleted varchar(10)
);

create table member(
id int auto_increment primary key,
username varchar(20),
pw varchar(20),
authority varchar(10)
);