/*
 Navicat MySQL Data Transfer

 Source Server         : home
 Source Server Type    : MySQL
 Source Server Version : 80000
 Source Host           : localhost:3306
 Source Schema         : escola

 Target Server Type    : MySQL
 Target Server Version : 80000
 File Encoding         : 65001

 Date: 04/06/2022 22:13:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aluno
-- ----------------------------
DROP TABLE IF EXISTS `aluno`;
CREATE TABLE `aluno`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nomeMae` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nomePai` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dataNascimento` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dataCadastro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aluno
-- ----------------------------
INSERT INTO `aluno` VALUES (11, 'Daniel', 'Ruth', 'Hilario', '16/09/1991', '04.06.2022');

-- ----------------------------
-- Table structure for professor
-- ----------------------------
DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataNascimento` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataCadastro` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of professor
-- ----------------------------
INSERT INTO `professor` VALUES (1, 'DR Ranchucrutes', '14/03/1980', '04.06.2022');
INSERT INTO `professor` VALUES (2, 'Michelangelo', '06/03/1475', '04.06.2022');

-- ----------------------------
-- Table structure for turma
-- ----------------------------
DROP TABLE IF EXISTS `turma`;
CREATE TABLE `turma`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idProfessor` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idProfessor_idx`(`idProfessor`) USING BTREE,
  CONSTRAINT `idProfessor` FOREIGN KEY (`idProfessor`) REFERENCES `professor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of turma
-- ----------------------------
INSERT INTO `turma` VALUES (1, 'Matematica', 1);
INSERT INTO `turma` VALUES (2, 'Arte', 2);

-- ----------------------------
-- Table structure for turma_aluno
-- ----------------------------
DROP TABLE IF EXISTS `turma_aluno`;
CREATE TABLE `turma_aluno`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idAluno` int(11) NULL DEFAULT NULL,
  `idTurma` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idAluno_idx`(`idAluno`) USING BTREE,
  INDEX `idTurma_idx`(`idTurma`) USING BTREE,
  CONSTRAINT `idAluno` FOREIGN KEY (`idAluno`) REFERENCES `aluno` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idTurma` FOREIGN KEY (`idTurma`) REFERENCES `turma` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of turma_aluno
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
