/*
SQLyog Professional v12.08 (64 bit)
MySQL - 8.0.13 : Database - meteor
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`meteor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `meteor`;

/*Table structure for table `ct_member` */

DROP TABLE IF EXISTS `ct_member`;

CREATE TABLE `ct_member` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员',
  `account_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账号ID',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `realname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别,0:女，1:男',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态,0:正常，1:停用',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除,0:未删除，1:已删除',
  `reg_time` datetime DEFAULT NULL COMMENT '注册时间',
  `enable_used` tinyint(1) DEFAULT '0' COMMENT '是否启用期限,0:否，1:是',
  `enable_start_time` datetime DEFAULT NULL COMMENT '期限起始时间',
  `enable_end_time` datetime DEFAULT NULL COMMENT '期限结束时间',
  `version` bigint(20) DEFAULT '0' COMMENT '版本号',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `ct_member` */

insert  into `ct_member`(`id`,`account_id`,`nickname`,`realname`,`mobile`,`avatar`,`gender`,`status`,`deleted`,`reg_time`,`enable_used`,`enable_start_time`,`enable_end_time`,`version`,`login_time`) values ('1600000000000000001','1500000000000000000','超级管理员','超级管理员','13122223333',NULL,0,0,0,NULL,0,NULL,NULL,1,'2023-09-15 16:41:25');

/*Table structure for table `sys_account` */

DROP TABLE IF EXISTS `sys_account`;

