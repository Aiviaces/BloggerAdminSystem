create table t_blogpost
(
    uid          varchar(20)       not null comment '文章uid 通过后端生成保证不重复 不适用自增整形
',
    name         varchar(30)       not null comment '文章标题
',
    author       varchar(20)       not null comment '作者id
',
    content      text              null comment '文章内容 HTML格式',
    release_time datetime          null comment '发布时间 - 写在sql里生成',
    review       tinyint default 0 not null comment '文章审核状态 0未通过 1通过',
    constraint blogpost_id_uindex
        unique (uid)
);

alter table t_blogpost
    add primary key (uid);

create table t_permission_group
(
    uid                    int               not null comment '权限组id 后台代码生成',
    name                   varchar(255)      not null comment '权限组名称',
    fixed                  tinyint default 0 not null comment '是否为固定权限,固定权限不可修改',
    p_login                tinyint default 0 not null comment '登录页权限',
    p_admin_pgroup_operate tinyint default 0 not null comment '权限组操作页权限',
    p_index                tinyint default 0 not null comment '主页权限',
    p_admin_user_operate   tinyint default 0 not null comment '用户操作页权限',
    p_admin_post_operate   tinyint default 0 not null comment '文章页操作权限
',
    p_admin_post_add       tinyint default 0 not null comment '添加文章页权限
',
    p_admin_post_review    tinyint default 0 not null comment '审核文章页权限',
    constraint t_permission_group_uid_uindex
        unique (uid)
);

alter table t_permission_group
    add primary key (uid);

create table t_user
(
    username varchar(20)              not null comment '用户名 主键 唯一',
    email    varchar(320)             not null comment '邮箱地址 - 理论长度最大320',
    nick     varchar(20) default '游客' null comment '昵称 - 系统生成
',
    password varchar(20)              not null comment '用户密码
',
    pgroup   smallint    default 0    null comment '用户所属用户组 -1管理员 0游客 1用户',
    constraint user_username_uindex
        unique (username)
);

alter table t_user
    add primary key (username);


