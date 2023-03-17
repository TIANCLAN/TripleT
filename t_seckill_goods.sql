/*
Navicat MySQL Data Transfer

Source Server         : qqq
Source Server Version : 50736
Source Host           : localhost:3306
Source Database       : easydb

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2022-07-31 23:50:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_seckill_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_seckill_goods`;
CREATE TABLE `t_seckill_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀商品ID',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `seckill_price` decimal(10,2) NOT NULL COMMENT '秒杀家',
  `stock_count` int(10) NOT NULL COMMENT '库存数量',
  `start_date` datetime NOT NULL COMMENT '秒杀开始时间',
  `end_date` datetime NOT NULL COMMENT '秒杀结束时间',
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='秒杀商品表';

-- ----------------------------
-- Records of t_seckill_goods
-- ----------------------------
INSERT INTO `t_seckill_goods` VALUES ('23', '1', '20.00', '10', '2022-07-09 19:30:07', '2022-07-09 19:30:07', '10');
INSERT INTO `t_seckill_goods` VALUES ('27', '21', '20.00', '20', '2022-07-09 19:38:03', '2022-07-09 19:38:03', '20');
INSERT INTO `t_seckill_goods` VALUES ('28', '21', '200.00', '10', '2022-07-09 20:32:03', '2022-07-09 20:32:03', '10');
INSERT INTO `t_seckill_goods` VALUES ('33', '21', '45.00', '50', '2022-07-12 16:23:03', '2022-07-12 16:23:03', '50');
INSERT INTO `t_seckill_goods` VALUES ('34', '35', '50.00', '100', '2022-07-16 13:32:59', '2022-07-16 13:32:59', '100');
INSERT INTO `t_seckill_goods` VALUES ('78', '128', '66.00', '1', '2022-07-17 14:11:55', '2022-07-17 14:11:55', '1');
INSERT INTO `t_seckill_goods` VALUES ('90', '65', '10.00', '100', '2022-07-17 16:38:03', '2022-07-17 16:38:03', '100');
INSERT INTO `t_seckill_goods` VALUES ('120', '35', '1.00', '200', '2022-07-30 01:03:03', '2022-07-30 01:03:03', '0');
