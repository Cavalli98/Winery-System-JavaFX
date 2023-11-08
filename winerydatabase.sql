-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: winerydatabase
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bottle`
--

DROP TABLE IF EXISTS `bottle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bottle` (
  `id_bottle` bigint NOT NULL,
  `features` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `vines` varchar(255) DEFAULT NULL,
  `year` int DEFAULT NULL,
  PRIMARY KEY (`id_bottle`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bottle`
--

LOCK TABLES `bottle` WRITE;
/*!40000 ALTER TABLE `bottle` DISABLE KEYS */;
INSERT INTO `bottle` VALUES (1,'','Malvasia','San Giovanni',1990),(11,'','Lambrusco','',1980),(4,'','Malvasia','San Giovanni',1990),(10,'','Lambrusco','',1980),(7,'','Malvasia','San Giovanni',1990),(9,'','Lambrusco','',1980),(12,'','Malvasia','San Giovanni',1990),(14,'','Lambrusco','',2000),(15,'','Lambrusco','',2000),(16,'','Lambrusco','',2000),(17,'','Lambrusco','',2000),(18,'','Lambrusco','',2000),(19,'','Lambrusco','',2000),(20,'','Lambrusco','',2000),(21,'','Lambrusco','',2000),(22,'','Lambrusco','',2000),(23,'','Lambrusco','',2000),(24,'','Lambrusco','',2000),(25,'','Lambrusco','',2000),(26,'','Lambrusco','',2000),(27,'','Lambrusco','',2000),(28,'','Lambrusco','',2000),(29,'','Chianti','',2010),(30,'','Chianti','',2010),(31,'','Chianti','',2010),(32,'','Chianti','',2010),(33,'','Chianti','',2010),(34,'','Chianti','',2010),(35,'','Chianti','',2010),(36,'','Chianti','',2010),(37,'','Chianti','',2010),(38,'','Chianti','',2010),(39,'','Chianti','',2010),(40,'','Chianti','',2010),(41,'','Chianti','',2010),(42,'','Chianti','',2010),(43,'','Chianti','',2010),(44,'','Chianti','',2010),(45,'','Chianti','',2010),(46,'','Chianti','',2010),(47,'','Gutturnio','',2008),(48,'','Gutturnio','',2008),(49,'','Gutturnio','',2008),(50,'','Gutturnio','',2008),(51,'','Gutturnio','',2008),(52,'','Gutturnio','',2008),(53,'','Gutturnio','',2008),(54,'','Gutturnio','',2008),(55,'','Gutturnio','',2008),(56,'','Gutturnio','',2008),(57,'','Gutturnio','',2008),(58,'','Gutturnio','',2008),(59,'','Gutturnio','',2008),(60,'','Gutturnio','',2008),(61,'','Gutturnio','',2008),(62,'','Gutturnio','',2008),(63,'','Gutturnio','',2008),(64,'','Gutturnio','',2008),(65,'','Gutturnio','',2008),(66,'','Gutturnio','',2008),(67,'','Chianti','',2016),(68,'','Chianti','',2016),(69,'','Chianti','',2016),(70,'','Chianti','',2016),(71,'','Chianti','',2016),(72,'','Chianti','',2016),(73,'','Chianti','',2016),(74,'','Chianti','',2016),(75,'','Chianti','',2016),(76,'','Chianti','',2016),(77,'','Chianti','',2016),(78,'','Chianti','',2016),(79,'','Chianti','',2016),(80,'','Chianti','',2016),(81,'','Chianti','',2016),(82,'','Chianti','',2016),(83,'','Chianti','',2016),(84,'','Chianti','',2016),(85,'','Chianti','',2016),(86,'','Chianti','',2016),(89,'','Chianti','',2015),(90,'','Chianti','',2015),(91,'','Chianti','',2015),(92,'','Chianti','',2015),(93,'','Chianti','',2015),(94,'','Chianti','',2015),(95,'','Chianti','',2015),(96,'','Chianti','',2015),(97,'','Chianti','',2015),(98,'','Chianti','',2015),(99,'','Chianti','',2015),(100,'','Chianti','',2015),(101,'','Chianti','',2015),(105,'','Gutturnio','',2016),(106,'','Gutturnio','',2016),(107,'','Gutturnio','',2016),(108,'','Gutturnio','',2016),(109,'','Gutturnio','',2016),(110,'','Gutturnio','',2016),(111,'','Gutturnio','',2016),(112,'','Lambrusco','',2005),(113,'','Lambrusco','',2005),(114,'','Lambrusco','',2005),(115,'','Lambrusco','',2005),(116,'','Lambrusco','',2005),(117,'','Lambrusco','',2005),(118,'','Lambrusco','',2005),(119,'','Lambrusco','',2005),(120,'','Lambrusco','',2005),(121,'','Lambrusco','',2005),(122,'','Lambrusco','',2005),(123,'','Lambrusco','',2005),(124,'','Lambrusco','',2011),(125,'','Lambrusco','',2011),(126,'','Lambrusco','',2011),(127,'','Lambrusco','',2011),(128,'','Lambrusco','',2011),(129,'','Lambrusco','',2011),(130,'','Lambrusco','',2011),(131,'','Lambrusco','',2011),(132,'','Lambrusco','',2011),(133,'','Lambrusco','',2011),(134,'','Lambrusco','',2011),(135,'','Lambrusco','',2011),(136,'','Lambrusco','',2011),(137,'','Lambrusco','',2011),(138,'','Lambrusco','',2011),(139,'','Lambrusco','',2011),(140,'','Lambrusco','',2011),(141,'','Lambrusco','',2011),(142,'','Lambrusco','',2011),(143,'','Lambrusco','',2011),(144,'','Lambrusco','',2017),(145,'','Lambrusco','',2017),(146,'','Lambrusco','',2017),(147,'','Lambrusco','',2017),(148,'','Lambrusco','',2017),(149,'','Lambrusco','',2017),(150,'','Lambrusco','',2017),(151,'','Lambrusco','',2017),(152,'','Lambrusco','',2016),(153,'','Lambrusco','',2016),(154,'','Lambrusco','',2016),(155,'','Lambrusco','',2016),(156,'','Lambrusco','',2016),(157,'','Lambrusco','',2016),(158,'','Lambrusco','',2016),(159,'','Lambrusco','',2016),(160,'','Lambrusco','',2016),(161,'','Lambrusco','',2016);
/*!40000 ALTER TABLE `bottle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `identification` varchar(20) NOT NULL,
  PRIMARY KEY (`identification`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('00284274'),('00285348'),('00287755');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (162);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `wine_year` int NOT NULL,
  `wine_name` varchar(30) NOT NULL,
  `mail` varchar(100) NOT NULL,
  PRIMARY KEY (`wine_year`,`wine_name`,`mail`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1992,'Lambrusco','prova@prova.com');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `mail` varchar(250) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mail`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('dariocavalli@gmail.com','03111710'),('prova@prova.com','prova'),('uz@gmail.com','uzpassw'),('a@a.aa','aaaa'),('sdc@gmail.com','sdcm'),('m@gmail.com','mmmm'),('agostino.poggi@unipr.it','unprogettopiulungononceloaveva?'),('damianocavalli@gmail.com','pingu');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notification`
--

DROP TABLE IF EXISTS `user_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_notification` (
  `mail` varchar(250) NOT NULL,
  `notification` varchar(255) DEFAULT NULL,
  KEY `FKpde2oli8tddhsi0qb3hnyr0ba` (`mail`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notification`
--

LOCK TABLES `user_notification` WRITE;
/*!40000 ALTER TABLE `user_notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `winery`
--

DROP TABLE IF EXISTS `winery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `winery` (
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `winery`
--

LOCK TABLES `winery` WRITE;
/*!40000 ALTER TABLE `winery` DISABLE KEYS */;
INSERT INTO `winery` VALUES ('La Vineria');
/*!40000 ALTER TABLE `winery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `winetoadd`
--

DROP TABLE IF EXISTS `winetoadd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `winetoadd` (
  `name` varchar(100) NOT NULL,
  `wineToAdd` varchar(255) DEFAULT NULL,
  KEY `FK4xl3jjxrnkl4dwfgcq85wp850` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `winetoadd`
--

LOCK TABLES `winetoadd` WRITE;
/*!40000 ALTER TABLE `winetoadd` DISABLE KEYS */;
/*!40000 ALTER TABLE `winetoadd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `winetosend`
--

DROP TABLE IF EXISTS `winetosend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `winetosend` (
  `year` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`year`,`name`,`mail`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `winetosend`
--

LOCK TABLES `winetosend` WRITE;
/*!40000 ALTER TABLE `winetosend` DISABLE KEYS */;
INSERT INTO `winetosend` VALUES (2015,'Chianti','dariocavalli@gmail.com',2),(1992,'Lambrusco','prova@prova.com',1),(2016,'Gutturnio','dariocavalli@gmail.com',3);
/*!40000 ALTER TABLE `winetosend` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-23 19:04:43
