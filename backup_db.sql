-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: smcse-stuproj00.city.ac.uk    Database: in2018g08
-- ------------------------------------------------------
-- Server version	5.5.68-MariaDB

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
-- Table structure for table `Administrator`
--

DROP TABLE IF EXISTS `Administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Administrator` (
  `ID` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `Administrator_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `AirViaUser` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Administrator`
--

LOCK TABLES `Administrator` WRITE;
/*!40000 ALTER TABLE `Administrator` DISABLE KEYS */;
INSERT INTO `Administrator` VALUES (200006572),(298575920);
/*!40000 ALTER TABLE `Administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AirViaUser`
--

DROP TABLE IF EXISTS `AirViaUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AirViaUser` (
  `ID` int(10) NOT NULL,
  `Role` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AirViaUser`
--

LOCK TABLES `AirViaUser` WRITE;
/*!40000 ALTER TABLE `AirViaUser` DISABLE KEYS */;
INSERT INTO `AirViaUser` VALUES (200006572,'System Administrator','Abdul','Rehman','unijet.tp.ats@gmail.com','ef901c4463e617fbcd30933b4f5c2c55'),(200007193,'Travel Advisor','Jamie-Lee','Gordon','unijet.tp.ats@gmail.com','678176ffcd02e849c5fba0b1a7391c'),(200008122,'Travel Advisor','Ali','Ul-Haq','unijet.tp.ats@gmail.com','d7627506f77098ea48ba40fd33e69e26'),(200008133,'Office Manager','Charlie','Wal','unijet.tp.ats@gmail.com','37ce25a9ddde35760ee90368497173b5'),(200011111,'Office Manager','Hassan','Wallace','unijet.tp.ats@gmail.com','e499fab30ead0cd9ae88b3700a4fe79d'),(200012834,'Travel Advisor','Sahi','Pathmalingam','unijet.tp.ats@gmail.com','5157d8c80f956f7c2a19d8e82e601170'),(200037193,'Travel Advisor','Kelly','Smith','unijet.tp.ats@gmail.com','41ed01c18dfcab16cb372fdfb60832f9'),(200081454,'Office Manager','Tharshika','Sivananthan','unijet.tp.ats@gmail.com','49b0fe85c04413850221490d46073974'),(200097652,'System Administrator','Alley','Wallace','unijet.tp.ats@gmail.com','4006630f0f6254122811d3c9c4eca6f0'),(200208232,'Travel Advisor','John','Harris','unijet.tp.ats@gmail.com','6e0b7076126a29d5dfcbd54835387b7b'),(200234561,'Travel Advisor','Lauturo','Martinez','unijet.tp.ats@gmail.com','c3358c133309830adb0c0c8bc36da64'),(200578493,'Office Manager','Junaid','Alam','unijet.tp.ats@gmail.com','d218256e7a4ffa26505216018ff63b5c'),(200987465,'Travel Advisor','Tahmid','Alam','unijet.tp.ats@gmail.com','9687695f83f04cc6a717b1b6089083b6'),(202012834,'Travel Advisor','Lilly','Man','unijet.tp.ats@gmail.com','44ccafea67e44a1ca3c5ecb4bf74d415'),(202081454,'Office Manager','Allen','Athan','unijet.tp.ats@gmail.com','86c8baface4c599a120a6aa896f366e6'),(202578493,'Office Manager','Juid','Aam','unijet.tp.ats@gmail.com','d0f8baff1851f7ceaddd31bfc294b20a'),(202647589,'Office Manager','Jonny','Kelly','unijet.tp.ats@gmail.com','7e2dba5a3ff8ecdee19734e0152d7eb0'),(209876782,'System Administrator','Jessica','Smyth','unijet.tp.ats@gmail.com','62344705b86a2063db6c9fa97b5f63c0'),(210021377,'Office Manager','Abdul','Rehman','unijet.tp.ats@gmail.com','57ba172a6be125cca2f449826f9980ca'),(234323400,'System Administrator','Cristiano','Ronaldo','unijet.tp.ats@gmail.com','dded8ae1808a8b531cac39e48d43de3f'),(234543234,'Travel Advisor','Josh','Hassan','unijet.tp.ats@gmail.com','95dc947b27dcbb5f1d8eb16773bb77fc');
/*!40000 ALTER TABLE `AirViaUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BlankStock`
--

DROP TABLE IF EXISTS `BlankStock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BlankStock` (
  `ID` int(255) NOT NULL,
  `BlankType` varchar(255) DEFAULT NULL,
  `Status` varchar(10) NOT NULL,
  `UsedStatus` varchar(255) DEFAULT NULL,
  `AirViaUserID` int(10) DEFAULT NULL,
  `AdvisorID` int(10) DEFAULT NULL,
  `TicketID` int(10) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `TicketID` (`TicketID`),
  KEY `AdvisorID` (`AdvisorID`),
  KEY `AirViaUserID` (`AirViaUserID`),
  CONSTRAINT `BlankStock_ibfk_1` FOREIGN KEY (`TicketID`) REFERENCES `Ticket` (`ID`),
  CONSTRAINT `BlankStock_ibfk_2` FOREIGN KEY (`AdvisorID`) REFERENCES `TravelAdvisor` (`ID`),
  CONSTRAINT `BlankStock_ibfk_3` FOREIGN KEY (`AirViaUserID`) REFERENCES `AirViaUser` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BlankStock`
--

LOCK TABLES `BlankStock` WRITE;
/*!40000 ALTER TABLE `BlankStock` DISABLE KEYS */;
INSERT INTO `BlankStock` VALUES (1019933,'44400000001 – 44400000020\r\n','Assigned','Not Used',200987465,200008122,1610,'2023-02-22'),(1236572,'10100000001 – 10100000050','Assigned',NULL,NULL,200007193,NULL,'2019-05-08'),(1245781,'10100000001 – 10100000050','Assigned',NULL,NULL,200007193,NULL,'2023-04-07'),(2298934,'44400000001 – 44400000020\r\n','Assigned','Used',200007193,200037193,2232,'2021-06-20'),(3359348,'44400000001 – 44400000020\r\n','Assigned','Used',200008122,200007193,3098,'2022-09-11'),(7657719,'10100000001 – 10100000050','Recieved',NULL,NULL,NULL,NULL,'2023-09-09'),(22334455,'44400000001 - 44400000020','Assigned',NULL,NULL,200007193,NULL,'2023-03-25');
/*!40000 ALTER TABLE `BlankStock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CommisionRate`
--

DROP TABLE IF EXISTS `CommisionRate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CommisionRate` (
  `TicketType` int(10) NOT NULL,
  `Percentage` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`TicketType`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CommisionRate`
--

LOCK TABLES `CommisionRate` WRITE;
/*!40000 ALTER TABLE `CommisionRate` DISABLE KEYS */;
INSERT INTO `CommisionRate` VALUES (101,6.00),(201,NULL),(420,0.12),(440,0.15),(444,NULL);
/*!40000 ALTER TABLE `CommisionRate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `ID` int(10) NOT NULL,
  `FirstName` varchar(15) NOT NULL,
  `LastName` varchar(15) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Type` varchar(15) NOT NULL,
  `AdvisorID` int(10) DEFAULT NULL,
  `DiscountPlan` varchar(255) DEFAULT NULL,
  `Percentage` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AdvisorID` (`AdvisorID`),
  CONSTRAINT `Customer_ibfk_1` FOREIGN KEY (`AdvisorID`) REFERENCES `TravelAdvisor` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (123446,'Jose','Tilly','tills@gmail.com','valued',NULL,'Fixed',15.00),(123456,'Joseph','Goodman','goodman@gmail.com','Valued',NULL,'fixed',5.00),(157684,'Dagmar','Ridgewell','dridgewell0@outlook.com','Valued',248448333,'Fixed',7.50),(240204,'Chane','Zellmer','czellmer1@github.io','Valued',200007193,'Fixed',7.00),(410293,'Madelle','Forder','mforder3@example.com','Regular',200987465,NULL,NULL),(577732,'Barri','Handling','bhandling4@privacy.gov.au','Regular',200008122,NULL,NULL),(667536,'Harry','Gerrard','gerr@gmail.com','Valued',NULL,NULL,NULL),(768755,'Eva','Smith','eva@yahoo.com','Valued',NULL,NULL,NULL);
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ExchangeRates`
--

DROP TABLE IF EXISTS `ExchangeRates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ExchangeRates` (
  `Country` varchar(255) NOT NULL,
  `RateToUSD` decimal(10,4) NOT NULL,
  `LocalTax` decimal(10,4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExchangeRates`
--

LOCK TABLES `ExchangeRates` WRITE;
/*!40000 ALTER TABLE `ExchangeRates` DISABLE KEYS */;
INSERT INTO `ExchangeRates` VALUES ('USA',1.0000,0.0000),('Canada',0.8000,0.0500),('Mexico',0.0500,0.1500),('United Kingdom',1.3900,0.2000),('Germany',1.1700,0.1900),('Japan',0.0092,0.0800),('China',0.1500,0.1000),('India',0.0140,0.0500),('Australia',0.7500,0.1000),('Brazil',0.1800,0.2000),('South Africa',0.0660,0.1500),('Russia',0.0130,0.1800),('France',1.1800,0.2000),('Spain',1.1800,0.2100);
/*!40000 ALTER TABLE `ExchangeRates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OfficeManager`
--

DROP TABLE IF EXISTS `OfficeManager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `OfficeManager` (
  `ID` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `OfficeManager_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `AirViaUser` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OfficeManager`
--

LOCK TABLES `OfficeManager` WRITE;
/*!40000 ALTER TABLE `OfficeManager` DISABLE KEYS */;
INSERT INTO `OfficeManager` VALUES (200081454),(200578493),(200647589);
/*!40000 ALTER TABLE `OfficeManager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sales`
--

DROP TABLE IF EXISTS `Sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Sales` (
  `ID` int(10) NOT NULL,
  `BlankStockID` int(10) NOT NULL,
  `CustomerID` int(10) NOT NULL,
  `CommissionRateTicketType` int(10) DEFAULT NULL,
  `AdvisorID` int(10) NOT NULL,
  `PaymentType` varchar(255) NOT NULL,
  `SaleType` varchar(255) NOT NULL,
  `Country` varchar(255) DEFAULT NULL,
  `TotalAmount` int(10) DEFAULT NULL,
  `AmountPaid` int(10) DEFAULT NULL,
  `Status` varchar(255) NOT NULL,
  `Date` date DEFAULT NULL,
  `LocalTax` decimal(7,2) DEFAULT NULL,
  `OtherTax` decimal(7,2) DEFAULT NULL,
  `ExchangeRate` decimal(4,2) DEFAULT NULL,
  `CommisionRate` decimal(4,2) DEFAULT NULL,
  `LocalCurrency` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `BlankStockID` (`BlankStockID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `CommissionRateTicketType` (`CommissionRateTicketType`),
  KEY `AdvisorID` (`AdvisorID`),
  CONSTRAINT `Sales_ibfk_1` FOREIGN KEY (`BlankStockID`) REFERENCES `BlankStock` (`ID`),
  CONSTRAINT `Sales_ibfk_2` FOREIGN KEY (`CustomerID`) REFERENCES `Customer` (`ID`),
  CONSTRAINT `Sales_ibfk_3` FOREIGN KEY (`CommissionRateTicketType`) REFERENCES `CommisionRate` (`TicketType`),
  CONSTRAINT `Sales_ibfk_4` FOREIGN KEY (`AdvisorID`) REFERENCES `TravelAdvisor` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sales`
--

LOCK TABLES `Sales` WRITE;
/*!40000 ALTER TABLE `Sales` DISABLE KEYS */;
INSERT INTO `Sales` VALUES (1001,1019933,157684,420,248448333,'Credit Card','Interline','USA',500,500,'Paid','2016-01-22',NULL,NULL,0.54,NULL,NULL),(1002,2298934,240204,101,200007193,'Cash','Domestic','United Kingdom',1000,700,'Refunded','2023-03-15',NULL,NULL,0.49,9.00,130.00),(1003,3359348,310933,444,200012834,'Debit Card','Interline','Japan',750,750,'Paid','2019-10-22',NULL,NULL,0.51,NULL,NULL),(1004,4342049,410293,440,200987465,'Credit Card','Interline','USA',2000,1000,'Partially Paid','2030-12-03',NULL,NULL,0.54,NULL,NULL),(1005,5339483,577732,201,200008122,'Cash','Domestic','China',1500,1500,'Paid','2023-09-11',NULL,NULL,0.45,NULL,NULL),(1006,3359348,157684,NULL,200007193,'Card','Domestic','Belgium',NULL,NULL,'Refunded','2022-06-11',20.00,40.00,0.54,15.00,220.00),(1010,1019933,123446,NULL,200007193,'Cash','Interline','USA',NULL,NULL,'Paid','2023-01-01',23.00,35.00,0.54,9.00,220.00),(1021,1236572,768755,NULL,200007193,'Credit Card: VISA 4901 0002 2345 3456','Interline','GER',NULL,NULL,'Paid','2023-01-01',43.00,55.00,0.54,9.00,230.00),(1031,1236572,157684,NULL,200007193,'Cash','Interline','USA',NULL,NULL,'can pay later','2023-10-01',80.00,44.00,0.54,12.00,400.00),(1035,1236572,667536,NULL,200007193,'Cash','Domestic','GER',NULL,NULL,'Refunded','2021-09-11',23.00,80.00,0.45,9.00,300.00),(1088,1236572,765455,NULL,200007193,'Cash','Interline','USA',NULL,NULL,'Paid','2023-04-02',90.00,60.00,0.54,9.00,180.00);
/*!40000 ALTER TABLE `Sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ticket`
--

DROP TABLE IF EXISTS `Ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ticket` (
  `ID` int(10) NOT NULL,
  `Departure` varchar(255) NOT NULL,
  `Arrival` varchar(255) NOT NULL,
  `Price` decimal(19,0) DEFAULT NULL,
  `CustomerID` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `Ticket_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `Customer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ticket`
--

LOCK TABLES `Ticket` WRITE;
/*!40000 ALTER TABLE `Ticket` DISABLE KEYS */;
INSERT INTO `Ticket` VALUES (1610,'Dubai - Dubai International Airport(DXB)','Istanbul - Istanbul Atatürk Airport(IST)',500,157684),(2232,'Los Angeles (CA) - International','London - Heathrow(LHR)',1000,240204),(3098,'Athens, Hellinikon Airport(HEW)','Istanbul - Istanbul Atatürk Airport(IST)',250,310933),(4123,'London - Heathrow(LHR)','Paris(PAR)',300,410293),(5090,'Dubai - Dubai International Airport(DXB)','Moscow - Metropolitan Area(MOW)',700,577732);
/*!40000 ALTER TABLE `Ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TravelAdvisor`
--

DROP TABLE IF EXISTS `TravelAdvisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TravelAdvisor` (
  `ID` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `TravelAdvisor_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `AirViaUser` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TravelAdvisor`
--

LOCK TABLES `TravelAdvisor` WRITE;
/*!40000 ALTER TABLE `TravelAdvisor` DISABLE KEYS */;
INSERT INTO `TravelAdvisor` VALUES (200007193),(200008122),(200012834),(200987465),(248448333);
/*!40000 ALTER TABLE `TravelAdvisor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-11 19:28:03
