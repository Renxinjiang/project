create database poetry charset utf8mb4;
use poetry;
create table t_tangshi (
                         id int(11) auto_increment primary key comment '自增主键',
                         dynasty varchar(32) not null comment '朝代',
                         title varchar(200) not null comment '标题',
                         author varchar(20) not null comment '作者',
                         content text not null comment '正文',
                         words text not null comment '分词信息',
                         sha256 char(64) unique not null comment 'hash（标题+正文）'
  );