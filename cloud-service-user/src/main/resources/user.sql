/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.3.42
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 192.168.3.42:3306
 Source Schema         : db

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 15/06/2024 15:55:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
  `img_url` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '头像url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
