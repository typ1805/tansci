/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : tansci

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 29/06/2022 17:37:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log_error_info
-- ----------------------------
DROP TABLE IF EXISTS `log_error_info`;
CREATE TABLE `log_error_info`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `req_param` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常名称',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户名称',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求url',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求IP',
  `version` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '异常日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_error_info
-- ----------------------------

-- ----------------------------
-- Table structure for log_info
-- ----------------------------
DROP TABLE IF EXISTS `log_info`;
CREATE TABLE `log_info`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `module` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能模块',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作描述',
  `req_param` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `res_param` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '响应参数',
  `take_up_time` int(64) NULL DEFAULT NULL COMMENT '耗时',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户名称',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作方法',
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求url',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求IP',
  `version` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parent_id` int(11) NOT NULL COMMENT '父ID',
  `group_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分组名称',
  `type` int(1) NULL DEFAULT NULL COMMENT '类型：0、系统，1、业务',
  `dic_value` int(11) NOT NULL COMMENT '值',
  `dic_label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `text1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段1',
  `text2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段2',
  `text3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段3',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `group_name`(`group_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `sys_dic` VALUES (1, 0, 'sys_dic', 0, 0, '数据字典', 0, NULL, NULL, NULL, '2021-07-06 14:40:33', '2021-07-06 11:38:58', NULL);
