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
-- Table structure for table `user_log`
--

DROP TABLE IF EXISTS `user_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_userId` char(32) DEFAULT NULL COMMENT '访问该地址的用户id',
  `log_userName` varchar(255) DEFAULT NULL COMMENT '访问该地址的用户名',
  `log_url` varchar(255) DEFAULT NULL,
  `log_ip` varchar(255) DEFAULT NULL COMMENT '访问改地址的用户id',
  `log_data_time` datetime NOT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_log`
--

LOCK TABLES `user_log` WRITE;
/*!40000 ALTER TABLE `user_log` DISABLE KEYS */;
INSERT INTO `user_log` VALUES (1,NULL,NULL,'/userAccount/login','0:0:0:0:0:0:0:1','2018-08-01 15:58:05'),(2,NULL,NULL,'/userAccount/login','0:0:0:0:0:0:0:1','2018-08-01 16:14:22'),(3,'7d90fec8f29d4dadae888be8f51f3830','admin','/userAccount/login','0:0:0:0:0:0:0:1','2018-08-01 16:16:13'),(4,'7d90fec8f29d4dadae888be8f51f3830','admin','/userAccount/login','0:0:0:0:0:0:0:1','2018-08-01 16:16:19'),(5,'7d90fec8f29d4dadae888be8f51f3830','admin','/userAccount/login','0:0:0:0:0:0:0:1','2018-08-01 16:16:19'),(6,'7d90fec8f29d4dadae888be8f51f3830','admin','/userAccount/login','0:0:0:0:0:0:0:1','2018-08-01 16:16:19'),(7,'7d90fec8f29d4dadae888be8f51f3830','admin','/userAccount/login','0:0:0:0:0:0:0:1','2018-08-01 16:16:20'),(8,'7d90fec8f29d4dadae888be8f51f3830','admin','/userAccount/login','0:0:0:0:0:0:0:1','2018-08-01 16:16:20'),(9,'7d90fec8f29d4dadae888be8f51f3830','admin','/userAccount/login','0:0:0:0:0:0:0:1','2018-08-01 16:16:20'),(10,'7d90fec8f29d4dadae888be8f51f3830','admin','/userAccount/login','0:0:0:0:0:0:0:1','2018-08-01 16:16:20'),(11,NULL,NULL,'/userAccount/register','0:0:0:0:0:0:0:1','2018-08-01 16:49:58'),(12,NULL,NULL,'/userAccount/register','0:0:0:0:0:0:0:1','2018-08-01 16:50:19'),(13,NULL,NULL,'/userAccount/register','0:0:0:0:0:0:0:1','2018-08-01 16:50:35'),(14,NULL,NULL,'/userAccount/register','0:0:0:0:0:0:0:1','2018-08-01 17:27:54'),(15,NULL,NULL,'/userAccount/register','192.168.20.100','2018-08-01 17:32:55');
/*!40000 ALTER TABLE `user_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-02 16:58:55
