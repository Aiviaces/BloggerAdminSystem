/*
 Navicat Premium Data Transfer

 Source Server         : 张新宇的数据库
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : bloggeradmin       <------数据库名,手动创建

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 14/08/2023 19:01:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blogpost
-- ----------------------------
DROP TABLE IF EXISTS `t_blogpost`;
CREATE TABLE `t_blogpost`
(
    `uid`          varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章uid 通过后端生成保证不重复 不适用自增整形\n',
    `name`         varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章标题\n',
    `author`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者id\n',
    `content`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci        NULL COMMENT '文章内容 HTML格式',
    `release_time` datetime                                                     NULL     DEFAULT NULL COMMENT '发布时间 - 写在sql里生成',
    `review`       tinyint                                                      NOT NULL DEFAULT 0 COMMENT '文章审核状态 0未通过 1通过',
    PRIMARY KEY (`uid`) USING BTREE,
    UNIQUE INDEX `blogpost_id_uindex` (`uid` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blogpost
-- ----------------------------
INSERT INTO `t_blogpost`
VALUES ('1', '测试', '0', '测试111', NULL, 0);
INSERT INTO `t_blogpost`
VALUES ('21', 'test002', '12', '测试插入', '2023-07-23 20:00:34', 0);
INSERT INTO `t_blogpost`
VALUES ('23', 'test004', '14', '测试插入3', '2023-07-23 20:02:52', 0);
INSERT INTO `t_blogpost`
VALUES ('24', 'test003', '13', '测试插入1', '2023-07-23 20:02:52', 0);

-- ----------------------------
-- Table structure for t_permission_group
-- ----------------------------
DROP TABLE IF EXISTS `t_permission_group`;
CREATE TABLE `t_permission_group`
(
    `uid`                    int                                                           NOT NULL COMMENT '权限组id 后台代码生成',
    `name`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限组名称',
    `fixed`                  tinyint                                                       NOT NULL DEFAULT 0 COMMENT '是否为固定权限,固定权限不可修改',
    `p_login`                tinyint                                                       NOT NULL DEFAULT 0 COMMENT '登录页权限',
    `p_admin_pgroup_operate` tinyint                                                       NOT NULL DEFAULT 0 COMMENT '权限组操作页权限',
    `p_index`                tinyint                                                       NOT NULL DEFAULT 0 COMMENT '主页权限',
    `p_admin_user_operate`   tinyint                                                       NOT NULL DEFAULT 0 COMMENT '用户操作页权限',
    `p_admin_post_operate`   tinyint                                                       NOT NULL DEFAULT 0 COMMENT '文章页操作权限\n',
    `p_admin_post_add`       tinyint                                                       NOT NULL DEFAULT 0 COMMENT '添加文章页权限\n',
    `p_admin_post_review`    tinyint                                                       NOT NULL DEFAULT 0 COMMENT '审核文章页权限',
    PRIMARY KEY (`uid`) USING BTREE,
    UNIQUE INDEX `t_permission_group_uid_uindex` (`uid` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission_group
-- ----------------------------
INSERT INTO `t_permission_group`
VALUES (0, 'master', 1, 0, 1, 0, 0, 0, 0, 0);
INSERT INTO `t_permission_group`
VALUES (1, 'admin', 0, 1, 0, 1, 1, 1, 1, 1);
INSERT INTO `t_permission_group`
VALUES (2, 'auditor', 0, 1, 0, 1, 0, 0, 0, 1);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '用户名 主键 唯一',
    `email`    varchar(320) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱地址 - 理论长度最大320',
    `nick`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT '游客' COMMENT '昵称 - 系统生成\n',
    `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '用户密码\n',
    `pgroup`   smallint                                                      NULL DEFAULT 0 COMMENT '用户所属用户组 -1管理员 0游客 1用户',
    PRIMARY KEY (`username`) USING BTREE,
    UNIQUE INDEX `user_username_uindex` (`username` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user`
VALUES ('1', 'aiviaces@163.com', '游客', '1', 1);
INSERT INTO `t_user`
VALUES ('2', 'dacongming@xiaojilinggui.com', '游客', '2', -1);
INSERT INTO `t_user`
VALUES ('33', '', '游客', '44', 0);
INSERT INTO `t_user`
VALUES ('34', '', '游客', '354', 0);
INSERT INTO `t_user`
VALUES ('3f', '', '游客', '34', 0);
INSERT INTO `t_user`
VALUES ('4', '', '游客', '4', 3);
INSERT INTO `t_user`
VALUES ('43', '', '游客', '3', 0);
INSERT INTO `t_user`
VALUES ('434', '', '游客', '54', 0);
INSERT INTO `t_user`
VALUES ('44', '', '游客', '5', 0);
INSERT INTO `t_user`
VALUES ('5', '', '游客', '4', 0);
INSERT INTO `t_user`
VALUES ('g', '', '游客', '343', 0);
INSERT INTO `t_user`
VALUES ('test', '', '游客', '1', -1);
INSERT INTO `t_user`
VALUES ('test-rollback-45', '', '游客', '1', 1);
INSERT INTO `t_user`
VALUES ('test-rollback-55', '', '游客', '2', 2);
INSERT INTO `t_user`
VALUES ('test-rollback-6', '', '游客', '1', 1);
INSERT INTO `t_user`
VALUES ('test01', '', '游客', '2', -1);
INSERT INTO `t_user`
VALUES ('test02', '', '游客', '2', -1);

SET FOREIGN_KEY_CHECKS = 1;
