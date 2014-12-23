/*
Navicat MySQL Data Transfer

Source Server         : devserver
Source Server Version : 50610
Source Host           : 192.168.4.123:3306
Source Database       : dbtopit

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2014-12-10 10:25:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_menu_item`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_item`;
CREATE TABLE `sys_menu_item` (
  `Id` int(11) NOT NULL COMMENT '菜单节点编号。',
  `Name` varchar(50) NOT NULL COMMENT '菜单项显示名称。',
  `ParentId` int(11) DEFAULT NULL COMMENT '所属组的ID',
  `LeftChildId` int(11) DEFAULT NULL COMMENT '菜单项的第一个左孩子节点编号。',
  `NextBrotherId` int(11) DEFAULT NULL COMMENT '菜单项的下一个兄弟节点编号。',
  `ModuleId` int(11) DEFAULT NULL COMMENT '菜单项模块号。如果为null表示不指向任何模块（适用于菜单文件夹或分隔项）。',
  `TagParams` varchar(50) DEFAULT NULL COMMENT '菜单项附件参数，预留用。',
  `ShortCutKey` varchar(50) DEFAULT NULL COMMENT '快捷键。',
  `Tip` varchar(100) DEFAULT NULL COMMENT '菜单项的提示信息。',
  `IconFile` varchar(50) DEFAULT NULL COMMENT '图标文件。',
  `Creator` int(11) DEFAULT NULL COMMENT '创建人。',
  `CreateTime` datetime NOT NULL COMMENT '创建时间。',
  `LastEditor` int(11) DEFAULT NULL COMMENT '最后修改人。',
  `state` varchar(11) DEFAULT NULL,
  `LastEditTime` datetime NOT NULL COMMENT '最后修改时间。',
  `Version` int(11) NOT NULL COMMENT '数据记录版本号。',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_item
-- ----------------------------
INSERT INTO `sys_menu_item` VALUES ('1', '主菜单', '-1', '307', '-1', '-1', null, null, '主菜单', null, '1', '2014-12-10 09:14:27', '1', 'closed', '2014-12-22 09:14:42', '3');
INSERT INTO `sys_menu_item` VALUES ('261', '张毅', '1', '310', '262', '-1', null, null, '张毅', null, '0', '2014-12-10 09:15:12', '0', 'closed', '2014-12-10 09:15:12', '14');
INSERT INTO `sys_menu_item` VALUES ('262', '邱桂', '1', '271', '276', '-1', null, null, '邱桂', null, '0', '2014-12-10 09:15:23', '0', 'closed', '2014-12-10 09:15:23', '6');
INSERT INTO `sys_menu_item` VALUES ('271', '用户组', '262', '-1', '-1', '24', null, null, '2321321323', null, '0', '2014-12-10 09:16:06', '0', 'open', '2014-12-10 09:16:06', '2');
INSERT INTO `sys_menu_item` VALUES ('276', '周方旭', '1', '277', '-1', '-1', null, null, '周方旭', null, '0', '2014-12-10 09:17:50', '0', 'closed', '2014-12-10 09:17:50', '4');
INSERT INTO `sys_menu_item` VALUES ('277', '用户组', '276', '-1', '-1', '24', null, null, '2321321323', null, '0', '2014-12-10 09:17:54', '0', 'open', '2014-12-10 09:17:54', '2');
INSERT INTO `sys_menu_item` VALUES ('307', '高达川', '1', '308', '261', '-1', null, null, '高达川', null, '0', '2014-12-10 10:22:33', '0', 'closed', '2014-12-10 10:22:33', '3');
INSERT INTO `sys_menu_item` VALUES ('308', '系统选项项', '307', '-1', '309', '25', null, null, '系统选项', null, '0', '2014-12-10 10:22:50', '0', 'open', '2014-12-10 10:22:50', '1');
INSERT INTO `sys_menu_item` VALUES ('309', '用户组', '307', '-1', '-1', '24', null, null, '2321321323', null, '0', '2014-12-10 10:22:53', '0', 'open', '2014-12-10 10:22:53', '0');
INSERT INTO `sys_menu_item` VALUES ('310', '模块维护', '261', '-1', '311', '22', null, null, '12121', null, '0', '2014-12-10 10:22:57', '0', 'open', '2014-12-10 10:22:57', '1');
INSERT INTO `sys_menu_item` VALUES ('311', '系统选项项', '261', '-1', '-1', '25', null, null, '系统选项', null, '0', '2014-12-10 10:22:59', '0', 'open', '2014-12-10 10:22:59', '0');
