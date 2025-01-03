-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ledger
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `accountbalance`
--

DROP TABLE IF EXISTS `accountbalance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accountbalance` (
  `user_id` int NOT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  `savings` decimal(10,2) DEFAULT NULL,
  `loan` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountbalance`
--

LOCK TABLES `accountbalance` WRITE;
/*!40000 ALTER TABLE `accountbalance` DISABLE KEYS */;
INSERT INTO `accountbalance` VALUES (13,1059.93,NULL,NULL),(14,1300.50,NULL,NULL),(17,139424.00,1350.00,0.00),(18,436482.92,148.08,7631.66),(19,1411.00,120.00,0.00);
/*!40000 ALTER TABLE `accountbalance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank` (
  `bank_id` int NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(100) DEFAULT NULL,
  `interest_rate` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`bank_id`),
  UNIQUE KEY `bank_id_UNIQUE` (`bank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans` (
  `loan_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `principal_amount` decimal(10,2) DEFAULT NULL,
  `interest_rate` decimal(10,2) DEFAULT NULL,
  `repayment_period` int DEFAULT NULL,
  `outstanding_balance` decimal(10,2) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_at` timestamp(2) NULL DEFAULT NULL,
  PRIMARY KEY (`loan_id`),
  UNIQUE KEY `loan_id_UNIQUE` (`loan_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT ` user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES (4,18,10000.00,12.00,24,0.00,'Paid','2023-12-26 17:25:37.73'),(5,18,12323.00,1.00,23,0.00,'Paid','2023-12-26 17:38:28.91'),(7,18,10000.00,2.00,1,0.00,'Paid','2023-12-31 14:24:28.87'),(8,18,10000.00,1.00,0,0.00,'Paid','2024-01-31 15:15:54.52'),(9,18,5000.00,1.00,1,0.00,'Paid','2024-02-02 03:58:24.01'),(10,18,10000.00,12.00,3,7631.66,'Unpaid','2025-01-03 09:00:54.97');
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `loans_AFTER_INSERT` AFTER INSERT ON `loans` FOR EACH ROW BEGIN
UPDATE accountbalance
SET loan = NEW.outstanding_balance
WHERE user_id=NEW.user_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `loans_AFTER_UPDATE` AFTER UPDATE ON `loans` FOR EACH ROW BEGIN
UPDATE accountbalance
SET loan = NEW.outstanding_balance
WHERE user_id=NEW.user_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `savingloan`
--

DROP TABLE IF EXISTS `savingloan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savingloan` (
  `transactions_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `transaction_type` varchar(45) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `date` date DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`transactions_id`),
  UNIQUE KEY `transactions_id_UNIQUE` (`transactions_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savingloan`
--

LOCK TABLES `savingloan` WRITE;
/*!40000 ALTER TABLE `savingloan` DISABLE KEYS */;
INSERT INTO `savingloan` VALUES (1,18,'repaid',9954.66,'2025-01-03',0.00),(2,18,'saving',148.08,'2025-01-03',148.08),(3,18,'repaid',2323.00,'2025-01-03',7631.66),(4,18,'saving',500.00,'2025-02-03',0.00),(5,18,'saving',700.00,'2025-03-03',0.00);
/*!40000 ALTER TABLE `savingloan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savings`
--

DROP TABLE IF EXISTS `savings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savings` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `status` tinyint NOT NULL DEFAULT '0',
  `percentage` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `user_id_index` (`user_id`) /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savings`
--

LOCK TABLES `savings` WRITE;
/*!40000 ALTER TABLE `savings` DISABLE KEYS */;
INSERT INTO `savings` VALUES (17,0,0.00),(18,0,0.00),(19,0,0.00);
/*!40000 ALTER TABLE `savings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `transaction_type` varchar(45) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `balance` decimal(10,2) NOT NULL,
  PRIMARY KEY (`transaction_id`),
  UNIQUE KEY `transaction_id_UNIQUE` (`transaction_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (11,13,'Debit',1300.50,'Living Expense','2024-12-15',0.00),(12,13,'Credit',240.57,'Rental Fee','2024-12-15',0.00),(13,14,'Debit',1300.50,'test','2024-12-15',0.00),(15,18,'Debit',10.00,'asd','2024-12-17',1485.00),(16,18,'Credit',123.00,'ks','2024-12-17',1362.00),(17,18,'Debit',546.00,'wqewqeqw','2024-12-17',1908.00),(18,18,'Credit',15000.00,'qwewwe','2024-12-17',-13092.00),(19,17,'Debit',12345.00,'asd','2024-12-17',12345.00),(20,17,'Credit',1238.00,'a','2024-12-17',11107.00),(21,17,'Debit',121600.00,'k','2024-12-17',132707.00),(22,17,'Credit',123.00,'k','2024-12-17',132584.00),(23,17,'Debit',120.00,'a','2024-12-17',132704.00),(24,17,'Debit',123.00,'asd','2024-12-17',132827.00),(25,17,'Debit',456.00,'as','2024-12-17',133283.00),(26,17,'Debit',123.00,'asd','2024-12-17',133406.00),(27,17,'Debit',1234.00,'as','2024-12-17',134640.00),(28,17,'Debit',1234.00,'as','2024-12-17',135874.00),(29,18,'Debit',41411.00,'a','2024-12-18',28319.00),(30,18,'Debit',123.00,'a','2024-12-18',28442.00),(31,17,'Debit',1000.00,'a','2024-12-18',136374.00),(32,17,'Debit',1000.00,'s','2024-12-18',136874.00),(33,17,'Debit',1200.00,'d','2024-12-18',137474.00),(34,17,'Debit',300.00,'f','2024-12-18',137624.00),(35,17,'Debit',1000.00,'f','2024-12-18',138124.00),(36,18,'Debit',1000.00,'a','2024-12-18',29442.00),(37,17,'Credit',100.00,'s','2024-12-18',138024.00),(38,17,'Debit',500.00,'d','2024-12-18',138524.00),(39,18,'Debit',1234.00,'123','2024-12-18',30676.00),(40,18,'Debit',12.00,'1','2024-12-19',30688.00),(41,18,'Credit',13.00,'3','2024-12-19',30675.00),(42,19,'Debit',1234.00,'kk','2024-12-21',1234.00),(43,19,'Debit',300.00,'50','2024-12-21',1414.00),(44,19,'Debit',120.00,'hello i want to do something with you so do you want to play?','2024-12-21',1534.00),(45,19,'Credit',123.00,'nothing to do','2024-12-21',1411.00),(46,19,'Credit',123.00,'hsi','2024-12-21',1411.00),(47,18,'Credit',12323.00,'asdsddd','2024-12-21',18352.00),(48,18,'Debit',12323.00,'sd','2024-12-21',30675.00),(49,18,'Debit',123.00,'d','2024-12-27',30798.00),(50,18,'Debit',123.00,'d','2024-12-27',30921.00),(51,18,'Credit',1234.00,'dcf','2024-12-27',29687.00),(52,18,'Credit',123456.00,'a','2024-12-28',-93769.00),(53,18,'Debit',456231.00,'d','2024-12-28',362462.00),(54,17,'Debit',1000.00,'a','2024-12-29',139424.00),(55,18,'Debit',12.00,'a','2024-12-31',372474.00),(56,18,'Credit',123.00,'23','2024-12-31',372351.00),(57,18,'Debit',50000.00,'as','2024-12-31',422351.00),(58,18,'Debit',12334.00,'as','2024-12-31',434685.00),(59,18,'Debit',123.00,'dsd','2024-12-31',435308.00),(60,18,'Debit',1200.00,'123','2025-12-31',436364.00),(61,18,'Debit',123.00,'34','2025-12-31',436602.71),(62,18,'Debit',1234.00,'dsds','2025-01-03',437716.92),(63,18,'Credit',1234.00,'sdsd','2025-01-03',436482.92);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (13,'Yeah Jing Ze','jingze@gmail.com','$2a$10$0M4ehWkt5jpQoHfp3h7A1uBDwT2eayK34z1iSnE852lYcZscWdW5a'),(14,'Choo Tian Long','tianlong@gmail.com','$2a$10$jmGRpC7ZuNh/jsTCpbiGt.cHDByIgrEJt.i1TDLgPPaZinoQ6ub.O'),(16,'Muhammad Ali bin Abu Bakar Shahfiq','shafiq@gmail.com','$2a$10$a.25enC3TJpZ4/QOGahqVOe0QxnJxFYu6IJQuu4eeqvbzDF54FIbi'),(17,'low','lowliyik@gmail.com','$2a$10$VZUk.fdsmslKKrwQ9iEMn.erY66YT4ANgZ/BH3kGNdtf1MjjqUpQ2'),(18,'saw','warren@gmail.com','$2a$10$AesbeC.qmR97uNu5DZ8JrezT2.3bVUraCEx3Z2lyfQneP6UPrwxnG'),(19,'heng','heng@yahoo.com','$2a$10$WAY99bePDX90tFDQMZ/eTuboUvixsJWyg0sQS2UexykxV7eIQ1hTa');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `user_AFTER_INSERT` AFTER INSERT ON `user` FOR EACH ROW BEGIN
INSERT INTO accountbalance
VALUE( NEW.user_id, 0.00, 0.00, 0.00);
INSERT INTO savings
VALUE ( NEW.user_id,0,0);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'ledger'
--

--
-- Dumping routines for database 'ledger'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-03 19:23:17
