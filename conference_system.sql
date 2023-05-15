/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : conference_system

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 15/05/2023 04:17:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for x_blind_review
-- ----------------------------
DROP TABLE IF EXISTS `x_blind_review`;
CREATE TABLE `x_blind_review`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `paper_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `innovative` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `logical_structure` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `readability` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `comprehensive_review` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `review` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `blind_user_id`(`user_id`) USING BTREE,
  INDEX `blind_paper_id`(`paper_id`) USING BTREE,
  CONSTRAINT `blind_paper_id` FOREIGN KEY (`paper_id`) REFERENCES `x_paper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `blind_user_id` FOREIGN KEY (`user_id`) REFERENCES `x_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_blind_review
-- ----------------------------

-- ----------------------------
-- Table structure for x_paper
-- ----------------------------
DROP TABLE IF EXISTS `x_paper`;
CREATE TABLE `x_paper`  (
  `id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `abstract_cn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `research_dir` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commitTime` date NULL DEFAULT NULL,
  `submit` int(255) NULL DEFAULT NULL,
  `pre_trial_report` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `paper_user_id`(`user_id`) USING BTREE,
  INDEX `paper_pdf`(`submit`) USING BTREE,
  INDEX `paper_pre_trial_pdf`(`pre_trial_report`) USING BTREE,
  CONSTRAINT `paper_pdf` FOREIGN KEY (`submit`) REFERENCES `x_pdf` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `paper_pre_trial_pdf` FOREIGN KEY (`pre_trial_report`) REFERENCES `x_pdf` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `paper_user_id` FOREIGN KEY (`user_id`) REFERENCES `x_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_paper
-- ----------------------------

-- ----------------------------
-- Table structure for x_pdf
-- ----------------------------
DROP TABLE IF EXISTS `x_pdf`;
CREATE TABLE `x_pdf`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_pdf
-- ----------------------------

-- ----------------------------
-- Table structure for x_scientific
-- ----------------------------
DROP TABLE IF EXISTS `x_scientific`;
CREATE TABLE `x_scientific`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `journal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commit_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `x_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_scientific
-- ----------------------------
INSERT INTO `x_scientific` VALUES (2, '51255902041', '汤汤', '论文名字', '发表期刊', '发表时间', '类型');
INSERT INTO `x_scientific` VALUES (3, '51255902041', '汤汤', '论文名字1', '发表期刊2', '发表时间3', '类型4');
INSERT INTO `x_scientific` VALUES (4, '51255902041', '汤汤', '论文名字1', '发表期刊2', '发表时间3', '类型4');

-- ----------------------------
-- Table structure for x_user
-- ----------------------------
DROP TABLE IF EXISTS `x_user`;
CREATE TABLE `x_user`  (
  `id` varchar(11) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `faculty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `academic_system` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `research_direction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `political_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `mail` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_user
-- ----------------------------
INSERT INTO `x_user` VALUES ('000', 'manager', '000', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `x_user` VALUES ('51255902041', '汤汤', '123456', '软件学院', '软件学院', '四年', '软件工程', '团员', '18917304018', 'ttt0630@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
