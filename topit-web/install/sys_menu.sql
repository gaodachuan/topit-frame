/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50532
Source Host           : localhost:3306
Source Database       : housemanagesystem

Target Server Type    : MYSQL
Target Server Version : 50532
File Encoding         : 65001

Date: 2014-12-26 18:02:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `Id` int(11) NOT NULL COMMENT '编号。',
  `Name` varchar(50) NOT NULL COMMENT '菜单名称。',
  `RootItemId` int(11) NOT NULL COMMENT '菜单的根节点编号，对应sys_menu_item中的一条记录，根节点是允许删除的。除非删除本菜单。',
  `SystemId` int(11) NOT NULL COMMENT '菜单所属的子系统编号。',
  `UserId` int(11) DEFAULT NULL COMMENT '菜单所属于的特定用户的用户编号。',
  `MaxLevel` int(11) DEFAULT NULL COMMENT '菜单允许的最大层次数。',
  `Description` varchar(200) DEFAULT NULL COMMENT '说明。',
  `IsSystemDefine` int(11) NOT NULL COMMENT '是否系统预定义菜单，0：否，1：是。程序逻辑应该不允许删除预定义菜单。',
  `Inactive` int(11) NOT NULL COMMENT '是否禁用，0：否，1：是。预定义菜单不允许禁用。',
  `Creator` int(11) DEFAULT NULL COMMENT '创建人。',
  `CreateTime` datetime NOT NULL COMMENT '创建时间。',
  `LastEditor` int(11) DEFAULT NULL COMMENT '最后修改人。',
  `LastEditTime` datetime NOT NULL COMMENT '最后修改时间。',
  `Version` int(11) NOT NULL COMMENT '数据记录版本号。',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('9', '主菜单', '1', '0', '0', '2', '系统主菜单', '1', '1', '0', '2014-12-24 15:20:49', '0', '2014-12-23 15:21:02', '0');
INSERT INTO `sys_menu` VALUES ('16', '菜单2', '351', '0', '0', '2', '测试									', '1', '1', '0', '2014-12-25 13:57:46', '0', '2014-12-25 13:57:46', '0');
