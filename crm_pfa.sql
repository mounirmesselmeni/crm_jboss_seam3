-- MySQL dump 10.13  Distrib 5.5.23, for Linux (x86_64)
--
-- Host: localhost    Database: crm_pfa
-- ------------------------------------------------------
-- Server version	5.5.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `logoURL` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `crmUser_id` bigint(20) DEFAULT NULL,
  `fidelity_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1D0C220D123FCEEF` (`crmUser_id`),
  KEY `FK1D0C220DB25605B` (`fidelity_id`),
  CONSTRAINT `FK1D0C220D123FCEEF` FOREIGN KEY (`crmUser_id`) REFERENCES `BackendUser` (`id`),
  CONSTRAINT `FK1D0C220DB25605B` FOREIGN KEY (`fidelity_id`) REFERENCES `Fidelity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (1,'2012-06-01 16:48:05','2012-06-06 07:03:26','El Mouradi.png','El Mouradi','CLIENT','www.el_moradi.tn',2,NULL),(2,'2012-06-06 07:08:07','2012-06-06 07:08:07','Topnet.png','Topnet','CLIENT','www.topnet.tn',2,1),(3,'2012-06-06 07:09:36','2012-06-06 07:10:23','Geant.png','Geant','CLIENT','www.geant.tn',2,2),(4,'2012-06-06 07:11:43','2012-06-06 10:35:00','Palm Garden.png','Palm Garden','CLIENT','www.palm_garden.tn',2,3);
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Account_Address`
--