CREATE TABLE `sys_account` (
  `id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `username` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '盐',
  `wx_openid` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '微信openid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_account` */

insert  into `sys_account`(`id`,`username`,`password`,`salt`,`wx_openid`) values ('1500000000000000000','admin','$2a$10$pccanNk4DWywvNx6YLjEQul/bbZGJQL1FxFmocffSMJ4t2kAcvLQ2','$2a$10$pccanNk4DWywvNx6YLjEQu',NULL),('1679072593479639042','test01','$2a$10$92VTxgzVgiqwh5bUkpggOeJmIzW.uqrUbmmhc0YOZKKqw0n6WcBti','$2a$10$92VTxgzVgiqwh5bUkpggOe',NULL),('1701148681932304386','ceshi1','$2a$10$.71LcSPz9jVNDh/j9Ue3XOf4IskrSn/wSs6OsHHFFJlMr9k1mO0uG','$2a$10$.71LcSPz9jVNDh/j9Ue3XO',NULL),('1701150637782396929','ceshi2','$2a$10$qDooP2Pvb16jzvu51zYIq.DOf4q5u2G/o2HqeXZ1Fkh4zkrc4Jm3a','$2a$10$qDooP2Pvb16jzvu51zYIq.',NULL);

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置',
  `key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置key键',
  `value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置value值',
  `desc` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_config` */

insert  into `sys_config`(`id`,`key`,`value`,`desc`) values ('1687284031457284091','oss_type1','0','OSS云存储使用类型-动态配置项，可选值为：HUAWEI、ALI、TENCENT、QINIU、MINIO，如值不在列表或无此动态配置，系统默认HUAWEI'),('1687284031457284092','oss_type2','1','OSS云存储使用类型-动态配置项，可选值为：HUAWEI、ALI、TENCENT、QINIU、MINIO，如值不在列表或无此动态配置，系统默认HUAWEI'),('1687284031457284093','oss_type3','0','OSS云存储使用类型-动态配置项，可选值为：HUAWEI、ALI、TENCENT、QINIU、MINIO，如值不在列表或无此动态配置，系统默认HUAWEI'),('1687284031457284094','oss_type4','1','OSS云存储使用类型-动态配置项，可选值为：HUAWEI、ALI、TENCENT、QINIU、MINIO，如值不在列表或无此动态配置，系统默认HUAWEI'),('1687284031457284095','oss_type5','HUAWEI','OSS云存储使用类型-动态配置项，可选值为：HUAWEI、ALI、TENCENT、QINIU、MINIO，如值不在列表或无此动态配置，系统默认HUAWEI'),('1687284031457284096','oss_type6','HUAWEI','OSS云存储使用类型-动态配置项，可选值为：HUAWEI、ALI、TENCENT、QINIU、MINIO，如值不在列表或无此动态配置，系统默认HUAWEI'),('1687284031457284097','oss_type7','HUAWEI','OSS云存储使用类型-动态配置项，可选值为：HUAWEI、ALI、TENCENT、QINIU、MINIO，如值不在列表或无此动态配置，系统默认HUAWEI'),('1687284031457284098','open_captcha','true','是否开启验证码'),('1687284031457284099','oss_type9','HUAWEI','OSS云存储使用类型-动态配置项，可选值为：HUAWEI、ALI、TENCENT、QINIU、MINIO，如值不在列表或无此动态配置，系统默认HUAWEI'),('1687284031457284100','oss_type10','HUAWEI','OSS云存储使用类型-动态配置项，可选值为：HUAWEI、ALI、TENCENT、QINIU、MINIO，如值不在列表或无此动态配置，系统默认HUAWEI'),('1687284031457284101','oss_type11','HUAWEI','OSS云存储使用类型-动态配置项，可选值为：HUAWEI、ALI、TENCENT、QINIU、MINIO，如值不在列表或无此动态配置，系统默认HUAWEI');

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_dict` */

/*Table structure for table `sys_dict_data` */

DROP TABLE IF EXISTS `sys_dict_data`;

CREATE TABLE `sys_dict_data` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典数据',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典数据名称',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典数据值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_dict_data` */

/*Table structure for table `sys_login_log` */

DROP TABLE IF EXISTS `sys_login_log`;

CREATE TABLE `sys_login_log` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录日志',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账号',
  `device` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备',
  `login_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录方式',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ip地址',
  `create_time` datetime DEFAULT NULL COMMENT '请求时间',
  `success` tinyint(1) DEFAULT '0' COMMENT '请求是否成功,0:失败，1:成功',
  `response_json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '响应数据json',
  `reason` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '异常原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_login_log` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单',
  `pid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '上级id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单名称',
  `type` int(11) DEFAULT '0' COMMENT '菜单类型,0:目录，1:菜单，2:按钮',
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '访问地址',
  `component_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件地址',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`pid`,`name`,`type`,`permission`,`path`,`component_path`,`icon`,`sort`) values ('1679064215760052226',NULL,'组织架构',0,'sys','sys',NULL,'TeamOutlined',0),('1679064318726021121','1679064215760052226','组织管理',1,'sys:org:list','org','system/org/index','TeamOutlined',0),('1679064335582928898','1679064215760052226','用户管理',1,'sys:user:list','user','system/user/index','UserOutlined',1),('1679064346794303489',NULL,'权限管理',0,'sys','sys',NULL,'ApiOutlined',1),('1679064358316056578','1679064346794303489','角色管理',1,'sys:role:list','role','system/role/index','TrademarkOutlined',0),('1679064370248851458','1679064346794303489','菜单管理',1,'sys:menu:list','menu','system/menu/index','MenuUnfoldOutlined',1),('1679064382471053313',NULL,'日志管理',0,'log','sys',NULL,'InfoCircleOutlined',3),('1681201183688097793','1679064382471053313','登录日志',1,'sys:log:login:list','log/login','system/log/login','LoginOutlined',0),('1681875437937209346','1679064335582928898','新增',2,'sys:user:create','',NULL,NULL,0),('1681918191618732034','1679064335582928898','编辑',2,'sys:user:update','',NULL,NULL,0),('1681918263643320322','1679064335582928898','删除',2,'sys:user:remove','',NULL,NULL,0),('1681918322116112386','1679064335582928898','角色',2,'sys:user:role:authorize','',NULL,NULL,0),('1681918418480246786','1679064335582928898','重置密码',2,'sys:user:resetPassword','',NULL,NULL,0),('1681918490903293953','1679064335582928898','修改密码',2,'sys:user:updatePassword','',NULL,NULL,0),('1681918715030122497','1679064358316056578','新增',2,'sys:role:create','',NULL,NULL,0),('1681918789403521025','1679064358316056578','编辑',2,'sys:role:update','',NULL,NULL,0),('1681918864968101889','1679064358316056578','删除',2,'sys:role:remove','',NULL,NULL,0),('1681918919208841218','1679064358316056578','授权',2,'sys:role:menu:authorize','',NULL,NULL,0),('1681919078336540674','1679064318726021121','新增',2,'sys:org:create','',NULL,NULL,0),('1681919136863858689','1679064318726021121','编辑',2,'sys:org:update','',NULL,NULL,0),('1681919198604013569','1679064318726021121','删除',2,'sys:org:remove','',NULL,NULL,0),('1681919318468833282','1679064370248851458','新增',2,'sys:menu:create','',NULL,NULL,0),('1681919386135539713','1679064370248851458','编辑',2,'sys:menu:update','',NULL,NULL,0),('1681919433229185025','1679064370248851458','删除',2,'sys:menu:remove','',NULL,NULL,0),('1682673865056522242','1679064382471053313','操作日志',1,'sys:log:operation:list','sys/log/operation','system/log/operation','ExclamationCircleOutlined',1),('1683323051362639873',NULL,'系统管理',0,NULL,'sys',NULL,'SettingOutlined',2),('1683323264953376770','1683323051362639873','系统配置',1,'sys:config:list','config','system/config/index','SettingOutlined',0),('1683323589592506369','1683323051362639873','字典管理',1,'sys:dict:list','dict','system/dict/type','BookOutlined',1),('1683412629817892865','1683323264953376770','新增',2,'sys:config:create','',NULL,NULL,0),('1683412698415734785','1683323264953376770','编辑',2,'sys:config:update','',NULL,NULL,0),('1683412752539033602','1683323264953376770','删除',2,'sys:config:remove','',NULL,NULL,0),('1683412971141963778','1683323264953376770','缓存操作',2,'sys:config:refresh','',NULL,NULL,0),('1683413098963378178','1683323589592506369','新增',2,'sys:dict:create','',NULL,NULL,0),('1683413162083459073','1683323589592506369','编辑',2,'sys:dict:update','',NULL,NULL,0),('1683413245919207425','1683323589592506369','删除',2,'sys:dict:remove','',NULL,NULL,0),('1683413320183554050','1683323589592506369','缓存操作',2,'sys:dict:refresh','',NULL,NULL,0);

/*Table structure for table `sys_operation_log` */

DROP TABLE IF EXISTS `sys_operation_log`;

CREATE TABLE `sys_operation_log` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作日志',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户ID',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账号',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ip地址',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求URL',
  `controller` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '控制器',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '方法',
  `params_json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求数据json',
  `create_time` datetime DEFAULT NULL COMMENT '请求时间',
  `success` tinyint(1) DEFAULT '0' COMMENT '请求是否成功,0:失败，1:成功',
  `response_json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '响应数据json',
  `duration` bigint(20) DEFAULT '0' COMMENT '时长,单位秒',
  `reason` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '异常原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_operation_log` */

/*Table structure for table `sys_org` */

DROP TABLE IF EXISTS `sys_org`;

CREATE TABLE `sys_org` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织',
  `pid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '上级id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组织名称',
  `type` int(11) DEFAULT '0' COMMENT '组织类型,0:部门，1:公司',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_org` */

insert  into `sys_org`(`id`,`pid`,`name`,`type`,`sort`) values ('1673583585110724610',NULL,'Meteor集团',0,0),('1679065280031137794','1673583585110724610','广西区分公司',1,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`code`) values ('1611111111111111111','超级管理员','role_admin'),('1679063381517180929','普通用户','common_user');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色菜单',
  `role_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `menu_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values ('1679075166844534786','1679063381517180929','1679064335582928898'),('1679075166844534787','1679063381517180929','1679064215760052226'),('1684739028054900738','1611111111111111111','1679064335582928898'),('1684739028054900739','1611111111111111111','1679064346794303489'),('1684739028054900740','1611111111111111111','1679064358316056578'),('1684739028054900741','1611111111111111111','1679064370248851458'),('1684739028054900742','1611111111111111111','1679064382471053313'),('1684739028054900743','1611111111111111111','1681201183688097793'),('1684739028092649473','1611111111111111111','1681875437937209346'),('1684739028092649474','1611111111111111111','1681918191618732034'),('1684739028092649475','1611111111111111111','1681918263643320322'),('1684739028092649476','1611111111111111111','1681918322116112386'),('1684739028092649477','1611111111111111111','1681918490903293953'),('1684739028092649478','1611111111111111111','1681918418480246786'),('1684739028101038081','1611111111111111111','1681918715030122497'),('1684739028101038082','1611111111111111111','1681918789403521025'),('1684739028101038083','1611111111111111111','1681918864968101889'),('1684739028101038084','1611111111111111111','1681918919208841218'),('1684739028101038085','1611111111111111111','1681919318468833282'),('1684739028101038086','1611111111111111111','1681919386135539713'),('1684739028101038087','1611111111111111111','1681919433229185025'),('1684739028101038088','1611111111111111111','1682673865056522242'),('1684739028109426689','1611111111111111111','1683323051362639873'),('1684739028109426690','1611111111111111111','1683323589592506369'),('1684739028109426691','1611111111111111111','1683323264953376770'),('1684739028109426692','1611111111111111111','1683412629817892865'),('1684739028109426693','1611111111111111111','1683412698415734785'),('1684739028109426694','1611111111111111111','1683412971141963778'),('1684739028109426695','1611111111111111111','1683412752539033602'),('1684739028109426696','1611111111111111111','1683413098963378178'),('1684739028109426697','1611111111111111111','1683413162083459073'),('1684739028109426698','1611111111111111111','1683413245919207425'),('1684739028109426699','1611111111111111111','1683413320183554050'),('1684739028109426700','1611111111111111111','1679064215760052226'),('1684739028117815298','1611111111111111111','1679064318726021121'),('1684739028117815299','1611111111111111111','1681919078336540674'),('1684739028117815300','1611111111111111111','1681919136863858689'),('1684739028117815301','1611111111111111111','1681919198604013569');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户',
  `account_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账号ID',
  `org_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组织ID',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `realname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别,0:女，1:男',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态,0:正常，1:停用',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除,0:未删除，1:已删除',
  `reg_time` datetime DEFAULT NULL COMMENT '注册时间',
  `enable_used` tinyint(1) DEFAULT '0' COMMENT '是否启用期限,0:否，1:是',
  `enable_start_time` datetime DEFAULT NULL COMMENT '期限起始时间',
  `enable_end_time` datetime DEFAULT NULL COMMENT '期限结束时间',
  `version` bigint(20) DEFAULT '0' COMMENT '版本号',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`account_id`,`org_id`,`nickname`,`realname`,`mobile`,`avatar`,`gender`,`status`,`deleted`,`reg_time`,`enable_used`,`enable_start_time`,`enable_end_time`,`version`,`login_time`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values ('1600000000000000000','1500000000000000000','1673583585110724610','超级管理员','超级管理员','13122223333','https://meteor-cloud.obs.cn-south-1.myhuaweicloud.com/avatar/987654321/955f1740380a4706b9a7fd4d84902f2b1676455310150.png',0,0,0,NULL,0,NULL,NULL,66,'2023-09-15 17:27:55',NULL,NULL,'1600000000000000000','2023-09-14 09:49:20',NULL),('1679072593479639041','1679072593479639042','1679065280031137794','测试用户01','测试用户01','13122223333',NULL,0,0,0,NULL,0,NULL,NULL,6,'2023-08-10 18:06:44','1234567890','2023-07-12 18:17:57','1679072593479639041','2023-08-10 18:06:44',NULL),('1701148681957470210','1701148681932304386','1679065280031137794','ceshi1','ceshi1','13122223334',NULL,1,0,1,NULL,0,NULL,NULL,0,NULL,'1600000000000000000','2023-09-11 16:20:26','1600000000000000000','2023-09-11 16:21:38',NULL),('1701150637782396930','1701150637782396929','1679065280031137794','ceshi2','ceshi2','13122223334',NULL,1,0,0,NULL,0,NULL,NULL,0,NULL,'1600000000000000000','2023-09-11 16:28:13',NULL,NULL,NULL);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户角色',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values ('1684733827549954050','1679072593479639041','1679063381517180929'),('1684736437719859201','1600000000000000000','1611111111111111111');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
