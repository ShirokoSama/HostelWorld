/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : hostel_world

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-22 17:17:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accomodation
-- ----------------------------
DROP TABLE IF EXISTS `accomodation`;
CREATE TABLE `accomodation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mid` int(10) unsigned DEFAULT NULL,
  `hid` int(10) unsigned NOT NULL,
  `rid` int(10) unsigned NOT NULL,
  `arrivedate` datetime NOT NULL,
  `leavedate` datetime DEFAULT NULL,
  `cost` int(11) NOT NULL,
  `roomtype` varchar(255) NOT NULL,
  `membername` varchar(255) NOT NULL,
  `paytype` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mid_accomodation` (`mid`),
  KEY `hid_accomodation` (`hid`),
  KEY `rid_accomodation` (`rid`),
  CONSTRAINT `hid_accomodation` FOREIGN KEY (`hid`) REFERENCES `hotel` (`id`),
  CONSTRAINT `mid_accomodation` FOREIGN KEY (`mid`) REFERENCES `member` (`id`),
  CONSTRAINT `rid_accomodation` FOREIGN KEY (`rid`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accomodation
-- ----------------------------
INSERT INTO `accomodation` VALUES ('1', '2', '3', '5', '2017-03-15 00:00:00', '2017-03-15 00:00:00', '400', 'single', 'srf', 'plan');
INSERT INTO `accomodation` VALUES ('2', null, '3', '5', '2017-03-18 00:00:00', '2017-03-18 00:00:00', '200', 'single', '路人甲', 'cash');
INSERT INTO `accomodation` VALUES ('3', '5', '3', '5', '2017-03-20 00:00:00', '2017-03-20 00:00:00', '400', 'single', 'Shiroko', 'plan');
INSERT INTO `accomodation` VALUES ('4', '5', '7', '155', '2017-03-20 00:00:00', '2017-03-20 00:00:00', '200', 'single', 'Shiroko', 'plan');
INSERT INTO `accomodation` VALUES ('5', null, '7', '155', '2017-03-20 00:00:00', '2017-03-20 00:00:00', '200', 'single', 'Shiroko', 'cash');

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cost` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `mid` int(10) unsigned DEFAULT NULL,
  `hid` int(10) unsigned DEFAULT NULL,
  `membername` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hid_account` (`hid`),
  KEY `mid_account` (`mid`),
  CONSTRAINT `hid_account` FOREIGN KEY (`hid`) REFERENCES `hotel` (`id`),
  CONSTRAINT `mid_account` FOREIGN KEY (`mid`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '400', 'approve', 'settle', '2', '3', null, '2017-03-15 00:00:00');
INSERT INTO `account` VALUES ('2', '400', null, 'appointment', '2', '3', null, '2017-03-17 13:48:08');
INSERT INTO `account` VALUES ('3', '2000', null, 'recharge', '5', null, null, '2017-03-17 13:48:12');
INSERT INTO `account` VALUES ('4', '2000', null, 'recharge', '5', null, null, '2017-03-17 13:48:14');
INSERT INTO `account` VALUES ('5', '2000', null, 'recharge', '6', null, null, '2017-03-17 13:48:16');
INSERT INTO `account` VALUES ('6', '3000', null, 'recharge', '7', null, null, '2017-03-17 13:48:17');
INSERT INTO `account` VALUES ('7', '1000', null, 'recharge', '5', null, null, '2017-03-17 13:48:18');
INSERT INTO `account` VALUES ('8', '1000', null, 'recharge', '5', null, null, '2017-03-17 13:48:19');
INSERT INTO `account` VALUES ('9', '200', null, 'appointment', '5', '3', null, '2017-03-17 13:48:21');
INSERT INTO `account` VALUES ('10', '200', null, 'cancel', '5', '3', null, '2017-03-17 13:48:22');
INSERT INTO `account` VALUES ('12', '200', null, 'appointment', '5', '3', null, '2017-03-17 00:00:00');
INSERT INTO `account` VALUES ('13', '400', 'approve', 'settle', '2', '3', null, '2017-03-18 00:00:00');
INSERT INTO `account` VALUES ('14', '200', null, 'cash', null, '3', '路人甲', '2017-03-18 00:00:00');
INSERT INTO `account` VALUES ('15', '200', 'approve', 'settle', '5', '3', null, '2017-03-20 00:00:00');
INSERT INTO `account` VALUES ('16', '0', null, 'score', '5', null, null, '2017-03-20 00:00:00');
INSERT INTO `account` VALUES ('17', '1000', null, 'recharge', '9', null, null, '2017-03-20 00:00:00');
INSERT INTO `account` VALUES ('18', '1000', null, 'recharge', '5', null, null, '2017-03-20 00:00:00');
INSERT INTO `account` VALUES ('19', '400', null, 'appointment', '5', '3', null, '2017-03-20 00:00:00');
INSERT INTO `account` VALUES ('20', '400', null, 'cancel', '5', '3', null, '2017-03-20 00:00:00');
INSERT INTO `account` VALUES ('21', '200', null, 'appointment', '5', '3', null, '2017-03-20 00:00:00');
INSERT INTO `account` VALUES ('22', '200', null, 'appointment', '5', '7', null, '2017-03-20 00:00:00');
INSERT INTO `account` VALUES ('23', '200', 'apply', 'settle', '5', '7', null, '2017-03-20 00:00:00');
INSERT INTO `account` VALUES ('24', '200', null, 'cash', null, '7', 'Shiroko', '2017-03-20 00:00:00');

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `identitynum` varchar(255) DEFAULT NULL,
  `mid` int(10) unsigned NOT NULL,
  `hid` int(10) unsigned NOT NULL,
  `type` varchar(255) NOT NULL,
  `num` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `days` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mid_apponitment` (`mid`),
  KEY `hid_appointment` (`hid`),
  CONSTRAINT `hid_appointment` FOREIGN KEY (`hid`) REFERENCES `hotel` (`id`),
  CONSTRAINT `mid_apponitment` FOREIGN KEY (`mid`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('1', '1000001', '2', '3', 'single', '1', '400', 'settled', '2017-03-14 00:00:00', '2');
INSERT INTO `appointment` VALUES ('2', '1000002', '2', '3', 'single', '1', '400', 'settled', '2017-03-15 00:00:00', '2');
INSERT INTO `appointment` VALUES ('3', '1000003', '5', '3', 'single', '1', '200', 'canceled', '2017-03-17 00:00:00', '1');
INSERT INTO `appointment` VALUES ('4', '1000004', '5', '3', 'single', '1', '200', 'settled', '2017-03-17 00:00:00', '1');
INSERT INTO `appointment` VALUES ('5', '1000005', '5', '3', 'single', '1', '400', 'canceled', '2017-03-20 00:00:00', '2');
INSERT INTO `appointment` VALUES ('6', '1000006', '5', '3', 'single', '1', '200', 'ordering', '2017-03-20 00:00:00', '1');
INSERT INTO `appointment` VALUES ('7', '1000007', '5', '7', 'single', '1', '200', 'settled', '2017-03-20 00:00:00', '2');

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `identitynum` varchar(255) NOT NULL,
  `bankcnum` varchar(255) NOT NULL,
  `money` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `costcount` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `createdate` datetime NOT NULL,
  `suspenddate` datetime DEFAULT NULL,
  `terminatedate` datetime DEFAULT NULL,
  `mid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mid_card` (`mid`),
  CONSTRAINT `mid_card` FOREIGN KEY (`mid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('13', '1000000', '111', '600', '1', '800', '80', '2017-03-13 00:00:00', '2018-03-13 00:00:00', null, '2');
INSERT INTO `card` VALUES ('14', '1000001', '23333333', '4600', '1', '400', '20', '2017-03-16 00:00:00', '2018-03-16 00:00:00', null, '5');
INSERT INTO `card` VALUES ('16', '1000002', '123456', '2000', '1', '0', '0', '2017-03-16 00:00:00', '2018-03-16 00:00:00', null, '6');
INSERT INTO `card` VALUES ('17', '1000003', '141250107', '3000', '1', '0', '0', '2017-03-16 00:00:00', '2018-03-16 00:00:00', null, '7');
INSERT INTO `card` VALUES ('18', '1000004', '111', '1000', '1', '0', '0', '2017-03-20 00:00:00', '2018-03-20 00:00:00', null, '9');

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `identitynum` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `plandate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES ('3', '如家', '123456', '1000001', '南京', '无', 'working', '2017-03-31 00:00:00');
INSERT INTO `hotel` VALUES ('4', '万达', '111111', '1000002', '南京', '无', 'working', '2017-03-24 19:41:11');
INSERT INTO `hotel` VALUES ('5', '如家', '111', '1000001', '南京', '测试修改', 'edit', null);
INSERT INTO `hotel` VALUES ('6', '7天', '123456', '1000003', '南京', '没有', 'working', null);
INSERT INTO `hotel` VALUES ('7', '233', '123456', '1000004', '南京', '没有', 'working', '2017-03-25 00:00:00');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'manager', '123456');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `membername` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('2', 'srf', '111111', 'active');
INSERT INTO `member` VALUES ('5', 'Shiroko', '123456', 'active');
INSERT INTO `member` VALUES ('6', 'sbin', '123456', 'active');
INSERT INTO `member` VALUES ('7', 'sccc', '123456', 'active');
INSERT INTO `member` VALUES ('8', 'ayano', '123456', 'not active');
INSERT INTO `member` VALUES ('9', 'sss', '123456', 'active');

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hid` int(10) unsigned NOT NULL,
  `type` varchar(255) NOT NULL,
  `totalnum` int(11) NOT NULL,
  `staynum` int(11) NOT NULL,
  `ordernum` int(11) NOT NULL,
  `plannum` int(11) NOT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hid_plan` (`hid`),
  CONSTRAINT `hid_plan` FOREIGN KEY (`hid`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `plan` VALUES ('1', '3', 'single', '10', '0', '0', '10', '200');
INSERT INTO `plan` VALUES ('2', '3', 'double', '10', '0', '0', '10', '200');
INSERT INTO `plan` VALUES ('3', '3', 'flat', '5', '0', '0', '5', '300');
INSERT INTO `plan` VALUES ('4', '3', 'deluxe', '5', '0', '0', '5', '500');
INSERT INTO `plan` VALUES ('5', '4', 'single', '20', '0', '0', '0', '0');
INSERT INTO `plan` VALUES ('6', '4', 'double', '20', '0', '0', '0', '0');
INSERT INTO `plan` VALUES ('7', '4', 'flat', '10', '0', '0', '0', '0');
INSERT INTO `plan` VALUES ('8', '4', 'deluxe', '10', '0', '0', '0', '0');
INSERT INTO `plan` VALUES ('9', '6', 'single', '20', '0', '0', '0', '0');
INSERT INTO `plan` VALUES ('10', '6', 'double', '20', '0', '0', '0', '0');
INSERT INTO `plan` VALUES ('11', '6', 'flat', '10', '0', '0', '0', '0');
INSERT INTO `plan` VALUES ('12', '6', 'deluxe', '10', '0', '0', '0', '0');
INSERT INTO `plan` VALUES ('13', '7', 'single', '20', '0', '0', '20', '100');
INSERT INTO `plan` VALUES ('14', '7', 'double', '10', '0', '0', '10', '200');
INSERT INTO `plan` VALUES ('15', '7', 'flat', '10', '0', '0', '10', '300');
INSERT INTO `plan` VALUES ('16', '7', 'deluxe', '10', '0', '0', '10', '400');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hid` int(10) unsigned NOT NULL,
  `type` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `roomnum` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hid_room` (`hid`),
  CONSTRAINT `hid_room` FOREIGN KEY (`hid`) REFERENCES `hotel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('5', '3', 'single', 'empty', '101');
INSERT INTO `room` VALUES ('6', '3', 'single', 'empty', '102');
INSERT INTO `room` VALUES ('7', '3', 'single', 'empty', '103');
INSERT INTO `room` VALUES ('8', '3', 'single', 'empty', '104');
INSERT INTO `room` VALUES ('9', '3', 'single', 'empty', '105');
INSERT INTO `room` VALUES ('10', '3', 'single', 'empty', '106');
INSERT INTO `room` VALUES ('11', '3', 'single', 'empty', '107');
INSERT INTO `room` VALUES ('12', '3', 'single', 'empty', '108');
INSERT INTO `room` VALUES ('13', '3', 'single', 'empty', '109');
INSERT INTO `room` VALUES ('14', '3', 'single', 'empty', '110');
INSERT INTO `room` VALUES ('15', '3', 'double', 'empty', '111');
INSERT INTO `room` VALUES ('16', '3', 'double', 'empty', '112');
INSERT INTO `room` VALUES ('17', '3', 'double', 'empty', '113');
INSERT INTO `room` VALUES ('18', '3', 'double', 'empty', '114');
INSERT INTO `room` VALUES ('19', '3', 'double', 'empty', '115');
INSERT INTO `room` VALUES ('20', '3', 'double', 'empty', '116');
INSERT INTO `room` VALUES ('21', '3', 'double', 'empty', '117');
INSERT INTO `room` VALUES ('22', '3', 'double', 'empty', '118');
INSERT INTO `room` VALUES ('23', '3', 'double', 'empty', '119');
INSERT INTO `room` VALUES ('24', '3', 'double', 'empty', '120');
INSERT INTO `room` VALUES ('25', '3', 'flat', 'empty', '201');
INSERT INTO `room` VALUES ('26', '3', 'flat', 'empty', '202');
INSERT INTO `room` VALUES ('27', '3', 'flat', 'empty', '203');
INSERT INTO `room` VALUES ('28', '3', 'flat', 'empty', '204');
INSERT INTO `room` VALUES ('29', '3', 'flat', 'empty', '205');
INSERT INTO `room` VALUES ('30', '3', 'deluxe', 'empty', '206');
INSERT INTO `room` VALUES ('31', '3', 'deluxe', 'empty', '207');
INSERT INTO `room` VALUES ('32', '3', 'deluxe', 'empty', '208');
INSERT INTO `room` VALUES ('33', '3', 'deluxe', 'empty', '209');
INSERT INTO `room` VALUES ('34', '3', 'deluxe', 'empty', '210');
INSERT INTO `room` VALUES ('35', '4', 'single', 'empty', '101');
INSERT INTO `room` VALUES ('36', '4', 'single', 'empty', '102');
INSERT INTO `room` VALUES ('37', '4', 'single', 'empty', '103');
INSERT INTO `room` VALUES ('38', '4', 'single', 'empty', '104');
INSERT INTO `room` VALUES ('39', '4', 'single', 'empty', '105');
INSERT INTO `room` VALUES ('40', '4', 'single', 'empty', '106');
INSERT INTO `room` VALUES ('41', '4', 'single', 'empty', '107');
INSERT INTO `room` VALUES ('42', '4', 'single', 'empty', '108');
INSERT INTO `room` VALUES ('43', '4', 'single', 'empty', '109');
INSERT INTO `room` VALUES ('44', '4', 'single', 'empty', '110');
INSERT INTO `room` VALUES ('45', '4', 'single', 'empty', '111');
INSERT INTO `room` VALUES ('46', '4', 'single', 'empty', '112');
INSERT INTO `room` VALUES ('47', '4', 'single', 'empty', '113');
INSERT INTO `room` VALUES ('48', '4', 'single', 'empty', '114');
INSERT INTO `room` VALUES ('49', '4', 'single', 'empty', '115');
INSERT INTO `room` VALUES ('50', '4', 'single', 'empty', '116');
INSERT INTO `room` VALUES ('51', '4', 'single', 'empty', '117');
INSERT INTO `room` VALUES ('52', '4', 'single', 'empty', '118');
INSERT INTO `room` VALUES ('53', '4', 'single', 'empty', '119');
INSERT INTO `room` VALUES ('54', '4', 'single', 'empty', '120');
INSERT INTO `room` VALUES ('55', '4', 'double', 'empty', '201');
INSERT INTO `room` VALUES ('56', '4', 'double', 'empty', '202');
INSERT INTO `room` VALUES ('57', '4', 'double', 'empty', '203');
INSERT INTO `room` VALUES ('58', '4', 'double', 'empty', '204');
INSERT INTO `room` VALUES ('59', '4', 'double', 'empty', '205');
INSERT INTO `room` VALUES ('60', '4', 'double', 'empty', '206');
INSERT INTO `room` VALUES ('61', '4', 'double', 'empty', '207');
INSERT INTO `room` VALUES ('62', '4', 'double', 'empty', '208');
INSERT INTO `room` VALUES ('63', '4', 'double', 'empty', '209');
INSERT INTO `room` VALUES ('64', '4', 'double', 'empty', '210');
INSERT INTO `room` VALUES ('65', '4', 'double', 'empty', '211');
INSERT INTO `room` VALUES ('66', '4', 'double', 'empty', '212');
INSERT INTO `room` VALUES ('67', '4', 'double', 'empty', '213');
INSERT INTO `room` VALUES ('68', '4', 'double', 'empty', '214');
INSERT INTO `room` VALUES ('69', '4', 'double', 'empty', '215');
INSERT INTO `room` VALUES ('70', '4', 'double', 'empty', '216');
INSERT INTO `room` VALUES ('71', '4', 'double', 'empty', '217');
INSERT INTO `room` VALUES ('72', '4', 'double', 'empty', '218');
INSERT INTO `room` VALUES ('73', '4', 'double', 'empty', '219');
INSERT INTO `room` VALUES ('74', '4', 'double', 'empty', '220');
INSERT INTO `room` VALUES ('75', '4', 'flat', 'empty', '301');
INSERT INTO `room` VALUES ('76', '4', 'flat', 'empty', '302');
INSERT INTO `room` VALUES ('77', '4', 'flat', 'empty', '303');
INSERT INTO `room` VALUES ('78', '4', 'flat', 'empty', '304');
INSERT INTO `room` VALUES ('79', '4', 'flat', 'empty', '305');
INSERT INTO `room` VALUES ('80', '4', 'flat', 'empty', '306');
INSERT INTO `room` VALUES ('81', '4', 'flat', 'empty', '307');
INSERT INTO `room` VALUES ('82', '4', 'flat', 'empty', '308');
INSERT INTO `room` VALUES ('83', '4', 'flat', 'empty', '309');
INSERT INTO `room` VALUES ('84', '4', 'flat', 'empty', '310');
INSERT INTO `room` VALUES ('85', '4', 'deluxe', 'empty', '311');
INSERT INTO `room` VALUES ('86', '4', 'deluxe', 'empty', '312');
INSERT INTO `room` VALUES ('87', '4', 'deluxe', 'empty', '313');
INSERT INTO `room` VALUES ('88', '4', 'deluxe', 'empty', '314');
INSERT INTO `room` VALUES ('89', '4', 'deluxe', 'empty', '315');
INSERT INTO `room` VALUES ('90', '4', 'deluxe', 'empty', '316');
INSERT INTO `room` VALUES ('91', '4', 'deluxe', 'empty', '317');
INSERT INTO `room` VALUES ('92', '4', 'deluxe', 'empty', '318');
INSERT INTO `room` VALUES ('93', '4', 'deluxe', 'empty', '319');
INSERT INTO `room` VALUES ('94', '4', 'deluxe', 'empty', '320');
INSERT INTO `room` VALUES ('95', '6', 'single', 'empty', '101');
INSERT INTO `room` VALUES ('96', '6', 'single', 'empty', '102');
INSERT INTO `room` VALUES ('97', '6', 'single', 'empty', '103');
INSERT INTO `room` VALUES ('98', '6', 'single', 'empty', '104');
INSERT INTO `room` VALUES ('99', '6', 'single', 'empty', '105');
INSERT INTO `room` VALUES ('100', '6', 'single', 'empty', '106');
INSERT INTO `room` VALUES ('101', '6', 'single', 'empty', '107');
INSERT INTO `room` VALUES ('102', '6', 'single', 'empty', '108');
INSERT INTO `room` VALUES ('103', '6', 'single', 'empty', '109');
INSERT INTO `room` VALUES ('104', '6', 'single', 'empty', '110');
INSERT INTO `room` VALUES ('105', '6', 'single', 'empty', '111');
INSERT INTO `room` VALUES ('106', '6', 'single', 'empty', '112');
INSERT INTO `room` VALUES ('107', '6', 'single', 'empty', '113');
INSERT INTO `room` VALUES ('108', '6', 'single', 'empty', '114');
INSERT INTO `room` VALUES ('109', '6', 'single', 'empty', '115');
INSERT INTO `room` VALUES ('110', '6', 'single', 'empty', '116');
INSERT INTO `room` VALUES ('111', '6', 'single', 'empty', '117');
INSERT INTO `room` VALUES ('112', '6', 'single', 'empty', '118');
INSERT INTO `room` VALUES ('113', '6', 'single', 'empty', '119');
INSERT INTO `room` VALUES ('114', '6', 'single', 'empty', '120');
INSERT INTO `room` VALUES ('115', '6', 'double', 'empty', '201');
INSERT INTO `room` VALUES ('116', '6', 'double', 'empty', '202');
INSERT INTO `room` VALUES ('117', '6', 'double', 'empty', '203');
INSERT INTO `room` VALUES ('118', '6', 'double', 'empty', '204');
INSERT INTO `room` VALUES ('119', '6', 'double', 'empty', '205');
INSERT INTO `room` VALUES ('120', '6', 'double', 'empty', '206');
INSERT INTO `room` VALUES ('121', '6', 'double', 'empty', '207');
INSERT INTO `room` VALUES ('122', '6', 'double', 'empty', '208');
INSERT INTO `room` VALUES ('123', '6', 'double', 'empty', '209');
INSERT INTO `room` VALUES ('124', '6', 'double', 'empty', '210');
INSERT INTO `room` VALUES ('125', '6', 'double', 'empty', '211');
INSERT INTO `room` VALUES ('126', '6', 'double', 'empty', '212');
INSERT INTO `room` VALUES ('127', '6', 'double', 'empty', '213');
INSERT INTO `room` VALUES ('128', '6', 'double', 'empty', '214');
INSERT INTO `room` VALUES ('129', '6', 'double', 'empty', '215');
INSERT INTO `room` VALUES ('130', '6', 'double', 'empty', '216');
INSERT INTO `room` VALUES ('131', '6', 'double', 'empty', '217');
INSERT INTO `room` VALUES ('132', '6', 'double', 'empty', '218');
INSERT INTO `room` VALUES ('133', '6', 'double', 'empty', '219');
INSERT INTO `room` VALUES ('134', '6', 'double', 'empty', '220');
INSERT INTO `room` VALUES ('135', '6', 'flat', 'empty', '301');
INSERT INTO `room` VALUES ('136', '6', 'flat', 'empty', '302');
INSERT INTO `room` VALUES ('137', '6', 'flat', 'empty', '303');
INSERT INTO `room` VALUES ('138', '6', 'flat', 'empty', '304');
INSERT INTO `room` VALUES ('139', '6', 'flat', 'empty', '305');
INSERT INTO `room` VALUES ('140', '6', 'flat', 'empty', '306');
INSERT INTO `room` VALUES ('141', '6', 'flat', 'empty', '307');
INSERT INTO `room` VALUES ('142', '6', 'flat', 'empty', '308');
INSERT INTO `room` VALUES ('143', '6', 'flat', 'empty', '309');
INSERT INTO `room` VALUES ('144', '6', 'flat', 'empty', '310');
INSERT INTO `room` VALUES ('145', '6', 'deluxe', 'empty', '311');
INSERT INTO `room` VALUES ('146', '6', 'deluxe', 'empty', '312');
INSERT INTO `room` VALUES ('147', '6', 'deluxe', 'empty', '313');
INSERT INTO `room` VALUES ('148', '6', 'deluxe', 'empty', '314');
INSERT INTO `room` VALUES ('149', '6', 'deluxe', 'empty', '315');
INSERT INTO `room` VALUES ('150', '6', 'deluxe', 'empty', '316');
INSERT INTO `room` VALUES ('151', '6', 'deluxe', 'empty', '317');
INSERT INTO `room` VALUES ('152', '6', 'deluxe', 'empty', '318');
INSERT INTO `room` VALUES ('153', '6', 'deluxe', 'empty', '319');
INSERT INTO `room` VALUES ('154', '6', 'deluxe', 'empty', '320');
INSERT INTO `room` VALUES ('155', '7', 'single', 'empty', '101');
INSERT INTO `room` VALUES ('156', '7', 'single', 'empty', '102');
INSERT INTO `room` VALUES ('157', '7', 'single', 'empty', '103');
INSERT INTO `room` VALUES ('158', '7', 'single', 'empty', '104');
INSERT INTO `room` VALUES ('159', '7', 'single', 'empty', '105');
INSERT INTO `room` VALUES ('160', '7', 'single', 'empty', '106');
INSERT INTO `room` VALUES ('161', '7', 'single', 'empty', '107');
INSERT INTO `room` VALUES ('162', '7', 'single', 'empty', '108');
INSERT INTO `room` VALUES ('163', '7', 'single', 'empty', '109');
INSERT INTO `room` VALUES ('164', '7', 'single', 'empty', '110');
INSERT INTO `room` VALUES ('165', '7', 'single', 'empty', '111');
INSERT INTO `room` VALUES ('166', '7', 'single', 'empty', '112');
INSERT INTO `room` VALUES ('167', '7', 'single', 'empty', '113');
INSERT INTO `room` VALUES ('168', '7', 'single', 'empty', '114');
INSERT INTO `room` VALUES ('169', '7', 'single', 'empty', '115');
INSERT INTO `room` VALUES ('170', '7', 'single', 'empty', '116');
INSERT INTO `room` VALUES ('171', '7', 'single', 'empty', '117');
INSERT INTO `room` VALUES ('172', '7', 'single', 'empty', '118');
INSERT INTO `room` VALUES ('173', '7', 'single', 'empty', '119');
INSERT INTO `room` VALUES ('174', '7', 'single', 'empty', '120');
INSERT INTO `room` VALUES ('175', '7', 'double', 'empty', '201');
INSERT INTO `room` VALUES ('176', '7', 'double', 'empty', '202');
INSERT INTO `room` VALUES ('177', '7', 'double', 'empty', '203');
INSERT INTO `room` VALUES ('178', '7', 'double', 'empty', '204');
INSERT INTO `room` VALUES ('179', '7', 'double', 'empty', '205');
INSERT INTO `room` VALUES ('180', '7', 'double', 'empty', '206');
INSERT INTO `room` VALUES ('181', '7', 'double', 'empty', '207');
INSERT INTO `room` VALUES ('182', '7', 'double', 'empty', '208');
INSERT INTO `room` VALUES ('183', '7', 'double', 'empty', '209');
INSERT INTO `room` VALUES ('184', '7', 'double', 'empty', '210');
INSERT INTO `room` VALUES ('185', '7', 'flat', 'empty', '211');
INSERT INTO `room` VALUES ('186', '7', 'flat', 'empty', '212');
INSERT INTO `room` VALUES ('187', '7', 'flat', 'empty', '213');
INSERT INTO `room` VALUES ('188', '7', 'flat', 'empty', '214');
INSERT INTO `room` VALUES ('189', '7', 'flat', 'empty', '215');
INSERT INTO `room` VALUES ('190', '7', 'flat', 'empty', '216');
INSERT INTO `room` VALUES ('191', '7', 'flat', 'empty', '217');
INSERT INTO `room` VALUES ('192', '7', 'flat', 'empty', '218');
INSERT INTO `room` VALUES ('193', '7', 'flat', 'empty', '219');
INSERT INTO `room` VALUES ('194', '7', 'flat', 'empty', '220');
INSERT INTO `room` VALUES ('195', '7', 'deluxe', 'empty', '301');
INSERT INTO `room` VALUES ('196', '7', 'deluxe', 'empty', '302');
INSERT INTO `room` VALUES ('197', '7', 'deluxe', 'empty', '303');
INSERT INTO `room` VALUES ('198', '7', 'deluxe', 'empty', '304');
INSERT INTO `room` VALUES ('199', '7', 'deluxe', 'empty', '305');
INSERT INTO `room` VALUES ('200', '7', 'deluxe', 'empty', '306');
INSERT INTO `room` VALUES ('201', '7', 'deluxe', 'empty', '307');
INSERT INTO `room` VALUES ('202', '7', 'deluxe', 'empty', '308');
INSERT INTO `room` VALUES ('203', '7', 'deluxe', 'empty', '309');
INSERT INTO `room` VALUES ('204', '7', 'deluxe', 'empty', '310');
