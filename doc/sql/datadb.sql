# Create Database
# ------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS DataDB DEFAULT CHARACTER SET = utf8mb4;

Use DataDB;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL DEFAULT 'default' COMMENT '字典名称',
  `code` varchar(32) NOT NULL DEFAULT 'default' COMMENT '字典编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

SET FOREIGN_KEY_CHECKS = 1;
