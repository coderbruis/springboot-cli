/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : distributed_mall

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-07-04 12:42:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(128) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clientdetails
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NULL DEFAULT NULL,
  `lastModifiedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client', null, '$2a$10$r0g/vsua5ohX/pi4Wo561O9cX2.oKe4ctJvaPl6rQRYZJ8rM8D0xW', 'all', 'authorization_code,password', 'http://www.baidu.com', null, null, null, null, 'false');
INSERT INTO `oauth_client_details` VALUES ('client2', null, '$2a$10$r0g/vsua5ohX/pi4Wo561O9cX2.oKe4ctJvaPl6rQRYZJ8rM8D0xW', 'user_info', 'authorization_code', 'http://localhost:8903/login', null, null, null, null, 'user_info');
INSERT INTO `oauth_client_details` VALUES ('CouponSystem', null, '$2a$10$dYRcFip80f0jIKGzRGulFelK12036xWQKgajanfxT65QB4htsEXNK', 'user_info', 'authorization_code', 'http://localhost:8082/login', null, null, null, null, 'user_info');
INSERT INTO `oauth_client_details` VALUES ('MemberSystem', null, '$2a$10$dYRcFip80f0jIKGzRGulFelK12036xWQKgajanfxT65QB4htsEXNK', 'user_info', 'authorization_code', 'http://localhost:8081/login', null, null, null, null, 'user_info');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '?????????',
  `name` varchar(64) NOT NULL COMMENT '????????????',
  `enname` varchar(64) NOT NULL COMMENT '??????????????????',
  `url` varchar(255) NOT NULL COMMENT '????????????',
  `description` varchar(200) DEFAULT NULL COMMENT '??????',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='?????????';

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES ('37', '0', '????????????', 'System', '/', null, '2019-04-04 23:22:54', '2019-04-04 23:22:56');
INSERT INTO `tb_permission` VALUES ('38', '37', '????????????', 'SystemUser', '/users/', null, '2019-04-04 23:25:31', '2019-04-04 23:25:33');
INSERT INTO `tb_permission` VALUES ('39', '38', '????????????', 'SystemUserView', '/users/view/**', null, '2019-04-04 15:30:30', '2019-04-04 15:30:43');
INSERT INTO `tb_permission` VALUES ('40', '38', '????????????', 'SystemUserInsert', '/users/insert/**', null, '2019-04-04 15:30:31', '2019-04-04 15:30:44');
INSERT INTO `tb_permission` VALUES ('41', '38', '????????????', 'SystemUserUpdate', '/users/update/**', null, '2019-04-04 15:30:32', '2019-04-04 15:30:45');
INSERT INTO `tb_permission` VALUES ('42', '38', '????????????', 'SystemUserDelete', '/users/delete/**', null, '2019-04-04 15:30:48', '2019-04-04 15:30:45');
INSERT INTO `tb_permission` VALUES ('44', '37', '????????????', 'SystemOrder', '/order/', null, '2019-04-06 18:23:58', '2019-04-06 18:24:00');
INSERT INTO `tb_permission` VALUES ('45', '44', '????????????', 'SystemOrderView', '/order/view/**', null, '2019-04-06 23:49:39', '2019-04-06 23:49:41');
INSERT INTO `tb_permission` VALUES ('46', '44', '????????????', 'SystemOrderInsert', '/order/insert/**', null, '2019-04-06 23:51:00', '2019-04-06 23:51:02');
INSERT INTO `tb_permission` VALUES ('47', '44', '????????????', 'SystemOrderUpdate', '/order/update/**', null, '2019-04-06 23:51:04', '2019-04-06 23:51:06');
INSERT INTO `tb_permission` VALUES ('48', '44', '????????????', 'SystemOrderDelete', '/order/delete/**', null, '2019-04-06 23:51:08', '2019-04-06 23:51:10');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '?????????',
  `name` varchar(64) NOT NULL COMMENT '????????????',
  `enname` varchar(64) NOT NULL COMMENT '??????????????????',
  `description` varchar(200) DEFAULT NULL COMMENT '??????',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='?????????';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('37', '0', '???????????????', 'admin', null, '2019-04-04 23:22:03', '2019-04-04 23:22:05');

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '?????? ID',
  `permission_id` bigint(20) NOT NULL COMMENT '?????? ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='???????????????';

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES ('37', '37', '37');
INSERT INTO `tb_role_permission` VALUES ('38', '37', '38');
INSERT INTO `tb_role_permission` VALUES ('39', '37', '39');
INSERT INTO `tb_role_permission` VALUES ('40', '37', '40');
INSERT INTO `tb_role_permission` VALUES ('41', '37', '41');
INSERT INTO `tb_role_permission` VALUES ('42', '37', '42');
INSERT INTO `tb_role_permission` VALUES ('43', '37', '44');
INSERT INTO `tb_role_permission` VALUES ('44', '37', '45');
INSERT INTO `tb_role_permission` VALUES ('45', '37', '46');
INSERT INTO `tb_role_permission` VALUES ('46', '37', '47');
INSERT INTO `tb_role_permission` VALUES ('47', '37', '48');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '?????????',
  `password` varchar(64) NOT NULL COMMENT '?????????????????????',
  `phone` varchar(20) DEFAULT NULL COMMENT '???????????????',
  `email` varchar(50) DEFAULT NULL COMMENT '????????????',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='?????????';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('37', 'admin', '$2a$10$xTvic9aNdopwgyXCYb13zOAbXEZ4tX5kevEoLFOP8n7evNGmYm/2K', '15888888888', 'lee.lusifer@gmail.com', '2019-04-04 23:21:27', '2019-04-04 23:21:29');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '?????? ID',
  `role_id` bigint(20) NOT NULL COMMENT '?????? ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='???????????????';

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('37', '37', '37');