DROP TABLE IF EXISTS `Account_Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account_Address` (
  `Account_id` bigint(20) NOT NULL,
  `lstAddresses_id` bigint(20) NOT NULL,
  UNIQUE KEY `lstAddresses_id` (`lstAddresses_id`),
  KEY `FK9AA39FA23E011A19` (`Account_id`),
  KEY `FK9AA39FA2F24D2298` (`lstAddresses_id`),
  CONSTRAINT `FK9AA39FA23E011A19` FOREIGN KEY (`Account_id`) REFERENCES `Account` (`id`),
  CONSTRAINT `FK9AA39FA2F24D2298` FOREIGN KEY (`lstAddresses_id`) REFERENCES `Address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account_Address`
--

LOCK TABLES `Account_Address` WRITE;
/*!40000 ALTER TABLE `Account_Address` DISABLE KEYS */;
INSERT INTO `Account_Address` VALUES (1,1),(2,4),(3,5),(4,6);
/*!40000 ALTER TABLE `Account_Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Account_EmailAdress`
--

DROP TABLE IF EXISTS `Account_EmailAdress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account_EmailAdress` (
  `Account_id` bigint(20) NOT NULL,
  `lstEmails_id` bigint(20) NOT NULL,
  UNIQUE KEY `lstEmails_id` (`lstEmails_id`),
  KEY `FKFD722DE03E011A19` (`Account_id`),
  KEY `FKFD722DE0120D7607` (`lstEmails_id`),
  CONSTRAINT `FKFD722DE0120D7607` FOREIGN KEY (`lstEmails_id`) REFERENCES `EmailAdress` (`id`),
  CONSTRAINT `FKFD722DE03E011A19` FOREIGN KEY (`Account_id`) REFERENCES `Account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account_EmailAdress`
--

LOCK TABLES `Account_EmailAdress` WRITE;
/*!40000 ALTER TABLE `Account_EmailAdress` DISABLE KEYS */;
INSERT INTO `Account_EmailAdress` VALUES (1,1),(2,4),(2,5),(2,6),(3,7),(3,8),(4,9);
/*!40000 ALTER TABLE `Account_EmailAdress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Account_PhoneNumber`
--

DROP TABLE IF EXISTS `Account_PhoneNumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account_PhoneNumber` (
  `Account_id` bigint(20) NOT NULL,
  `lstPhoneNumbers_id` bigint(20) NOT NULL,
  UNIQUE KEY `lstPhoneNumbers_id` (`lstPhoneNumbers_id`),
  KEY `FK65CB3705FB189207` (`lstPhoneNumbers_id`),
  KEY `FK65CB37053E011A19` (`Account_id`),
  CONSTRAINT `FK65CB37053E011A19` FOREIGN KEY (`Account_id`) REFERENCES `Account` (`id`),
  CONSTRAINT `FK65CB3705FB189207` FOREIGN KEY (`lstPhoneNumbers_id`) REFERENCES `PhoneNumber` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account_PhoneNumber`
--

LOCK TABLES `Account_PhoneNumber` WRITE;
/*!40000 ALTER TABLE `Account_PhoneNumber` DISABLE KEYS */;
INSERT INTO `Account_PhoneNumber` VALUES (1,1),(2,3),(2,4),(3,5),(4,6);
/*!40000 ALTER TABLE `Account_PhoneNumber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ActivationCode`
--

DROP TABLE IF EXISTS `ActivationCode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ActivationCode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK741EF443F51CF139` (`contact_id`),
  CONSTRAINT `FK741EF443F51CF139` FOREIGN KEY (`contact_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ActivationCode`
--

LOCK TABLES `ActivationCode` WRITE;
/*!40000 ALTER TABLE `ActivationCode` DISABLE KEYS */;
/*!40000 ALTER TABLE `ActivationCode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `gouvernment` varchar(255) NOT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `street` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
INSERT INTO `Address` VALUES (1,'2012-06-01 16:48:05','2012-06-06 07:03:26','Tunis','TUNIS','1004','Place D\'Afrique','TRAVAIL'),(2,'2012-06-01 16:48:54','2012-06-05 23:08:23','ariana','TUNIS','2080','rue mustapha hjayij, imm 5 app 37','TRAVAIL'),(3,'2012-06-05 23:08:23','2012-06-05 23:08:23','tunis','TUNIS','1002','20, rue habib bourguiga','DOMICILE'),(4,'2012-06-06 07:08:07','2012-06-06 07:08:07','Ariana','TUNIS','2008','20, rue Alger','TRAVAIL'),(5,'2012-06-06 07:09:36','2012-06-06 07:10:23','Ariana','TUNIS','2045','20, avenue la liberte','TRAVAIL'),(6,'2012-06-06 07:11:43','2012-06-06 07:11:43','Tunis','TUNIS','1002','14, rue Tayeb Lemhiri','TRAVAIL'),(7,'2012-06-06 07:18:15','2012-06-06 07:18:15','Sakier Ezzit','SFAX','1233','20, rue Turquie','DOMICILE'),(8,'2012-06-06 07:22:41','2012-06-06 07:22:41','Ezzahra','TUNIS','3012','14, rue Tripoli','TRAVAIL'),(9,'2012-06-06 07:25:49','2012-06-06 07:25:49','Tunis','TUNIS','1000','8, avenue de Carthage','TRAVAIL'),(10,'2012-06-06 10:47:57','2012-06-06 10:47:57','Ariana','TUNIS','2080','58,rue hedi chaker','TRAVAIL');
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BackendUser`
--

DROP TABLE IF EXISTS `BackendUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BackendUser` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5492A45FF1C94363` (`id`),
  CONSTRAINT `FK5492A45FF1C94363` FOREIGN KEY (`id`) REFERENCES `CrmUser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BackendUser`
--

LOCK TABLES `BackendUser` WRITE;
/*!40000 ALTER TABLE `BackendUser` DISABLE KEYS */;
INSERT INTO `BackendUser` VALUES (2);
/*!40000 ALTER TABLE `BackendUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
INSERT INTO `Category` VALUES (1,'2012-06-02 20:16:08','2012-06-06 07:31:48','Small Cameras'),(2,'2012-06-06 06:31:24','2012-06-06 07:32:03','Big Cameras'),(3,'2012-06-06 06:31:36','2012-06-06 07:32:15','Cables'),(4,'2012-06-06 07:32:24','2012-06-06 07:32:24','Accessoires');
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Contact`
--

DROP TABLE IF EXISTS `Contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Contact` (
  `imageURL` varchar(255) DEFAULT NULL,
  `salutation` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `accountId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9BEFBC00F1C94363` (`id`),
  KEY `FK9BEFBC00FCBDB4` (`accountId`),
  CONSTRAINT `FK9BEFBC00F1C94363` FOREIGN KEY (`id`) REFERENCES `CrmUser` (`id`),
  CONSTRAINT `FK9BEFBC00FCBDB4` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contact`
--

LOCK TABLES `Contact` WRITE;
/*!40000 ALTER TABLE `Contact` DISABLE KEYS */;
INSERT INTO `Contact` VALUES ('mounir.png','MR',1,1),('slim.png','MR',3,2),('rafik.png','MR',4,3),('samira.png','MLLE',5,4),('amir.png','MR',6,3);
/*!40000 ALTER TABLE `Contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Contact_Address`
--

DROP TABLE IF EXISTS `Contact_Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Contact_Address` (
  `Contact_id` bigint(20) NOT NULL,
  `lstAddresses_id` bigint(20) NOT NULL,
  UNIQUE KEY `lstAddresses_id` (`lstAddresses_id`),
  KEY `FK29D39695F51CF139` (`Contact_id`),
  KEY `FK29D39695F24D2298` (`lstAddresses_id`),
  CONSTRAINT `FK29D39695F24D2298` FOREIGN KEY (`lstAddresses_id`) REFERENCES `Address` (`id`),
  CONSTRAINT `FK29D39695F51CF139` FOREIGN KEY (`Contact_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contact_Address`
--

LOCK TABLES `Contact_Address` WRITE;
/*!40000 ALTER TABLE `Contact_Address` DISABLE KEYS */;
INSERT INTO `Contact_Address` VALUES (1,2),(1,3),(3,7),(4,8),(5,9),(6,10);
/*!40000 ALTER TABLE `Contact_Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Contact_PhoneNumber`
--

DROP TABLE IF EXISTS `Contact_PhoneNumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Contact_PhoneNumber` (
  `Contact_id` bigint(20) NOT NULL,
  `lstPhoneNumbers_id` bigint(20) NOT NULL,
  UNIQUE KEY `lstPhoneNumbers_id` (`lstPhoneNumbers_id`),
  KEY `FK5D707C78F51CF139` (`Contact_id`),
  KEY `FK5D707C78FB189207` (`lstPhoneNumbers_id`),
  CONSTRAINT `FK5D707C78F51CF139` FOREIGN KEY (`Contact_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK5D707C78FB189207` FOREIGN KEY (`lstPhoneNumbers_id`) REFERENCES `PhoneNumber` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contact_PhoneNumber`
--

LOCK TABLES `Contact_PhoneNumber` WRITE;
/*!40000 ALTER TABLE `Contact_PhoneNumber` DISABLE KEYS */;
INSERT INTO `Contact_PhoneNumber` VALUES (1,2),(3,7),(3,8),(4,9),(5,10),(6,11);
/*!40000 ALTER TABLE `Contact_PhoneNumber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CrmUser`
--

DROP TABLE IF EXISTS `CrmUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CrmUser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CrmUser`
--

LOCK TABLES `CrmUser` WRITE;
/*!40000 ALTER TABLE `CrmUser` DISABLE KEYS */;
INSERT INTO `CrmUser` VALUES (1,'2012-06-01 16:48:54','2012-06-01 16:48:54','Messelmeni','Mounir','mounir'),(2,'2012-06-02 01:30:53','2012-06-02 01:30:53','Affes','Mohamed Ali','admin'),(3,'2012-06-06 07:18:15','2012-06-06 07:18:28','Ben Ayed','Slim','slim'),(4,'2012-06-06 07:22:41','2012-06-06 07:22:41','Ben Yedder','Rafik','rafik'),(5,'2012-06-06 07:25:49','2012-06-06 10:35:26','Laroussi','Samira','samira'),(6,'2012-06-06 10:47:57','2012-06-06 10:47:57','Jarraya','Amir','amir');
/*!40000 ALTER TABLE `CrmUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CrmUser_EmailAdress`
--

DROP TABLE IF EXISTS `CrmUser_EmailAdress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CrmUser_EmailAdress` (
  `CrmUser_id` bigint(20) NOT NULL,
  `lstEmails_id` bigint(20) NOT NULL,
  UNIQUE KEY `lstEmails_id` (`lstEmails_id`),
  KEY `FKD99C5C7CA2C46CB9` (`CrmUser_id`),
  KEY `FKD99C5C7C120D7607` (`lstEmails_id`),
  CONSTRAINT `FKD99C5C7C120D7607` FOREIGN KEY (`lstEmails_id`) REFERENCES `EmailAdress` (`id`),
  CONSTRAINT `FKD99C5C7CA2C46CB9` FOREIGN KEY (`CrmUser_id`) REFERENCES `CrmUser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CrmUser_EmailAdress`
--

LOCK TABLES `CrmUser_EmailAdress` WRITE;
/*!40000 ALTER TABLE `CrmUser_EmailAdress` DISABLE KEYS */;
INSERT INTO `CrmUser_EmailAdress` VALUES (1,2),(1,3),(3,10),(4,11),(5,12),(6,13),(6,14);
/*!40000 ALTER TABLE `CrmUser_EmailAdress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Devis`
--

DROP TABLE IF EXISTS `Devis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Devis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3EDEFDFF51CF139` (`contact_id`),
  CONSTRAINT `FK3EDEFDFF51CF139` FOREIGN KEY (`contact_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Devis`
--

LOCK TABLES `Devis` WRITE;
/*!40000 ALTER TABLE `Devis` DISABLE KEYS */;
/*!40000 ALTER TABLE `Devis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Devis_ItemToPurchase`
--

DROP TABLE IF EXISTS `Devis_ItemToPurchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Devis_ItemToPurchase` (
  `Devis_id` bigint(20) NOT NULL,
  `itemsToPurchase_id` bigint(20) NOT NULL,
  UNIQUE KEY `itemsToPurchase_id` (`itemsToPurchase_id`),
  KEY `FKD0AF588F93D12FAE` (`itemsToPurchase_id`),
  KEY `FKD0AF588FEBD94499` (`Devis_id`),
  CONSTRAINT `FKD0AF588F93D12FAE` FOREIGN KEY (`itemsToPurchase_id`) REFERENCES `ItemToPurchase` (`id`),
  CONSTRAINT `FKD0AF588FEBD94499` FOREIGN KEY (`Devis_id`) REFERENCES `Devis` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Devis_ItemToPurchase`
--

LOCK TABLES `Devis_ItemToPurchase` WRITE;
/*!40000 ALTER TABLE `Devis_ItemToPurchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `Devis_ItemToPurchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EmailAdress`
--

DROP TABLE IF EXISTS `EmailAdress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EmailAdress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `emailAdress` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `emailAdress` (`emailAdress`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmailAdress`
--

LOCK TABLES `EmailAdress` WRITE;
/*!40000 ALTER TABLE `EmailAdress` DISABLE KEYS */;
INSERT INTO `EmailAdress` VALUES (1,'2012-06-01 16:48:05','2012-06-06 07:03:26','mouradi@hotel.tn'),(2,'2012-06-01 16:48:54','2012-06-06 10:45:58','messelmeni.mounir2@gmail.com'),(3,'2012-06-05 23:18:53','2012-06-05 23:18:53','test.crm@aol.com'),(4,'2012-06-06 07:08:07','2012-06-06 07:08:07','topnet@princip.tn'),(5,'2012-06-06 07:08:07','2012-06-06 07:08:07','topnet@sales.tn'),(6,'2012-06-06 07:08:07','2012-06-06 07:08:07','donotreply@topnet.tn'),(7,'2012-06-06 07:09:36','2012-06-06 07:09:36','geant@sales.tn'),(8,'2012-06-06 07:09:36','2012-06-06 07:09:36','geant@rh.tn'),(9,'2012-06-06 07:11:43','2012-06-06 07:11:43','palmgarden@gmail.tn'),(10,'2012-06-06 07:18:15','2012-06-06 07:21:29','benayedslim@gmail.com'),(11,'2012-06-06 07:22:41','2012-06-06 07:24:36','benyedder.rafik@gmail.com'),(12,'2012-06-06 07:25:49','2012-06-06 07:28:38','laroussi.samira@gmail.com'),(13,'2012-06-06 10:47:57','2012-06-06 10:47:57','messelmeni.mounir@gmail.com'),(14,'2012-06-06 10:47:57','2012-06-06 10:47:57','jarraya.amir@gmail.com');
/*!40000 ALTER TABLE `EmailAdress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fidelity`
--

DROP TABLE IF EXISTS `Fidelity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fidelity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `score` int(11) NOT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCA6727663E011A19` (`account_id`),
  CONSTRAINT `FKCA6727663E011A19` FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fidelity`
--

LOCK TABLES `Fidelity` WRITE;
/*!40000 ALTER TABLE `Fidelity` DISABLE KEYS */;
INSERT INTO `Fidelity` VALUES (1,'2012-06-06 07:08:07','2012-06-06 07:08:07',5,NULL),(2,'2012-06-06 07:09:36','2012-06-06 07:40:11',6,NULL),(3,'2012-06-06 07:11:43','2012-06-06 07:11:43',5,NULL);
/*!40000 ALTER TABLE `Fidelity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IdentityObject`
--

DROP TABLE IF EXISTS `IdentityObject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IdentityObject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `IDENTITY_OBJECT_TYPE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`IDENTITY_OBJECT_TYPE_ID`),
  KEY `FKB760C5BD3DDCEF05` (`IDENTITY_OBJECT_TYPE_ID`),
  CONSTRAINT `FKB760C5BD3DDCEF05` FOREIGN KEY (`IDENTITY_OBJECT_TYPE_ID`) REFERENCES `IdentityObjectType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityObject`
--

LOCK TABLES `IdentityObject` WRITE;
/*!40000 ALTER TABLE `IdentityObject` DISABLE KEYS */;
INSERT INTO `IdentityObject` VALUES (11,'admin',2),(15,'amir',2),(1,'crm',1),(7,'mounir',2),(13,'rafik',2),(14,'samira',2),(12,'slim',2);
/*!40000 ALTER TABLE `IdentityObject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IdentityObjectAttribute`
--

DROP TABLE IF EXISTS `IdentityObjectAttribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IdentityObjectAttribute` (
  `attributeId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `IDENTITY_OBJECT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`attributeId`),
  UNIQUE KEY `name` (`name`,`IDENTITY_OBJECT_ID`),
  KEY `FKEB1F295FC42863A4` (`IDENTITY_OBJECT_ID`),
  CONSTRAINT `FKEB1F295FC42863A4` FOREIGN KEY (`IDENTITY_OBJECT_ID`) REFERENCES `IdentityObject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityObjectAttribute`
--

LOCK TABLES `IdentityObjectAttribute` WRITE;
/*!40000 ALTER TABLE `IdentityObjectAttribute` DISABLE KEYS */;
INSERT INTO `IdentityObjectAttribute` VALUES (3,'informationUser','1',7),(4,'informationUser','2',11),(5,'informationUser','3',12),(6,'informationUser','4',13),(7,'informationUser','5',14),(8,'informationUser','6',15);
/*!40000 ALTER TABLE `IdentityObjectAttribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IdentityObjectCredential`
--

DROP TABLE IF EXISTS `IdentityObjectCredential`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IdentityObjectCredential` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) DEFAULT NULL,
  `IDENTITY_OBJECT_ID` bigint(20) DEFAULT NULL,
  `CREDENTIAL_TYPE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDENTITY_OBJECT_ID` (`IDENTITY_OBJECT_ID`,`CREDENTIAL_TYPE_ID`),
  KEY `FK37DC41F4C42863A4` (`IDENTITY_OBJECT_ID`),
  KEY `FK37DC41F48B732EF3` (`CREDENTIAL_TYPE_ID`),
  CONSTRAINT `FK37DC41F48B732EF3` FOREIGN KEY (`CREDENTIAL_TYPE_ID`) REFERENCES `IdentityObjectCredentialType` (`id`),
  CONSTRAINT `FK37DC41F4C42863A4` FOREIGN KEY (`IDENTITY_OBJECT_ID`) REFERENCES `IdentityObject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityObjectCredential`
--

LOCK TABLES `IdentityObjectCredential` WRITE;
/*!40000 ALTER TABLE `IdentityObjectCredential` DISABLE KEYS */;
INSERT INTO `IdentityObjectCredential` VALUES (3,'elmes',7,1),(7,'admin',11,1),(8,'slim',12,1),(9,'rafik',13,1),(10,'samira',14,1),(11,'amir',15,1);
/*!40000 ALTER TABLE `IdentityObjectCredential` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IdentityObjectCredentialType`
--

DROP TABLE IF EXISTS `IdentityObjectCredentialType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IdentityObjectCredentialType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityObjectCredentialType`
--

LOCK TABLES `IdentityObjectCredentialType` WRITE;
/*!40000 ALTER TABLE `IdentityObjectCredentialType` DISABLE KEYS */;
INSERT INTO `IdentityObjectCredentialType` VALUES (1,'password');
/*!40000 ALTER TABLE `IdentityObjectCredentialType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IdentityObjectRelationship`
--

DROP TABLE IF EXISTS `IdentityObjectRelationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IdentityObjectRelationship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `FROM_IDENTITY_ID` bigint(20) DEFAULT NULL,
  `RELATIONSHIP_TYPE_ID` bigint(20) DEFAULT NULL,
  `TO_IDENTITY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `FROM_IDENTITY_ID` (`FROM_IDENTITY_ID`,`TO_IDENTITY_ID`,`RELATIONSHIP_TYPE_ID`,`name`),
  KEY `FK8C4CDC953DE562F1` (`FROM_IDENTITY_ID`),
  KEY `FK8C4CDC95A06CA842` (`TO_IDENTITY_ID`),
  KEY `FK8C4CDC95EB830EB5` (`RELATIONSHIP_TYPE_ID`),
  CONSTRAINT `FK8C4CDC953DE562F1` FOREIGN KEY (`FROM_IDENTITY_ID`) REFERENCES `IdentityObject` (`id`),
  CONSTRAINT `FK8C4CDC95A06CA842` FOREIGN KEY (`TO_IDENTITY_ID`) REFERENCES `IdentityObject` (`id`),
  CONSTRAINT `FK8C4CDC95EB830EB5` FOREIGN KEY (`RELATIONSHIP_TYPE_ID`) REFERENCES `IdentityObjectRelationshipType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityObjectRelationship`
--

LOCK TABLES `IdentityObjectRelationship` WRITE;
/*!40000 ALTER TABLE `IdentityObjectRelationship` DISABLE KEYS */;
INSERT INTO `IdentityObjectRelationship` VALUES (3,'client',1,1,7),(4,'admin',1,1,11),(5,'client',1,1,12),(6,'client',1,1,13),(7,'client',1,1,14),(8,'client',1,1,15);
/*!40000 ALTER TABLE `IdentityObjectRelationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IdentityObjectRelationshipType`
--

DROP TABLE IF EXISTS `IdentityObjectRelationshipType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IdentityObjectRelationshipType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityObjectRelationshipType`
--

LOCK TABLES `IdentityObjectRelationshipType` WRITE;
/*!40000 ALTER TABLE `IdentityObjectRelationshipType` DISABLE KEYS */;
INSERT INTO `IdentityObjectRelationshipType` VALUES (2,'JBOSS_IDENTITY_MEMBERSHIP'),(1,'JBOSS_IDENTITY_ROLE');
/*!40000 ALTER TABLE `IdentityObjectRelationshipType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IdentityObjectType`
--

DROP TABLE IF EXISTS `IdentityObjectType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IdentityObjectType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityObjectType`
--

LOCK TABLES `IdentityObjectType` WRITE;
/*!40000 ALTER TABLE `IdentityObjectType` DISABLE KEYS */;
INSERT INTO `IdentityObjectType` VALUES (1,'GROUP'),(2,'USER');
/*!40000 ALTER TABLE `IdentityObjectType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IdentityPermission`
--

DROP TABLE IF EXISTS `IdentityPermission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IdentityPermission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) DEFAULT NULL,
  `relationshipName` varchar(255) DEFAULT NULL,
  `resource` varchar(255) DEFAULT NULL,
  `identityObject_id` bigint(20) NOT NULL,
  `relationshipType_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8B94794D3425D2E7` (`identityObject_id`),
  KEY `FK8B94794D33794484` (`relationshipType_id`),
  CONSTRAINT `FK8B94794D33794484` FOREIGN KEY (`relationshipType_id`) REFERENCES `IdentityObjectRelationshipType` (`id`),
  CONSTRAINT `FK8B94794D3425D2E7` FOREIGN KEY (`identityObject_id`) REFERENCES `IdentityObject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityPermission`
--

LOCK TABLES `IdentityPermission` WRITE;
/*!40000 ALTER TABLE `IdentityPermission` DISABLE KEYS */;
/*!40000 ALTER TABLE `IdentityPermission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IdentityRoleName`
--

DROP TABLE IF EXISTS `IdentityRoleName`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IdentityRoleName` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityRoleName`
--

LOCK TABLES `IdentityRoleName` WRITE;
/*!40000 ALTER TABLE `IdentityRoleName` DISABLE KEYS */;
INSERT INTO `IdentityRoleName` VALUES (1,'admin'),(3,'client'),(2,'commercial');
/*!40000 ALTER TABLE `IdentityRoleName` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ItemToPurchase`
--

DROP TABLE IF EXISTS `ItemToPurchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemToPurchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC79C742FA1A0AD9` (`product_id`),
  CONSTRAINT `FKC79C742FA1A0AD9` FOREIGN KEY (`product_id`) REFERENCES `Product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ItemToPurchase`
--

LOCK TABLES `ItemToPurchase` WRITE;
/*!40000 ALTER TABLE `ItemToPurchase` DISABLE KEYS */;
INSERT INTO `ItemToPurchase` VALUES (1,'2012-06-02 20:17:16','2012-06-05 14:28:29',20,1),(2,'2012-06-06 07:39:49','2012-06-06 07:39:49',20,4),(3,'2012-06-06 07:39:49','2012-06-06 07:39:49',12,1);
/*!40000 ALTER TABLE `ItemToPurchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MeetingConfirmation`
--

DROP TABLE IF EXISTS `MeetingConfirmation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MeetingConfirmation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `confirmed` tinyint(1) NOT NULL,
  `content` longtext,
  `contactToConfirm_id` bigint(20) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD8D771F0995C1C7B` (`task_id`),
  KEY `FKD8D771F046270F74` (`contactToConfirm_id`),
  CONSTRAINT `FKD8D771F046270F74` FOREIGN KEY (`contactToConfirm_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FKD8D771F0995C1C7B` FOREIGN KEY (`task_id`) REFERENCES `Task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MeetingConfirmation`
--

LOCK TABLES `MeetingConfirmation` WRITE;
/*!40000 ALTER TABLE `MeetingConfirmation` DISABLE KEYS */;
/*!40000 ALTER TABLE `MeetingConfirmation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Notification`
--

DROP TABLE IF EXISTS `Notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `readed` tinyint(1) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notification`
--

LOCK TABLES `Notification` WRITE;
/*!40000 ALTER TABLE `Notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `Notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NotificationContact`
--

DROP TABLE IF EXISTS `NotificationContact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NotificationContact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `backendUser_id` bigint(20) DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  `notification_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC1742C95EACB179` (`backendUser_id`),
  KEY `FKC1742C95675729BB` (`notification_id`),
  KEY `FKC1742C95F51CF139` (`contact_id`),
  CONSTRAINT `FKC1742C95675729BB` FOREIGN KEY (`notification_id`) REFERENCES `Notification` (`id`),
  CONSTRAINT `FKC1742C95EACB179` FOREIGN KEY (`backendUser_id`) REFERENCES `BackendUser` (`id`),
  CONSTRAINT `FKC1742C95F51CF139` FOREIGN KEY (`contact_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NotificationContact`
--

LOCK TABLES `NotificationContact` WRITE;
/*!40000 ALTER TABLE `NotificationContact` DISABLE KEYS */;
/*!40000 ALTER TABLE `NotificationContact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Opportunity`
--

DROP TABLE IF EXISTS `Opportunity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Opportunity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `closeDate` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `backendUserId` bigint(20) DEFAULT NULL,
  `contactId` bigint(20) DEFAULT NULL,
  `accepted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK16648EB3E2BB9238` (`backendUserId`),
  KEY `FK16648EB3D44540DA` (`contactId`),
  CONSTRAINT `FK16648EB3D44540DA` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK16648EB3E2BB9238` FOREIGN KEY (`backendUserId`) REFERENCES `BackendUser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Opportunity`
--

LOCK TABLES `Opportunity` WRITE;
/*!40000 ALTER TABLE `Opportunity` DISABLE KEYS */;
INSERT INTO `Opportunity` VALUES (1,'2012-06-02 20:17:16','2012-06-06 07:38:36','2012-06-15','','Offre fin d\'annee',2,2,3,0),(2,'2012-06-06 07:39:49','2012-06-06 07:40:11','2012-06-22','','Protion fete',2,2,4,1);
/*!40000 ALTER TABLE `Opportunity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Opportunity_ItemToPurchase`
--

DROP TABLE IF EXISTS `Opportunity_ItemToPurchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Opportunity_ItemToPurchase` (
  `Opportunity_id` bigint(20) NOT NULL,
  `itemsToPurchase_id` bigint(20) NOT NULL,
  UNIQUE KEY `itemsToPurchase_id` (`itemsToPurchase_id`),
  KEY `FK9734B73B3ECB38D9` (`Opportunity_id`),
  KEY `FK9734B73B93D12FAE` (`itemsToPurchase_id`),
  CONSTRAINT `FK9734B73B3ECB38D9` FOREIGN KEY (`Opportunity_id`) REFERENCES `Opportunity` (`id`),
  CONSTRAINT `FK9734B73B93D12FAE` FOREIGN KEY (`itemsToPurchase_id`) REFERENCES `ItemToPurchase` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Opportunity_ItemToPurchase`
--

LOCK TABLES `Opportunity_ItemToPurchase` WRITE;
/*!40000 ALTER TABLE `Opportunity_ItemToPurchase` DISABLE KEYS */;
INSERT INTO `Opportunity_ItemToPurchase` VALUES (1,1),(2,2),(2,3);
/*!40000 ALTER TABLE `Opportunity_ItemToPurchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PhoneNumber`
--

DROP TABLE IF EXISTS `PhoneNumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PhoneNumber` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `number` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PhoneNumber`
--

LOCK TABLES `PhoneNumber` WRITE;
/*!40000 ALTER TABLE `PhoneNumber` DISABLE KEYS */;
INSERT INTO `PhoneNumber` VALUES (1,'2012-06-01 16:48:05','2012-06-01 16:48:05','55-555-555','TRAVAIL'),(2,'2012-06-01 16:48:54','2012-06-01 16:48:54','22 487 036','TRAVAIL'),(3,'2012-06-06 07:08:07','2012-06-06 07:08:07','71-256-987','TRAVAIL'),(4,'2012-06-06 07:08:07','2012-06-06 07:08:07','71-445-896','TRAVAIL'),(5,'2012-06-06 07:09:36','2012-06-06 07:09:36','71-555-599','TRAVAIL'),(6,'2012-06-06 07:11:43','2012-06-06 07:11:43','71-548-963','TRAVAIL'),(7,'2012-06-06 07:18:15','2012-06-06 07:18:15','55 555 555','PORTABLE'),(8,'2012-06-06 07:18:15','2012-06-06 07:18:15','74 859 658','DOMICILE'),(9,'2012-06-06 07:22:41','2012-06-06 07:22:41','71 589 654','TRAVAIL'),(10,'2012-06-06 07:25:49','2012-06-06 07:25:49','44 558 899','TRAVAIL'),(11,'2012-06-06 10:47:57','2012-06-06 10:47:57','22 222 222','TRAVAIL');
/*!40000 ALTER TABLE `PhoneNumber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `tva_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK50C664CF2C8A655B` (`category_id`),
  KEY `FK50C664CFAB3B5059` (`tva_id`),
  CONSTRAINT `FK50C664CF2C8A655B` FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`),
  CONSTRAINT `FK50C664CFAB3B5059` FOREIGN KEY (`tva_id`) REFERENCES `TVA` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,'2012-06-02 20:16:49','2012-06-06 07:33:28','','Surveill-1200_21454612121.png','Surveill-1200',10,200,'21454612121',1,1),(2,'2012-06-03 14:47:34','2012-06-06 07:33:49','','HaltSurveill-895_4846132121.png','HaltSurveill-895',1,2000,'4846132121',1,1),(3,'2012-06-06 06:39:31','2012-06-06 07:34:20','','PicoSurveill-1899_sds54885sd.png','PicoSurveill-1899',0.3,1500,'sds54885sd',1,4),(4,'2012-06-06 06:40:21','2012-06-06 07:34:44','','CADER-522_SON-5485-hgf.png','CADER-522',100.2,500,'SON-5485-hgf',2,1),(5,'2012-06-06 06:41:16','2012-06-06 07:35:12','','Ultimate-1200_SAMS-54821lko.png','Ultimate-1200',250.6,240,'SAMS-54821lko',2,2),(6,'2012-06-06 06:42:04','2012-06-06 07:35:34','','LC connector 45_CHAUSS-548pol.png','LC connector 45',45.9,856,'CHAUSS-548pol',3,1),(7,'2012-06-06 06:42:57','2012-06-06 07:36:01','','VB tivk 548_N-50124.png','VB tivk 548',1099.8,420,'N-50124',3,3),(8,'2012-06-06 06:43:41','2012-06-06 07:36:49','','FRSurveill-98_548-gtf-77.png','FRSurveill-98',1277,560,'548-gtf-77',2,1),(9,'2012-06-06 07:37:50','2012-06-06 09:47:22','','Ubraid52_sdsd215.png','Ubraid52',85,6500,'sdsd215',4,4);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TVA`
--

DROP TABLE IF EXISTS `TVA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TVA` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `taux` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TVA`
--

LOCK TABLES `TVA` WRITE;
/*!40000 ALTER TABLE `TVA` DISABLE KEYS */;
INSERT INTO `TVA` VALUES (1,'2012-06-02 20:16:16','2012-06-06 06:31:56','',15),(2,'2012-06-03 11:02:14','2012-06-06 06:32:03','',12),(3,'2012-06-06 06:31:47','2012-06-06 06:31:47','',18),(4,'2012-06-06 06:32:08','2012-06-06 06:32:08','',6);
/*!40000 ALTER TABLE `TVA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Task`
--

DROP TABLE IF EXISTS `Task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `taskType` varchar(255) DEFAULT NULL,
  `assignedTo_id` bigint(20) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  `accepted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27A9A5B06F87F0` (`assignedTo_id`),
  KEY `FK27A9A5B37B8A8C` (`creator_id`),
  CONSTRAINT `FK27A9A5B06F87F0` FOREIGN KEY (`assignedTo_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK27A9A5B37B8A8C` FOREIGN KEY (`creator_id`) REFERENCES `BackendUser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Task`
--

LOCK TABLES `Task` WRITE;
/*!40000 ALTER TABLE `Task` DISABLE KEYS */;
INSERT INTO `Task` VALUES (1,'2012-06-03 14:36:56','2012-06-03 20:02:34','','2012-06-13 08:00:00',0,'2012-06-10 16:00:00','Daly','FAX',NULL,NULL,0),(2,'2012-06-03 14:49:15','2012-06-03 20:04:19','appler dupont pour corriger la vente ref 25556633322','2012-06-14 06:00:00',0,'2012-06-14 00:30:00','Vente opp 34','APPEL',NULL,NULL,0),(3,'2012-06-03 15:08:02','2012-06-03 17:16:17',NULL,'2012-05-15 00:00:00',NULL,'2012-05-25 00:00:00','Soutenance PFA @this','APPEL',NULL,NULL,0),(4,'2012-06-03 18:13:24','2012-06-03 19:40:28','','2012-06-07 05:00:00',3,'2012-06-07 03:00:00','Mounir23','EMAIL',NULL,NULL,0),(5,'2012-06-04 00:11:07','2012-06-06 07:46:57','','2012-06-04 17:00:00',3,'2012-06-04 15:00:00','Reunion Chiffre d\'Affaire','REUNION',1,2,1),(6,'2012-06-04 00:11:24','2012-06-06 07:47:20','','2012-06-05 18:30:00',2,'2012-06-05 15:00:00','Negotiation contrat','REUNION',3,2,0),(7,'2012-06-06 01:57:00','2012-06-06 07:47:44','','2012-06-06 00:00:00',0,'2012-06-06 00:00:00','Renouvellement contrat','APPEL',5,2,0),(8,'2012-06-06 01:57:23','2012-06-06 07:49:03','','2012-06-04 12:00:00',0,'2012-06-04 11:00:00','Envoyer campagne','EMAIL',5,2,0),(9,'2012-06-06 02:02:09','2012-06-06 07:48:31','','2012-06-07 12:00:00',0,'2012-06-07 10:00:00','Test de materiel','REUNION',3,2,1),(10,'2012-06-06 07:49:43','2012-06-06 07:49:51','','2012-05-30 00:00:00',1,'2012-05-30 00:00:00','Test de materiel','REUNION',4,2,1);
/*!40000 ALTER TABLE `Task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ticket`
--

DROP TABLE IF EXISTS `Ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `content` longtext,
  `resolved` tinyint(1) NOT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK954D572C3EFD89AD` (`creator_id`),
  CONSTRAINT `FK954D572C3EFD89AD` FOREIGN KEY (`creator_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ticket`
--

LOCK TABLES `Ticket` WRITE;
/*!40000 ALTER TABLE `Ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `Ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TicketResponse`
--

DROP TABLE IF EXISTS `TicketResponse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TicketResponse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `content` longtext,
  `title` varchar(255) DEFAULT NULL,
  `crmUser_id` bigint(20) DEFAULT NULL,
  `ticket_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA55AE38DA42FA6DB` (`ticket_id`),
  KEY `FKA55AE38DA2C46CB9` (`crmUser_id`),
  CONSTRAINT `FKA55AE38DA2C46CB9` FOREIGN KEY (`crmUser_id`) REFERENCES `CrmUser` (`id`),
  CONSTRAINT `FKA55AE38DA42FA6DB` FOREIGN KEY (`ticket_id`) REFERENCES `Ticket` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TicketResponse`
--

LOCK TABLES `TicketResponse` WRITE;
/*!40000 ALTER TABLE `TicketResponse` DISABLE KEYS */;
/*!40000 ALTER TABLE `TicketResponse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (11);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-06 12:48:17
