create table blogpost
(
    uid     varchar(20) not null comment '文章uid 通过后端生成保证不重复 不适用自增整形
',
    name    varchar(30) not null comment '文章标题
',
    author  varchar(20) not null comment '作者id
',
    content text        null comment '文章内容 HTML格式',
    constraint blogpost_id_uindex
        unique (uid)
);

alter table blogpost
    add primary key (uid);

create table user
(
    username varchar(20)        not null comment '用户名 主键 唯一',
    password varchar(20)        not null comment '用户密码
',
    pgroup   smallint default 0 null comment '用户所属用户组 -1管理员 0游客 1用户',
    constraint user_username_uindex
        unique (username)
);

alter table user
    add primary key (username);


