/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50532
Source Host           : localhost:3306
Source Database       : housemanagesystem

Target Server Type    : MYSQL
Target Server Version : 50532
File Encoding         : 65001

Date: 2014-12-26 18:01:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_menu_item`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_item`;
CREATE TABLE `sys_menu_item` (
  `Id` int(11) NOT NULL COMMENT '菜单节点编号。',
  `MenuId` int(11) DEFAULT '0' COMMENT '菜单号',
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
INSERT INTO `sys_menu_item` VALUES ('1', '9', '主菜单', '-1', '344', '-1', '-1', null, null, '主菜单', '', '1', '2014-12-10 09:14:27', '1', 'closed', '2014-12-22 09:14:42', '9');
INSERT INTO `sys_menu_item` VALUES ('344', '9', '面板一', '1', '347', '345', '-1', null, null, '面板一', '', '0', '2014-12-25 13:56:05', '0', 'closed', '2014-12-25 13:56:05', '4');
INSERT INTO `sys_menu_item` VALUES ('345', '9', '面板二', '1', '349', '358', '-1', null, null, '面板二', '', '0', '2014-12-25 13:56:26', '0', 'closed', '2014-12-25 13:56:26', '3');
INSERT INTO `sys_menu_item` VALUES ('346', '9', '系统选项项', '344', '-1', '348', '25', null, null, '系统选项', 'large-picture.png', '0', '2014-12-25 13:56:30', '0', 'open', '2014-12-25 13:56:30', '12');
INSERT INTO `sys_menu_item` VALUES ('347', '9', '模块维护', '344', '-1', '346', '22', null, null, '12121', 'large-clipart.png', '0', '2014-12-25 13:56:32', '0', 'open', '2014-12-25 13:56:32', '14');
INSERT INTO `sys_menu_item` VALUES ('348', '9', '用户组', '344', '-1', '-1', '24', null, null, '2321321323', 'large-chart.png', '0', '2014-12-25 13:56:33', '0', 'open', '2014-12-25 13:56:33', '9');
INSERT INTO `sys_menu_item` VALUES ('349', '9', '模块维护', '345', '-1', '350', '22', null, null, '12121', 'large-smartart.png', '0', '2014-12-25 13:56:36', '0', 'open', '2014-12-25 13:56:36', '4');
INSERT INTO `sys_menu_item` VALUES ('350', '9', '系统选项项', '345', '-1', '-1', '25', null, null, '系统选项', 'large-chart.png', '0', '2014-12-25 13:56:37', '0', 'open', '2014-12-25 13:56:37', '3');
INSERT INTO `sys_menu_item` VALUES ('351', '16', '菜单2', '-1', '352', '-1', '-1', null, null, null, '', '0', '2014-12-25 13:57:46', '0', 'closed', '2014-12-25 13:57:46', '1');
INSERT INTO `sys_menu_item` VALUES ('352', '16', '测试1', '351', '354', '353', '-1', null, null, '测试1', '', '0', '2014-12-25 13:58:04', '0', 'closed', '2014-12-25 13:58:04', '4');
INSERT INTO `sys_menu_item` VALUES ('353', '16', '测试2', '351', '356', '-1', '-1', null, null, '测试2', '', '0', '2014-12-25 13:58:11', '0', 'closed', '2014-12-25 13:58:11', '2');
INSERT INTO `sys_menu_item` VALUES ('354', '16', '系统选项项', '352', '-1', '355', '25', null, null, '系统选项', 'large-shapes.png', '0', '2014-12-25 13:58:16', '0', 'open', '2014-12-25 13:58:16', '1');
INSERT INTO `sys_menu_item` VALUES ('355', '16', '用户组', '352', '-1', '357', '24', null, null, '2321321323', 'large-shapes.png', '0', '2014-12-25 13:58:21', '0', 'open', '2014-12-25 13:58:21', '1');
INSERT INTO `sys_menu_item` VALUES ('356', '16', '系统选项项', '353', '-1', '-1', '25', null, null, '系统选项', 'large-shapes.png', '0', '2014-12-25 13:58:24', '0', 'open', '2014-12-25 13:58:24', '2');
INSERT INTO `sys_menu_item` VALUES ('357', '16', '模块维护', '352', '-1', '-1', '22', null, null, '12121', 'large-shapes.png', '0', '2014-12-25 13:58:25', '0', 'open', '2014-12-25 13:58:25', '1');
INSERT INTO `sys_menu_item` VALUES ('358', '9', '面板三', '1', '361', '-1', '-1', null, null, '面板三', null, '0', '2014-12-26 15:01:02', '0', 'closed', '2014-12-26 15:01:02', '3');
INSERT INTO `sys_menu_item` VALUES ('359', '9', '模块维护', '358', '-1', '360', '22', null, null, '12121', 'large-clipart.png', '0', '2014-12-26 15:01:06', '0', 'open', '2014-12-26 15:01:06', '4');
INSERT INTO `sys_menu_item` VALUES ('360', '9', '系统选项项', '358', '-1', '-1', '25', null, null, '系统选项', 'large-smartart.png', '0', '2014-12-26 15:01:08', '0', 'open', '2014-12-26 15:01:08', '4');
INSERT INTO `sys_menu_item` VALUES ('361', '9', '用户组', '358', '-1', '359', '24', null, null, '2321321323', 'large-chart.png', '0', '2014-12-26 15:01:09', '0', 'open', '2014-12-26 15:01:09', '3');