INSERT INTO `sys_dic` VALUES (2, 1, 'sys_dic_type', 0, -1, '系统数据字典类型', 0, NULL, NULL, NULL, '2021-07-06 17:08:04', '2021-07-06 12:44:36', '系统数据字典类型');
INSERT INTO `sys_dic` VALUES (6, 1, 'del_type', 0, -1, '删除状态', 0, NULL, NULL, NULL, '2021-07-06 17:03:39', '2021-07-06 16:16:17', '系统删除状态');
INSERT INTO `sys_dic` VALUES (7, 6, 'del_type', 0, 0, '正常', 1, NULL, NULL, NULL, NULL, '2021-07-06 16:16:45', '');
INSERT INTO `sys_dic` VALUES (8, 6, 'del_type', 0, 1, '已删除', 2, NULL, NULL, NULL, NULL, '2021-07-06 16:17:08', '');
INSERT INTO `sys_dic` VALUES (9, 2, 'sys_dic_type', 0, 0, '系统', 1, NULL, NULL, NULL, NULL, '2021-07-06 16:18:05', '');
INSERT INTO `sys_dic` VALUES (10, 2, 'sys_dic_type', 0, 1, '业务', 2, NULL, NULL, NULL, NULL, '2021-07-06 16:18:19', '');
INSERT INTO `sys_dic` VALUES (12, 1, 'user_type', 0, -1, '用户类型', 0, NULL, NULL, NULL, NULL, '2021-07-08 15:59:08', '');
INSERT INTO `sys_dic` VALUES (13, 12, 'user_type', 0, 0, '超级管理员', 1, NULL, NULL, NULL, NULL, '2021-07-08 15:59:39', '');
INSERT INTO `sys_dic` VALUES (14, 12, 'user_type', 0, 1, '管理员', 2, NULL, NULL, NULL, NULL, '2021-07-08 15:59:56', '');
INSERT INTO `sys_dic` VALUES (15, 12, 'user_type', 0, 2, '普通用户', 3, NULL, NULL, NULL, NULL, '2021-07-08 16:00:29', '');
INSERT INTO `sys_dic` VALUES (35, 1, 'menu_status', 0, -1, '菜单状态', 0, NULL, NULL, NULL, NULL, '2021-07-20 22:39:37', '');
INSERT INTO `sys_dic` VALUES (36, 35, 'menu_status', 0, 0, '未上架', 1, NULL, NULL, NULL, NULL, '2021-07-20 22:39:59', '');
INSERT INTO `sys_dic` VALUES (37, 35, 'menu_status', 0, 1, '正常', 2, NULL, NULL, NULL, NULL, '2021-07-20 22:40:16', '');
INSERT INTO `sys_dic` VALUES (38, 35, 'menu_status', 0, 2, '已下架', 3, NULL, NULL, NULL, NULL, '2021-07-20 22:40:33', '');
INSERT INTO `sys_dic` VALUES (39, 1, 'role_status', 0, -1, '角色状态', 0, NULL, NULL, NULL, '2021-07-23 17:03:27', '2021-07-23 17:01:04', '');
INSERT INTO `sys_dic` VALUES (40, 39, 'role_status', 0, 0, '未启用', 1, NULL, NULL, NULL, '2021-07-23 17:03:35', '2021-07-23 17:01:52', '');
INSERT INTO `sys_dic` VALUES (41, 39, 'role_status', 0, 1, '正常', 2, NULL, NULL, NULL, '2021-07-23 17:03:41', '2021-07-23 17:02:11', '');
INSERT INTO `sys_dic` VALUES (43, 39, 'role_status', 0, 2, '已禁用', 3, NULL, NULL, NULL, '2021-07-23 17:03:46', '2021-07-23 17:03:00', '');
INSERT INTO `sys_dic` VALUES (44, 1, 'template_type', 1, -1, '模板类型', 0, NULL, NULL, NULL, NULL, '2022-02-24 13:08:12', NULL);
INSERT INTO `sys_dic` VALUES (46, 44, 'template_type', 1, 0, '验证码', 1, NULL, NULL, NULL, NULL, '2022-02-24 13:10:10', NULL);
INSERT INTO `sys_dic` VALUES (47, 44, 'template_type', 1, 1, '短信通知', 2, NULL, NULL, NULL, NULL, '2022-02-24 13:11:09', NULL);
INSERT INTO `sys_dic` VALUES (48, 44, 'template_type', 1, 2, '推广短信', 3, NULL, NULL, NULL, NULL, '2022-02-24 13:11:09', NULL);
INSERT INTO `sys_dic` VALUES (49, 44, 'template_type', 1, 3, '国际/港澳台消息', 4, NULL, NULL, NULL, NULL, '2022-02-24 13:11:09', NULL);
INSERT INTO `sys_dic` VALUES (50, 1, 'template_business_type', 1, -1, '模板业务类型', 0, NULL, NULL, NULL, NULL, '2022-02-24 13:13:21', NULL);
INSERT INTO `sys_dic` VALUES (51, 50, 'template_business_type', 1, 0, '短信', 1, NULL, NULL, NULL, NULL, '2022-02-24 13:13:21', NULL);
INSERT INTO `sys_dic` VALUES (52, 50, 'template_business_type', 1, 1, '邮件', 2, NULL, NULL, NULL, NULL, '2022-02-24 13:13:21', NULL);
INSERT INTO `sys_dic` VALUES (54, 1, 'template_state', 1, -1, '模板状态', 0, NULL, NULL, NULL, NULL, '2022-02-24 14:01:44', NULL);
INSERT INTO `sys_dic` VALUES (55, 54, 'template_state', 1, 0, '审核中', 1, NULL, NULL, NULL, NULL, '2022-02-24 14:02:16', NULL);
INSERT INTO `sys_dic` VALUES (56, 54, 'template_state', 1, 1, '通过', 2, NULL, NULL, NULL, NULL, '2022-02-24 14:02:38', NULL);
INSERT INTO `sys_dic` VALUES (57, 54, 'template_state', 1, 2, '未通过', 3, NULL, NULL, NULL, NULL, '2022-02-24 14:02:54', NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parent_id` int(11) NOT NULL COMMENT '父id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单路由',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `type` int(1) NOT NULL COMMENT '类型：0、按钮，1、菜单，2、嵌套链接，3、跳转链接',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、未上架，1、正常，2、下架',
  `chinese_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  `english_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 'home', '/Home', 'HomeFilled', 1, 1, '工作台', 'Home', 1, '2021-07-28 15:43:30', '2021-07-20 22:02:18');
INSERT INTO `sys_menu` VALUES (2, 0, 'system', '/system', 'Tools', 1, 1, '系统管理', 'System', 1, '2021-12-17 17:33:08', '2021-10-23 19:50:28');
INSERT INTO `sys_menu` VALUES (3, 2, 'user', '/system/User', 'Avatar', 1, 1, '用户列表', 'UserList', 4, '2021-12-17 17:37:24', '2021-07-20 22:09:46');
INSERT INTO `sys_menu` VALUES (5, 2, 'dicInfo', '/system/DicInfo', 'Notebook', 1, 1, '字典管理', 'Dic', 6, '2021-12-17 17:38:14', '2021-07-22 17:09:46');
INSERT INTO `sys_menu` VALUES (6, 2, 'menu', '/system/Menu', 'Grid', 1, 1, '菜单管理', 'Menu', 1, '2021-12-17 17:34:53', '2021-07-22 17:11:10');
INSERT INTO `sys_menu` VALUES (7, 2, 'role', '/system/Role', 'Help', 1, 1, '角色管理', 'Role', 2, '2021-12-17 17:36:32', '2021-07-23 16:09:30');
INSERT INTO `sys_menu` VALUES (8, 2, 'org', '/system/Org', 'SetUp', 1, 1, '组织管理', 'Org', 3, '2021-12-17 17:37:10', '2021-10-23 14:23:11');
INSERT INTO `sys_menu` VALUES (9, 2, 'log', '/system/Log', 'Document', 1, 1, '日志管理', 'Log', 6, NULL, '2021-12-20 10:49:42');
INSERT INTO `sys_menu` VALUES (10, 0, 'message', '/message', 'Comment', 1, 1, '消息管理', 'Message', 2, NULL, '2022-02-24 10:17:44');
INSERT INTO `sys_menu` VALUES (11, 10, 'template', '/message/Template', 'Postcard', 1, 1, '消息模板', 'Template', 0, NULL, '2022-02-24 10:24:46');
INSERT INTO `sys_menu` VALUES (12, 0, 'task', '/task', 'Clock', 1, 1, '任务管理', 'Task Manage', 0, NULL, '2022-02-25 11:24:07');
INSERT INTO `sys_menu` VALUES (13, 12, 'taskConfig', '/task/TaskConfig', 'Odometer', 1, 1, '任务调度', 'TaskConfig', 1, NULL, '2022-02-25 11:25:00');
INSERT INTO `sys_menu` VALUES (14, 12, 'taskLog', '/task/TaskLog', 'Document', 1, 1, '调度日志', 'TaskLog', 2, NULL, '2022-06-28 17:35:05');
INSERT INTO `sys_menu` VALUES (15, 10, 'messageLog', '/message/MessageLog', 'Document', 1, 1, '消息日志', 'MessageLog', 2, NULL, '2022-06-28 17:37:21');
INSERT INTO `sys_menu` VALUES (16, 0, 'api', 'http://localhost:8005/tansci/doc.html', 'Notebook', 2, 1, 'API文档', 'API Docs', 5, '2022-06-29 16:33:20', '2022-06-29 15:21:23');
INSERT INTO `sys_menu` VALUES (17, 0, 'biying', 'https://cn.bing.com', 'Search', 3, 1, '必应链接', 'biying', 6, NULL, '2022-06-29 17:17:44');

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`  (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES (1, 1);
INSERT INTO `sys_menu_role` VALUES (1, 2);
INSERT INTO `sys_menu_role` VALUES (1, 3);
INSERT INTO `sys_menu_role` VALUES (1, 5);
INSERT INTO `sys_menu_role` VALUES (1, 6);
INSERT INTO `sys_menu_role` VALUES (1, 7);
INSERT INTO `sys_menu_role` VALUES (1, 8);
INSERT INTO `sys_menu_role` VALUES (1, 9);
INSERT INTO `sys_menu_role` VALUES (2, 1);
INSERT INTO `sys_menu_role` VALUES (2, 3);
INSERT INTO `sys_menu_role` VALUES (2, 6);
INSERT INTO `sys_menu_role` VALUES (2, 7);
INSERT INTO `sys_menu_role` VALUES (2, 8);
INSERT INTO `sys_menu_role` VALUES (3, 1);
INSERT INTO `sys_menu_role` VALUES (3, 2);
INSERT INTO `sys_menu_role` VALUES (3, 3);
INSERT INTO `sys_menu_role` VALUES (3, 5);
INSERT INTO `sys_menu_role` VALUES (3, 6);
INSERT INTO `sys_menu_role` VALUES (3, 7);
INSERT INTO `sys_menu_role` VALUES (3, 8);
INSERT INTO `sys_menu_role` VALUES (3, 9);
INSERT INTO `sys_menu_role` VALUES (3, 10);
INSERT INTO `sys_menu_role` VALUES (3, 11);
INSERT INTO `sys_menu_role` VALUES (3, 12);
INSERT INTO `sys_menu_role` VALUES (3, 13);
INSERT INTO `sys_menu_role` VALUES (3, 14);
INSERT INTO `sys_menu_role` VALUES (3, 15);
INSERT INTO `sys_menu_role` VALUES (3, 16);
INSERT INTO `sys_menu_role` VALUES (3, 17);
INSERT INTO `sys_menu_role` VALUES (4, 1);
INSERT INTO `sys_menu_role` VALUES (4, 3);
INSERT INTO `sys_menu_role` VALUES (4, 4);
INSERT INTO `sys_menu_role` VALUES (4, 5);
INSERT INTO `sys_menu_role` VALUES (5, 1);
INSERT INTO `sys_menu_role` VALUES (5, 3);

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `parent_id` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '父id',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `del_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '删除状态：0、正常，1、删除',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, '总公司', 0, 1, 0, NULL, '2021-10-23 14:31:30');
INSERT INTO `sys_org` VALUES (2, '兰州子公司', 1, 2, 0, NULL, '2021-10-23 15:08:20');
INSERT INTO `sys_org` VALUES (3, '西安子公司', 1, 3, 0, '2021-10-23 15:17:03', '2021-10-23 15:15:16');

-- ----------------------------
-- Table structure for sys_org_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_role`;
CREATE TABLE `sys_org_role`  (
  `org_id` int(10) NOT NULL COMMENT '组织id',
  `role_id` int(10) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`org_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_org_role
-- ----------------------------
INSERT INTO `sys_org_role` VALUES (1, 1);
INSERT INTO `sys_org_role` VALUES (2, 3);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态',
  `type` int(1) NULL DEFAULT NULL COMMENT '类型',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 1, 0, 'c270251fd4170fa89d166844f43fa0d8', '2021-12-15 17:17:52', '2021-07-23 16:51:14');
INSERT INTO `sys_role` VALUES (2, '普通用户', 1, 0, 'bc3ac26e69731b617eb80274453f6dba', '2021-07-28 15:44:58', '2021-07-23 16:51:01');
INSERT INTO `sys_role` VALUES (3, '管理员', 1, 0, 'bc3ac26e69731b617eb80274453f6dba', '2021-07-23 17:06:56', '2021-07-23 16:42:17');
INSERT INTO `sys_role` VALUES (4, '演示用户', 0, 1, 'bc3ac26e69731b617eb80274453f6dba', NULL, '2021-07-23 16:51:33');
INSERT INTO `sys_role` VALUES (5, '测试1', 1, 0, '37afd8dab98685df81e665dbc2cb78d6', NULL, '2022-01-04 09:57:50');
INSERT INTO `sys_role` VALUES (6, '测试2', 1, 0, '37afd8dab98685df81e665dbc2cb78d6', NULL, '2022-01-04 10:21:57');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `type` int(1) NULL DEFAULT 2 COMMENT '用户类型：0、超级管理员，1、管理员，2、普通用户',
  `gender` int(1) NULL DEFAULT NULL COMMENT '性别：0、男，1、女',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `open_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信唯一标识',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `remember` int(1) NULL DEFAULT 0 COMMENT '是否记住密码：0、否，1、是',
  `del_flag` int(1) NULL DEFAULT 0 COMMENT '删除状态：0、正常，1、已删除',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('37afd8dab98685df81e665dbc2cb78d6', 'fangzq', '$2a$10$o5yVg9Vf1.m3l5V7nNXJQucSLJfQ1aUcNQjFuWDtfe1bWYioNLnHu', '小强', 2, 0, '2021-10-23', '甘肃庆阳', '18712354789', NULL, '', 0, 0, '2021-12-16 11:46:22', '2021-10-23 20:56:29', NULL);
INSERT INTO `sys_user` VALUES ('bc3ac26e69731b617eb80274453f6dba', 'admin', '$2a$10$tlWWfjTObqLsC6ONrhNL/.GIpAoFu205TXPK6xUPHHr1kA/paK4lq', '管理员', 1, 0, '2021-07-09', '甘肃兰州', '18893817562', NULL, '', 0, 0, '2021-12-16 11:45:48', '2021-07-08 12:07:20', NULL);
INSERT INTO `sys_user` VALUES ('c270251fd4170fa89d166844f43fa0d8', 'tanyp', '$2a$10$WXuK1W4bWXr5bxAovO2mLuafYFp2HqphIBi19K9R61ZuZrqh5D3Ty', '小平', 0, 0, '2021-07-17', '甘肃兰州', '18893817562', 'on1PZ6T9fWjQf9SH79mgS0CFfaOA', 'tanyp@163.com', 0, 0, '2021-12-16 11:45:58', '2021-07-17 19:38:29', NULL);

-- ----------------------------
-- Table structure for sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_org`;
CREATE TABLE `sys_user_org`  (
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `org_id` int(11) NOT NULL COMMENT '组织ID',
  PRIMARY KEY (`user_id`, `org_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户组织' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_org
-- ----------------------------
INSERT INTO `sys_user_org` VALUES ('37afd8dab98685df81e665dbc2cb78d6', 3);
INSERT INTO `sys_user_org` VALUES ('bc3ac26e69731b617eb80274453f6dba', 2);
INSERT INTO `sys_user_org` VALUES ('c270251fd4170fa89d166844f43fa0d8', 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('37afd8dab98685df81e665dbc2cb78d6', 2);
INSERT INTO `sys_user_role` VALUES ('bc3ac26e69731b617eb80274453f6dba', 3);
INSERT INTO `sys_user_role` VALUES ('c270251fd4170fa89d166844f43fa0d8', 1);

-- ----------------------------
-- Table structure for task_config
-- ----------------------------
DROP TABLE IF EXISTS `task_config`;
CREATE TABLE `task_config`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务服务名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `task_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务编码',
  `expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务执行规则时间：cron表达式',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、未启动，1、正常',
  `creater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_config
-- ----------------------------
INSERT INTO `task_config` VALUES ('1', 'taskTest1Service', '自定义任务测试', 'T1000214524DFS', '*/20 * * * * ?', 0, 'admin', '2022-02-25 12:58:43', '2022-02-25 10:53:15', NULL);
INSERT INTO `task_config` VALUES ('2', 'taskTest2Service', '自定义任务测试', 'T1000214524DFS', '*/10 * * * * ?', 0, 'admin', '2022-02-25 10:53:12', '2022-02-25 10:53:15', NULL);

-- ----------------------------
-- Table structure for task_config
-- ----------------------------
DROP TABLE IF EXISTS `task_log`;
CREATE TABLE `task_log` (
    `id` varchar(64) NOT NULL COMMENT '主键id',
    `task_id` varchar(64) DEFAULT NULL COMMENT '调度id',
    `server_name` varchar(100) DEFAULT NULL COMMENT '任务服务名称',
    `status` int(1) DEFAULT NULL COMMENT '状态：0、成功，1、失败',
    `execution_time` datetime DEFAULT NULL COMMENT '执行时间',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `remarks` varchar(255) DEFAULT NULL COMMENT '描述',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='调度执行日志';

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板名称',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板内容',
  `type` int(11) NOT NULL COMMENT '模板类型：0：验证码，1：短信通知，2：推广短信，3：国际/港澳台消息',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态：0、审核中，1、通过，2、未通过',
  `creater` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `business_type` int(11) NULL DEFAULT NULL COMMENT '业务类型：0：短信，1：邮件',
  `del_flag` int(11) NULL DEFAULT 0 COMMENT '删除转态：0：正常，1：已删除',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of template
-- ----------------------------
INSERT INTO `template` VALUES ('fd1c585d659e416392163e08002ead95', 'YJ1645680416262', '测试邮件模板', '这是一个测试邮件的模板，参数${param1}，第二个参数${param2}。', 0, 1, 'bc3ac26e69731b617eb80274453f6dba', 1, 0, '2022-02-24 13:27:10', '2022-02-24 13:26:56', '测试邮件模板');

-- ----------------------------
-- Table structure for template_details
-- ----------------------------
DROP TABLE IF EXISTS `template_details`;
CREATE TABLE `template_details`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `state` int(11) NOT NULL COMMENT '状态：0、成功，1：失败',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of template_details
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
