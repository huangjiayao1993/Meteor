/*
SQLyog Professional v12.08 (64 bit)
MySQL - 8.0.24 : Database - meteor
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`meteor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `meteor`;

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_dict` */

/*Table structure for table `sys_dict_data` */

DROP TABLE IF EXISTS `sys_dict_data`;

CREATE TABLE `sys_dict_data` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `value` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_dict_data` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` int DEFAULT '0',
  `permission` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `component_path` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `icon` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sort` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`pid`,`name`,`type`,`permission`,`path`,`component_path`,`icon`,`sort`) values ('1679064215760052226',NULL,'组织架构',0,'sys','sys',NULL,NULL,0),('1679064318726021121','1679064215760052226','组织管理',1,'sys:org:list','org','system/org/index',NULL,0),('1679064335582928898','1679064215760052226','用户管理',1,'sys:user:list','user','system/user/index',NULL,0),('1679064346794303489',NULL,'权限管理',0,'sys','sys',NULL,NULL,0),('1679064358316056578','1679064346794303489','角色管理',1,'sys:role:list','role','system/role/index',NULL,0),('1679064370248851458','1679064346794303489','菜单管理',1,'sys:menu:list','menu','system/menu/index',NULL,0),('1679064382471053313',NULL,'日志管理',0,'log','log',NULL,NULL,0),('1681201183688097793','1679064382471053313','登录日志',1,'log:login:list','home','system/org/index',NULL,0),('1681875437937209346','1679064335582928898','新增',2,'sys:user:create','',NULL,NULL,0),('1681918191618732034','1679064335582928898','编辑',2,'sys:user:update','',NULL,NULL,0),('1681918263643320322','1679064335582928898','删除',2,'sys:user:remove','',NULL,NULL,0),('1681918322116112386','1679064335582928898','角色',2,'sys:user:role:authorize','',NULL,NULL,0),('1681918418480246786','1679064335582928898','重置密码',2,'sys:user:resetPassword','',NULL,NULL,0),('1681918490903293953','1679064335582928898','修改密码',2,'sys:user:updatePassword','',NULL,NULL,0),('1681918715030122497','1679064358316056578','新增',2,'sys:role:create','',NULL,NULL,0),('1681918789403521025','1679064358316056578','编辑',2,'sys:role:update','',NULL,NULL,0),('1681918864968101889','1679064358316056578','删除',2,'sys:role:remove','',NULL,NULL,0),('1681918919208841218','1679064358316056578','授权',2,'sys:role:menu:authorize','',NULL,NULL,0),('1681919078336540674','1679064318726021121','新增',2,'sys:org:create','',NULL,NULL,0),('1681919136863858689','1679064318726021121','编辑',2,'sys:org:update','',NULL,NULL,0),('1681919198604013569','1679064318726021121','删除',2,'sys:org:remove','',NULL,NULL,0),('1681919318468833282','1679064370248851458','新增',2,'sys:menu:create','',NULL,NULL,0),('1681919386135539713','1679064370248851458','编辑',2,'sys:menu:update','',NULL,NULL,0),('1681919433229185025','1679064370248851458','删除',2,'sys:menu:remove','',NULL,NULL,0);

/*Table structure for table `sys_org` */

DROP TABLE IF EXISTS `sys_org`;

CREATE TABLE `sys_org` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` int DEFAULT '0',
  `sort` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_org` */

insert  into `sys_org`(`id`,`pid`,`name`,`type`,`sort`) values ('1673583585110724610',NULL,'Meteor集团',0,0),('1679065280031137794','1673583585110724610','广西区分公司',1,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `code` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`code`) values ('1611111111111111111','超级管理员','role_admin'),('1679063381517180929','普通用户','common_user');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `menu_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values ('1679075166844534786','1679063381517180929','1679064335582928898'),('1679075166844534787','1679063381517180929','1679064215760052226'),('1681920948312387586','1611111111111111111','1679064215760052226'),('1681920948329164802','1611111111111111111','1679064335582928898'),('1681920948329164803','1611111111111111111','1679064346794303489'),('1681920948329164804','1611111111111111111','1679064358316056578'),('1681920948329164805','1611111111111111111','1679064370248851458'),('1681920948366913537','1611111111111111111','1679064382471053313'),('1681920948366913538','1611111111111111111','1681201183688097793'),('1681920948375302146','1611111111111111111','1679064318726021121'),('1681920948383690753','1611111111111111111','1681919078336540674'),('1681920948383690754','1611111111111111111','1681919136863858689'),('1681920948383690755','1611111111111111111','1681919198604013569'),('1681920948383690756','1611111111111111111','1681875437937209346'),('1681920948400467970','1611111111111111111','1681918191618732034'),('1681920948400467971','1611111111111111111','1681918263643320322'),('1681920948400467972','1611111111111111111','1681918322116112386'),('1681920948400467973','1611111111111111111','1681918490903293953'),('1681920948438216706','1611111111111111111','1681918418480246786'),('1681920948446605313','1611111111111111111','1681918715030122497'),('1681920948446605314','1611111111111111111','1681918789403521025'),('1681920948454993921','1611111111111111111','1681918864968101889'),('1681920948463382530','1611111111111111111','1681918919208841218'),('1681920948463382531','1611111111111111111','1681919318468833282'),('1681920948480159745','1611111111111111111','1681919386135539713'),('1681920948480159746','1611111111111111111','1681919433229185025');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `salt` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `org_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nickname` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `realname` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mobile` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `avatar` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gender` tinyint(1) DEFAULT '0',
  `status` tinyint(1) DEFAULT '0',
  `deleted` tinyint(1) DEFAULT '0',
  `reg_time` datetime DEFAULT NULL,
  `enable_used` tinyint(1) DEFAULT '0',
  `enable_start_time` datetime DEFAULT NULL,
  `enable_end_time` datetime DEFAULT NULL,
  `version` bigint DEFAULT '0',
  `login_time` datetime DEFAULT NULL,
  `create_by` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`salt`,`org_id`,`nickname`,`realname`,`mobile`,`avatar`,`gender`,`status`,`deleted`,`reg_time`,`enable_used`,`enable_start_time`,`enable_end_time`,`version`,`login_time`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values ('1600000000000000000','admin','$2a$10$wTkKBgNmLaSaf4bkywtxaOEgaTrEl2CIZLzLGhHBbQxUThKU.9UQ.','$2a$10$wTkKBgNmLaSaf4bkywtxaO','1673583585110724610','超级管理员','超级管理员','13122223333','https://meteor-cloud.obs.cn-south-1.myhuaweicloud.com/avatar/987654321/955f1740380a4706b9a7fd4d84902f2b1676455310150.png',0,0,0,NULL,0,NULL,NULL,11,'2023-07-20 14:55:58',NULL,NULL,'1600000000000000000','2023-07-20 14:55:58',NULL),('1679072593479639041','test01','$2a$10$wTkKBgNmLaSaf4bkywtxaOEgaTrEl2CIZLzLGhHBbQxUThKU.9UQ.','$2a$10$wTkKBgNmLaSaf4bkywtxaO','1679065280031137794','测试用户01','测试用户01','13122223333',NULL,0,0,0,NULL,0,NULL,NULL,5,'2023-07-20 11:18:06','1234567890','2023-07-12 18:17:57','1679072593479639041','2023-07-20 11:18:06',NULL);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values ('1676798180109475842','1600000000000000000','1611111111111111111'),('1679072626711109633','1679072593479639041','1679063381517180929');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
