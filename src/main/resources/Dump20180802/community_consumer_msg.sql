-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: community
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `consumer_msg`
--

DROP TABLE IF EXISTS `consumer_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `consumer_msg` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `msg_key` varchar(50) NOT NULL DEFAULT '' COMMENT '消息key',
  `biz_class_name` varchar(80) NOT NULL DEFAULT '' COMMENT '业务类名称',
  `consumer_class_name` varchar(80) NOT NULL DEFAULT '' COMMENT '监听类名称',
  `create_time` datetime NOT NULL COMMENT '消息接受时间',
  `exchange` varchar(20) NOT NULL DEFAULT '' COMMENT '交换机名称',
  `info_msg` varchar(300) DEFAULT NULL COMMENT '消息异常信息',
  `msg_content` mediumtext NOT NULL COMMENT '消息体内容',
  `retry_count` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '消费次数',
  `router_key` varchar(40) NOT NULL DEFAULT '' COMMENT '消息队列名称',
  `status` smallint(30) unsigned NOT NULL COMMENT '消息状态：100 消息消费初始化,101 消息消费成功,102 消息消费失败，103 消息处理中',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_msg_key` (`msg_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59735 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumer_msg`
--

LOCK TABLES `consumer_msg` WRITE;
/*!40000 ALTER TABLE `consumer_msg` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumer_msg` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-02 16:58:54
