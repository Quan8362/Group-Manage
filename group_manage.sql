-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: group_management
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `username` varchar(25) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `password` varchar(250) NOT NULL,
  `role` enum('Admin','Manager','Employee') NOT NULL,
  `create_date` date DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Email1@gmail.com','Username1','Fullname1','$2a$12$5COBIj1pjMuayCucIHuX5usF1q50n/mJKIa31ImkgjcorhPnWKBDW','Admin','2020-03-05'),(2,'Email2@gmail.com','Username2','Fullname2','$2a$12$LEZ8w6Qt/E63plSlLwC7mOBOmPI3a2LTIhDbPAXxmaPyZGXStxkUa','Employee','2020-02-08'),(3,'Email3@gmail.com','Username3','Fullname3','C123@123','Admin','2021-03-07'),(4,'Email4@gmail.com','Username4','Fullname4','D123@123','Manager','2020-03-17'),(5,'Email5@gmail.com','Username5','Fullname5','E123@123','Employee','2022-02-28'),(6,'Email6@gmail.com','Username6','Fullname6','F123@123','Admin','2021-06-21'),(7,'Email7@gmail.com','Username7','Fullname7','G123@123','Manager','2022-03-22'),(8,'Email8@gmail.com','Username8','Fullname8','H123@123','Admin','2022-01-07'),(9,'Email9@gmail.com','Username9','Fullname9','L123@123','Employee','2022-05-08'),(10,'Email10@gmail.com','Username10','Fullname10','I123@123','Manager','2021-08-07');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group` (
  `group_id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  `creator_id` int NOT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `group_name` (`group_name`),
  KEY `creator_id` (`creator_id`),
  CONSTRAINT `group_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (1,'Testing System',5,'2022-03-05 00:00:00'),(2,'Development',1,'2020-03-07 00:00:00'),(3,'VTI Sale 01',2,'2020-03-09 00:00:00'),(4,'VTI Sale 02',3,'2020-03-10 00:00:00'),(5,'VTI Sale 03',4,'2021-03-28 00:00:00'),(6,'VTI Creator',6,'2021-08-06 00:00:00'),(7,'VTI Marketing 01',7,'2022-04-07 00:00:00'),(8,'Management',8,'2022-09-08 00:00:00'),(9,'Chat with love',9,'2022-11-09 00:00:00'),(10,'Vi Ti Ai',10,'2021-10-10 00:00:00');
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_account`
--

DROP TABLE IF EXISTS `group_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_account` (
  `group_account_id` int NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `account_id` int NOT NULL,
  `join_date` date DEFAULT NULL,
  PRIMARY KEY (`group_account_id`),
  KEY `group_id` (`group_id`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `group_account_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_account_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_account`
--

LOCK TABLES `group_account` WRITE;
/*!40000 ALTER TABLE `group_account` DISABLE KEYS */;
INSERT INTO `group_account` VALUES (1,1,1,'2022-10-05'),(2,1,2,'2022-05-07'),(3,3,3,'2022-03-09'),(4,3,4,'2022-03-10'),(5,5,5,'2022-03-28'),(6,1,3,'2022-09-06'),(7,1,7,'2022-06-07'),(8,8,3,'2022-11-25'),(9,1,9,'2022-10-09'),(10,10,10,'2022-08-10');
/*!40000 ALTER TABLE `group_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-13 21:36:26
