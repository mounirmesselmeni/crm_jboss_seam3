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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `fidelityId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1D0C220DEAD4D688` (`fidelityId`),
  KEY `FK1D0C220D64589BBA` (`createdBy_id`),
  KEY `FK1D0C220D69C35C79` (`modifiedBy_id`),
  KEY `FK1D0C220D2F6FCAA4` (`userId`),
  CONSTRAINT `FK1D0C220D2F6FCAA4` FOREIGN KEY (`userId`) REFERENCES `BackendUser` (`id`),
  CONSTRAINT `FK1D0C220D64589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK1D0C220D69C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK1D0C220DEAD4D688` FOREIGN KEY (`fidelityId`) REFERENCES `Fidelity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (1,'2012-05-30 11:43:18','2012-05-30 11:43:18','Mounir.png','Mounir','CLIENT','www.elmes.com',NULL,NULL,NULL,NULL),(2,'2012-05-30 11:48:39','2012-05-30 11:48:39','Mounir2.png','Mounir2','FOURNISSEUR','www.elmes.com',NULL,NULL,NULL,NULL);
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
  CONSTRAINT `FK9AA39FA2F24D2298` FOREIGN KEY (`lstAddresses_id`) REFERENCES `Address` (`id`),
  CONSTRAINT `FK9AA39FA23E011A19` FOREIGN KEY (`Account_id`) REFERENCES `Account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account_Address`
--

LOCK TABLES `Account_Address` WRITE;
/*!40000 ALTER TABLE `Account_Address` DISABLE KEYS */;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK741EF44364589BBA` (`createdBy_id`),
  KEY `FK741EF443F51CF139` (`contact_id`),
  KEY `FK741EF44369C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK741EF44369C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK741EF44364589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK741EF443F51CF139` FOREIGN KEY (`contact_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ED033D464589BBA` (`createdBy_id`),
  KEY `FK1ED033D469C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK1ED033D469C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK1ED033D464589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BackendUser`
--

DROP TABLE IF EXISTS `BackendUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BackendUser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `emailId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5492A45F64589BBA` (`createdBy_id`),
  KEY `FK5492A45F5F052EE8` (`emailId`),
  KEY `FK5492A45F69C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK5492A45F5F052EE8` FOREIGN KEY (`emailId`) REFERENCES `EmailAdress` (`id`),
  CONSTRAINT `FK5492A45F64589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK5492A45F69C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BackendUser`
--

LOCK TABLES `BackendUser` WRITE;
/*!40000 ALTER TABLE `BackendUser` DISABLE KEYS */;
INSERT INTO `BackendUser` VALUES (3,'2012-05-30 15:23:50','2012-05-30 15:23:50','admin',NULL,NULL,NULL),(4,'2012-05-30 15:26:45','2012-05-30 15:26:45','admin2',NULL,NULL,NULL),(5,'2012-05-30 15:30:41','2012-05-30 15:30:41','admin3',NULL,NULL,NULL),(6,'2012-05-30 17:16:34','2012-05-30 17:16:34','test',NULL,NULL,NULL),(7,'2012-05-30 17:17:12','2012-05-30 17:17:12','admin575574',NULL,NULL,NULL),(8,'2012-05-30 17:19:12','2012-05-30 17:19:12','adminfgff',NULL,NULL,NULL),(9,'2012-05-30 17:33:18','2012-05-30 17:33:18','adminqdfsd',NULL,NULL,NULL),(10,'2012-05-30 17:33:58','2012-05-30 17:33:58','qzefzzqf',NULL,NULL,NULL),(11,'2012-05-30 17:39:30','2012-05-30 17:39:30','adminqdfqd',NULL,NULL,NULL),(12,'2012-05-30 18:58:16','2012-05-30 18:58:16','user',NULL,NULL,NULL);
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6DD211E64589BBA` (`createdBy_id`),
  KEY `FK6DD211E69C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK6DD211E64589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK6DD211E69C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Contact`
--

DROP TABLE IF EXISTS `Contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `firstName` varchar(255) NOT NULL,
  `imageURL` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `salutation` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `accountId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  KEY `FK9BEFBC0064589BBA` (`createdBy_id`),
  KEY `FK9BEFBC00FCBDB4` (`accountId`),
  KEY `FK9BEFBC0069C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK9BEFBC0064589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK9BEFBC0069C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK9BEFBC00FCBDB4` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contact`
--

LOCK TABLES `Contact` WRITE;
/*!40000 ALTER TABLE `Contact` DISABLE KEYS */;
INSERT INTO `Contact` VALUES (2,'2012-05-30 11:58:38','2012-05-30 11:58:38','Messelmeni','admin.png','Mounir','admin','MR',NULL,NULL,1);
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
/*!40000 ALTER TABLE `Contact_Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Contact_EmailAdress`
--

DROP TABLE IF EXISTS `Contact_EmailAdress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Contact_EmailAdress` (
  `Contact_id` bigint(20) NOT NULL,
  `lstEmails_id` bigint(20) NOT NULL,
  UNIQUE KEY `lstEmails_id` (`lstEmails_id`),
  KEY `FKF5177353F51CF139` (`Contact_id`),
  KEY `FKF5177353120D7607` (`lstEmails_id`),
  CONSTRAINT `FKF5177353120D7607` FOREIGN KEY (`lstEmails_id`) REFERENCES `EmailAdress` (`id`),
  CONSTRAINT `FKF5177353F51CF139` FOREIGN KEY (`Contact_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contact_EmailAdress`
--

LOCK TABLES `Contact_EmailAdress` WRITE;
/*!40000 ALTER TABLE `Contact_EmailAdress` DISABLE KEYS */;
/*!40000 ALTER TABLE `Contact_EmailAdress` ENABLE KEYS */;
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
  CONSTRAINT `FK5D707C78FB189207` FOREIGN KEY (`lstPhoneNumbers_id`) REFERENCES `PhoneNumber` (`id`),
  CONSTRAINT `FK5D707C78F51CF139` FOREIGN KEY (`Contact_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contact_PhoneNumber`
--

LOCK TABLES `Contact_PhoneNumber` WRITE;
/*!40000 ALTER TABLE `Contact_PhoneNumber` DISABLE KEYS */;
/*!40000 ALTER TABLE `Contact_PhoneNumber` ENABLE KEYS */;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3EDEFDF64589BBA` (`createdBy_id`),
  KEY `FK3EDEFDFF51CF139` (`contact_id`),
  KEY `FK3EDEFDF69C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK3EDEFDF69C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK3EDEFDF64589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
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
  CONSTRAINT `FKD0AF588FEBD94499` FOREIGN KEY (`Devis_id`) REFERENCES `Devis` (`id`),
  CONSTRAINT `FKD0AF588F93D12FAE` FOREIGN KEY (`itemsToPurchase_id`) REFERENCES `ItemToPurchase` (`id`)
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `emailAdress` (`emailAdress`),
  KEY `FKB3F5591264589BBA` (`createdBy_id`),
  KEY `FKB3F5591269C35C79` (`modifiedBy_id`),
  CONSTRAINT `FKB3F5591264589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FKB3F5591269C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmailAdress`
--

LOCK TABLES `EmailAdress` WRITE;
/*!40000 ALTER TABLE `EmailAdress` DISABLE KEYS */;
INSERT INTO `EmailAdress` VALUES (1,'2012-05-30 11:43:18','2012-05-30 11:43:18','messelmeni.moundyuir@gmail.com',NULL,NULL),(2,'2012-05-30 11:43:18','2012-05-30 11:43:18','mounirelmes@gmail.com',NULL,NULL),(3,'2012-05-30 11:48:39','2012-05-30 11:48:39','elmes@elmes.co',NULL,NULL),(5,'2012-05-30 11:58:38','2012-05-30 11:58:38','dhgdghd@sgdgs.com',NULL,NULL);
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCA67276664589BBA` (`createdBy_id`),
  KEY `FKCA6727663E011A19` (`account_id`),
  KEY `FKCA67276669C35C79` (`modifiedBy_id`),
  CONSTRAINT `FKCA6727663E011A19` FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`),
  CONSTRAINT `FKCA67276664589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FKCA67276669C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fidelity`
--

LOCK TABLES `Fidelity` WRITE;
/*!40000 ALTER TABLE `Fidelity` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityObject`
--

LOCK TABLES `IdentityObject` WRITE;
/*!40000 ALTER TABLE `IdentityObject` DISABLE KEYS */;
INSERT INTO `IdentityObject` VALUES (4,'admin',2),(5,'admin2',2),(6,'admin3',2),(8,'admin575574',2),(9,'adminfgff',2),(12,'adminqdfqd',2),(10,'adminqdfsd',2),(1,'crm',1),(11,'qzefzzqf',2),(7,'test',2),(13,'user',2);
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
INSERT INTO `IdentityObjectAttribute` VALUES (1,'informationUser','5',6),(2,'informationUser','6',7),(3,'informationUser','7',8),(4,'informationUser','8',9),(5,'informationUser','9',10),(6,'informationUser','10',11),(7,'informationUser','11',12),(8,'informationUser','12',13);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityObjectCredential`
--

LOCK TABLES `IdentityObjectCredential` WRITE;
/*!40000 ALTER TABLE `IdentityObjectCredential` DISABLE KEYS */;
INSERT INTO `IdentityObjectCredential` VALUES (3,'admin',4,1),(4,'admin',5,1),(5,'admin',6,1),(6,'test',7,1),(7,'ffff',8,1),(8,'ttt',9,1),(9,'dddd',10,1),(10,'aaaa',11,1),(11,'aaaa',12,1),(12,'user',13,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IdentityObjectRelationship`
--

LOCK TABLES `IdentityObjectRelationship` WRITE;
/*!40000 ALTER TABLE `IdentityObjectRelationship` DISABLE KEYS */;
INSERT INTO `IdentityObjectRelationship` VALUES (1,'admin',1,2,4),(2,'admin',1,2,6),(3,'commercial',1,2,7),(4,'client',1,2,13);
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
INSERT INTO `IdentityObjectRelationshipType` VALUES (1,'JBOSS_IDENTITY_MEMBERSHIP'),(2,'JBOSS_IDENTITY_ROLE');
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC79C742F64589BBA` (`createdBy_id`),
  KEY `FKC79C742FA1A0AD9` (`product_id`),
  KEY `FKC79C742F69C35C79` (`modifiedBy_id`),
  CONSTRAINT `FKC79C742F69C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FKC79C742F64589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FKC79C742FA1A0AD9` FOREIGN KEY (`product_id`) REFERENCES `Product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ItemToPurchase`
--

LOCK TABLES `ItemToPurchase` WRITE;
/*!40000 ALTER TABLE `ItemToPurchase` DISABLE KEYS */;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `contactToConfirm_id` bigint(20) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD8D771F064589BBA` (`createdBy_id`),
  KEY `FKD8D771F0995C1C7B` (`task_id`),
  KEY `FKD8D771F046270F74` (`contactToConfirm_id`),
  KEY `FKD8D771F069C35C79` (`modifiedBy_id`),
  CONSTRAINT `FKD8D771F069C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FKD8D771F046270F74` FOREIGN KEY (`contactToConfirm_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FKD8D771F064589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2D45DD0B64589BBA` (`createdBy_id`),
  KEY `FK2D45DD0B69C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK2D45DD0B69C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK2D45DD0B64589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  `notification_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC1742C9564589BBA` (`createdBy_id`),
  KEY `FKC1742C95675729BB` (`notification_id`),
  KEY `FKC1742C95F51CF139` (`contact_id`),
  KEY `FKC1742C9569C35C79` (`modifiedBy_id`),
  CONSTRAINT `FKC1742C9569C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FKC1742C9564589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FKC1742C95675729BB` FOREIGN KEY (`notification_id`) REFERENCES `Notification` (`id`),
  CONSTRAINT `FKC1742C95F51CF139` FOREIGN KEY (`contact_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `contactId` bigint(20) DEFAULT NULL,
  `accountId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK16648EB364589BBA` (`createdBy_id`),
  KEY `FK16648EB3D44540DA` (`contactId`),
  KEY `FK16648EB3FCBDB4` (`accountId`),
  KEY `FK16648EB369C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK16648EB369C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK16648EB364589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK16648EB3D44540DA` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK16648EB3FCBDB4` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Opportunity`
--

LOCK TABLES `Opportunity` WRITE;
/*!40000 ALTER TABLE `Opportunity` DISABLE KEYS */;
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
  CONSTRAINT `FK9734B73B93D12FAE` FOREIGN KEY (`itemsToPurchase_id`) REFERENCES `ItemToPurchase` (`id`),
  CONSTRAINT `FK9734B73B3ECB38D9` FOREIGN KEY (`Opportunity_id`) REFERENCES `Opportunity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Opportunity_ItemToPurchase`
--

LOCK TABLES `Opportunity_ItemToPurchase` WRITE;
/*!40000 ALTER TABLE `Opportunity_ItemToPurchase` DISABLE KEYS */;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1C4E623764589BBA` (`createdBy_id`),
  KEY `FK1C4E623769C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK1C4E623769C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK1C4E623764589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PhoneNumber`
--

LOCK TABLES `PhoneNumber` WRITE;
/*!40000 ALTER TABLE `PhoneNumber` DISABLE KEYS */;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `tva_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK50C664CF64589BBA` (`createdBy_id`),
  KEY `FK50C664CF2C8A655B` (`category_id`),
  KEY `FK50C664CFAB3B5059` (`tva_id`),
  KEY `FK50C664CF69C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK50C664CF69C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK50C664CF2C8A655B` FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`),
  CONSTRAINT `FK50C664CF64589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK50C664CFAB3B5059` FOREIGN KEY (`tva_id`) REFERENCES `TVA` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK145FF64589BBA` (`createdBy_id`),
  KEY `FK145FF69C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK145FF69C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK145FF64589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TVA`
--

LOCK TABLES `TVA` WRITE;
/*!40000 ALTER TABLE `TVA` DISABLE KEYS */;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `assignedTo_id` bigint(20) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27A9A564589BBA` (`createdBy_id`),
  KEY `FK27A9A5B06F87F0` (`assignedTo_id`),
  KEY `FK27A9A53EFD89AD` (`creator_id`),
  KEY `FK27A9A569C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK27A9A569C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK27A9A53EFD89AD` FOREIGN KEY (`creator_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK27A9A564589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK27A9A5B06F87F0` FOREIGN KEY (`assignedTo_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Task`
--

LOCK TABLES `Task` WRITE;
/*!40000 ALTER TABLE `Task` DISABLE KEYS */;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK954D572C64589BBA` (`createdBy_id`),
  KEY `FK954D572C3EFD89AD` (`creator_id`),
  KEY `FK954D572C69C35C79` (`modifiedBy_id`),
  CONSTRAINT `FK954D572C69C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK954D572C3EFD89AD` FOREIGN KEY (`creator_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK954D572C64589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `createdBy_id` bigint(20) DEFAULT NULL,
  `modifiedBy_id` bigint(20) DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  `ticket_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA55AE38D64589BBA` (`createdBy_id`),
  KEY `FKA55AE38DA42FA6DB` (`ticket_id`),
  KEY `FKA55AE38DF51CF139` (`contact_id`),
  KEY `FKA55AE38D69C35C79` (`modifiedBy_id`),
  CONSTRAINT `FKA55AE38D69C35C79` FOREIGN KEY (`modifiedBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FKA55AE38D64589BBA` FOREIGN KEY (`createdBy_id`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FKA55AE38DA42FA6DB` FOREIGN KEY (`ticket_id`) REFERENCES `Ticket` (`id`),
  CONSTRAINT `FKA55AE38DF51CF139` FOREIGN KEY (`contact_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TicketResponse`
--

LOCK TABLES `TicketResponse` WRITE;
/*!40000 ALTER TABLE `TicketResponse` DISABLE KEYS */;
/*!40000 ALTER TABLE `TicketResponse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-05-31 14:49:01
