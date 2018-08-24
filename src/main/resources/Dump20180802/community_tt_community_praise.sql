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
-- Table structure for table `tt_community_praise`
--

DROP TABLE IF EXISTS `tt_community_praise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tt_community_praise` (
  `business_type` int(11) DEFAULT NULL COMMENT '业务类型',
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `phone` varchar(20) DEFAULT NULL COMMENT '点赞人手机',
  `praise_time` datetime DEFAULT NULL COMMENT '点赞时间',
  `business_id` int(11) DEFAULT NULL COMMENT '业务id',
  `status` int(11) DEFAULT NULL COMMENT '点赞状态 0已取消点赞 1：已点赞',
  `last_update` datetime DEFAULT NULL COMMENT '最近更新时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` int(20) DEFAULT NULL COMMENT '创建人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新人',
  `business_creater` int(20) DEFAULT NULL COMMENT '业务创建人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_praise_business_id` (`business_id`) USING BTREE,
  KEY `index_praise_phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=939 DEFAULT CHARSET=utf8 COMMENT='社群点赞表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tt_community_praise`
--

LOCK TABLES `tt_community_praise` WRITE;
/*!40000 ALTER TABLE `tt_community_praise` DISABLE KEYS */;
/*!40000 ALTER TABLE `tt_community_praise` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-02 16:58:56
