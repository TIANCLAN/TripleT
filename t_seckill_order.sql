/*
Navicat MySQL Data Transfer

Source Server         : qqq
Source Server Version : 50736
Source Host           : localhost:3306
Source Database       : easydb

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2022-07-31 23:50:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_seckill_order
-- ----------------------------
DROP TABLE IF EXISTS `t_seckill_order`;
CREATE TABLE `t_seckill_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀订单ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀订单表';

-- ----------------------------
-- Records of t_seckill_order
-- ----------------------------
