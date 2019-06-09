/*
Navicat MySQL Data Transfer

Source Server         : cloud
Source Server Version : 50716
Source Host           : 39.105.76.3:3306
Source Database       : cams

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-06-09 10:42:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `aaa`
-- ----------------------------
DROP TABLE IF EXISTS `aaa`;
CREATE TABLE `aaa` (
  `shijian` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of aaa
-- ----------------------------
INSERT INTO `aaa` VALUES ('2019-01-15 13:20:40');

-- ----------------------------
-- Table structure for `activity`
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `a_no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) COLLATE utf8_bin NOT NULL,
  `content` varchar(200) COLLATE utf8_bin NOT NULL,
  `publish_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `participator_path` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `c_no` char(11) COLLATE utf8_bin DEFAULT NULL,
  `s_id` char(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`a_no`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('4', '一起学习呀', '学习安卓内容', '2019-01-06 00:14:20', '2019-01-06 00:14:20', '2019-01-07 00:00:00', '2019-01-16 00:00:00', '4.activity', '201524', '20152470426');
INSERT INTO `activity` VALUES ('5', '写代码', '熬夜写安卓', '2019-01-06 00:17:36', '2019-01-06 00:17:36', '2019-01-06 00:00:00', '2019-01-18 00:00:00', '5.activity', '201524', '20152470426');
INSERT INTO `activity` VALUES ('8', '跑步', '晚上去跑步', '2019-01-07 23:05:34', '2019-01-07 23:05:34', '2019-01-07 00:00:00', '2019-01-07 00:00:00', '8.activity', '201524', '20152470422');
INSERT INTO `activity` VALUES ('9', '云南旅游', '限定四人一组', '2019-01-07 23:05:36', '2019-01-07 23:05:36', '2019-03-08 00:00:00', '2019-03-15 00:00:00', '9.activity', '201524', '20152470431');
INSERT INTO `activity` VALUES ('11', '演示', '尝试', '2019-01-08 09:07:09', '2019-01-08 09:07:09', '2019-01-08 00:00:00', '2019-01-08 00:00:00', '11.activity', '201524', null);

-- ----------------------------
-- Table structure for `class`
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `c_no` char(11) COLLATE utf8_bin NOT NULL,
  `password` char(20) COLLATE utf8_bin NOT NULL,
  `name` varchar(30) COLLATE utf8_bin NOT NULL,
  `school` varchar(30) COLLATE utf8_bin NOT NULL,
  `image_path` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `profession` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `enrol_date` date DEFAULT NULL,
  `tell` char(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`c_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('201524', '1234567', '中澳计科4班', '郑州大学', '201524.jpg', '计算机科学与技术', '2015-09-01', null);
INSERT INTO `class` VALUES ('20152470', '123456', '计科四班', '郑州大学', '20152470.jpg', '计算机', '2019-01-02', null);

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `g_no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `participator_path` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `p_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`g_no`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '实时处理', '7', '1.goods', '1');
INSERT INTO `goods` VALUES ('2', '多媒体', '9', '2.goods', '1');
INSERT INTO `goods` VALUES ('3', '安卓', '11', '3.goods', '1');
INSERT INTO `goods` VALUES ('4', '黄焖茄子', '12', '4.goods', '2');
INSERT INTO `goods` VALUES ('5', '黄焖土豆', '10', '5.goods', '2');
INSERT INTO `goods` VALUES ('6', '黄焖红烧肉', '20', '6.goods', '2');
INSERT INTO `goods` VALUES ('9', '足球鞋', '200', '9.goods', '4');
INSERT INTO `goods` VALUES ('10', '球衣', '69', '10.goods', '4');
INSERT INTO `goods` VALUES ('11', '咖啡', '2', '11.goods', '5');
INSERT INTO `goods` VALUES ('12', 'java', '66', '12.goods', '6');
INSERT INTO `goods` VALUES ('13', 'ppt', '1', '13.goods', '7');

-- ----------------------------
-- Table structure for `purchase`
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `p_no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `type` char(20) COLLATE utf8_bin DEFAULT NULL,
  `publish_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `c_no` char(11) COLLATE utf8_bin DEFAULT NULL,
  `s_id` char(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`p_no`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES ('1', '复习资料订购', '购书，请积极参与', '复习资料', '2019-01-05 23:31:05', '2019-01-10 00:00:00', '201524', '20152470431');
INSERT INTO `purchase` VALUES ('2', '团购小小黄焖鸡', '一起吃饭呀，好吃的黄焖鸡', '食品', '2019-01-05 23:38:03', '2019-01-16 00:00:00', '201524', '20152470426');
INSERT INTO `purchase` VALUES ('4', '踢足球', '一起成为梅西呀', '运动', '2019-01-06 00:20:06', '2019-01-16 00:00:00', '201524', '20152470426');
INSERT INTO `purchase` VALUES ('5', '咖啡', '当熬夜之后最需要的东西', '饮料', '2019-01-07 23:07:45', '2019-01-10 00:00:00', '201524', '20152470415');
INSERT INTO `purchase` VALUES ('6', '团购java课本', '团购一起领', '学习', '2019-01-07 23:10:55', '2018-12-09 00:00:00', '201524', '20152470431');
INSERT INTO `purchase` VALUES ('7', '演示', '作品展示', '展示', '2019-01-08 09:15:03', '2019-01-09 00:00:00', '201524', '20152470415');

-- ----------------------------
-- Table structure for `r_c_s`
-- ----------------------------
DROP TABLE IF EXISTS `r_c_s`;
CREATE TABLE `r_c_s` (
  `r_no` int(11) NOT NULL,
  `c_no` char(11) COLLATE utf8_bin NOT NULL,
  `r_name` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `upload_date` datetime DEFAULT NULL,
  `s_id` char(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`r_no`,`c_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of r_c_s
-- ----------------------------

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `r_no` int(11) NOT NULL AUTO_INCREMENT,
  `type` char(20) COLLATE utf8_bin DEFAULT NULL,
  `subject` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `path` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `md5` char(200) COLLATE utf8_bin DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`r_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resource
-- ----------------------------

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `s_id` char(11) COLLATE utf8_bin NOT NULL,
  `password` char(20) COLLATE utf8_bin NOT NULL,
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `post` char(10) COLLATE utf8_bin DEFAULT NULL,
  `image_path` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `c_no` char(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('20152470401', '123456', '白鹤', '学生委员', null, '201524');
INSERT INTO `student` VALUES ('20152470415', '123456', '牛明远', '无', '20152470415.jpg', '201524');
INSERT INTO `student` VALUES ('20152470422', '123456', '王绍南', '无', '20152470422.jpg', '201524');
INSERT INTO `student` VALUES ('20152470426', '123456', '邬宏伟', '组织委员', '20152470426.jpg', '201524');
INSERT INTO `student` VALUES ('20152470431', '123456', '袁奎', '无', '20152470431.jpg', '201524');
INSERT INTO `student` VALUES ('20152470437', '123456', '帅牛牛', '网络委员', null, '201524');

-- ----------------------------
-- Table structure for `syllabus`
-- ----------------------------
DROP TABLE IF EXISTS `syllabus`;
CREATE TABLE `syllabus` (
  `s_no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `term` int(11) DEFAULT NULL,
  `all_subject` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `image_path` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `c_no` char(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`s_no`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of syllabus
-- ----------------------------
INSERT INTO `syllabus` VALUES ('4', '第一学期', '1', '微积分#', '201524_1.png', '201524');
INSERT INTO `syllabus` VALUES ('5', '第二学期', '2', '大学物理#', '201524_2.png', '201524');
INSERT INTO `syllabus` VALUES ('6', '春游', '1', '春游#', '201524_1.jpg', '201524');
INSERT INTO `syllabus` VALUES ('7', '春游', '4', '春游#', '201524_4.jpg', '201524');

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `t_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `publish_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `c_no` char(11) COLLATE utf8_bin DEFAULT NULL,
  `s_id` char(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`t_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of task
-- ----------------------------
