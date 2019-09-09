-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.18-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema fimsdb
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ fimsdb;
USE fimsdb;

--
-- Table structure for table `fimsdb`.`company_business`
--

DROP TABLE IF EXISTS `company_business`;
CREATE TABLE `company_business` (
  `businessDate` varchar(45) NOT NULL default '0000-00-00',
  `businessAmount` double NOT NULL default '0',
  `businessProfit` double NOT NULL default '0',
  `businessMonth` varchar(45) NOT NULL default '',
  `businessYear` varchar(45) NOT NULL default '',
  `discountAmount` double NOT NULL default '0',
  `enteredBy` varchar(45) NOT NULL default '',
  `enteredDate` datetime NOT NULL default '0000-00-00 00:00:00',
  `cashierAmount` double NOT NULL default '0',
  `companyBusinessId` int(10) unsigned NOT NULL auto_increment,
  PRIMARY KEY  (`companyBusinessId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 4096 kB';

--
-- Dumping data for table `fimsdb`.`company_business`
--

/*!40000 ALTER TABLE `company_business` DISABLE KEYS */;
INSERT INTO `company_business` (`businessDate`,`businessAmount`,`businessProfit`,`businessMonth`,`businessYear`,`discountAmount`,`enteredBy`,`enteredDate`,`cashierAmount`,`companyBusinessId`) VALUES 
 ('2010-04-27',13310,9735,'April','2010',40,'susanthap','2010-04-27 19:39:23',14720,74),
 ('2011-04-29',690,100,'April','2011',0,'a','2011-04-29 14:03:49',15410,75),
 ('2011-05-01',6020,1375,'May','2011',0,'a','2011-05-01 23:49:42',21430,76),
 ('2011-05-02',12190,5790,'May','2011',0,'a','2011-05-02 19:57:50',33620,77),
 ('2011-05-06',17450.8,11050.8,'May','2011',739.2,'a','2011-05-06 22:38:47',51070.8,78),
 ('2011-05-08',79940,64220,'May','2011',940,'a','2011-05-08 19:49:56',131010.8,79),
 ('2011-05-09',3000,1820,'May','2011',380,'a','2011-05-09 14:13:52',134010.8,80),
 ('2011-05-15',1815,1815,'May','2011',35,'a','2011-05-15 08:38:01',0,81),
 ('2011-05-19',29025.5,23245.5,'May','2011',297.5,'a','2011-05-19 12:59:57',29025.5,82),
 ('2011-11-16',3500,3500,'November','2011',0,'a','2011-11-16 02:14:16',32525.5,84);
/*!40000 ALTER TABLE `company_business` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`customer_invoice`
--

DROP TABLE IF EXISTS `customer_invoice`;
CREATE TABLE `customer_invoice` (
  `invoiceId` int(10) unsigned NOT NULL default '0',
  `totalAmount` double NOT NULL default '0',
  `discountAmount` double NOT NULL default '0',
  `discountPercent` double NOT NULL default '0',
  `invoicedDate` varchar(45) NOT NULL default '0000-00-00',
  `enteredBy` varchar(45) NOT NULL default '',
  `enteredDate` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`invoiceId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fimsdb`.`customer_invoice`
--

/*!40000 ALTER TABLE `customer_invoice` DISABLE KEYS */;
INSERT INTO `customer_invoice` (`invoiceId`,`totalAmount`,`discountAmount`,`discountPercent`,`invoicedDate`,`enteredBy`,`enteredDate`) VALUES 
 (1,2907,323,10,'2010-01-23','a','2010-01-23 16:33:42'),
 (2,690,0,0,'2010-04-03','a','2010-04-03 20:45:57'),
 (3,690,0,0,'2011-04-29','a','2011-04-29 14:03:49'),
 (4,690,0,0,'2011-05-01','a','2011-05-01 23:21:27'),
 (5,790,0,0,'2011-05-01','a','2011-05-01 23:29:19'),
 (6,790,0,0,'2011-05-01','a','2011-05-01 23:33:35'),
 (7,690,0,0,'2011-05-01','a','2011-05-01 23:34:41'),
 (8,690,0,0,'2011-05-01','a','2011-05-01 23:35:32'),
 (9,790,0,0,'2011-05-01','a','2011-05-01 23:44:14'),
 (10,790,0,0,'2011-05-01','a','2011-05-01 23:48:16'),
 (11,790,0,0,'2011-05-01','a','2011-05-01 23:49:42'),
 (12,790,0,0,'2011-05-02','a','2011-05-02 00:12:42'),
 (13,790,0,0,'2011-05-02','a','2011-05-02 08:34:34'),
 (14,790,0,0,'2011-05-02','a','2011-05-02 08:37:46'),
 (15,690,0,0,'2011-05-02','a','2011-05-02 08:42:46'),
 (16,790,0,0,'2011-05-02','a','2011-05-02 08:44:22'),
 (17,4860,0,0,'2011-05-02','a','2011-05-02 08:52:19');
INSERT INTO `customer_invoice` (`invoiceId`,`totalAmount`,`discountAmount`,`discountPercent`,`invoicedDate`,`enteredBy`,`enteredDate`) VALUES 
 (18,3480,0,0,'2011-05-02','a','2011-05-02 19:57:50'),
 (19,4270,0,0,'2011-05-06','a','2011-05-06 19:09:32'),
 (20,3380,100,0,'2011-05-06','a','2011-05-06 19:12:54'),
 (21,3340.8,139.2,4,'2011-05-06','a','2011-05-06 19:14:59'),
 (22,2980,500,0,'2011-05-06','a','2011-05-06 22:34:48'),
 (23,3480,0,0,'2011-05-06','a','2011-05-06 22:38:47'),
 (24,3500,0,0,'2011-05-08','a','2011-05-08 08:57:57'),
 (25,1380,0,0,'2011-05-08','a','2011-05-08 08:59:43'),
 (26,790,0,0,'2011-05-08','a','2011-05-08 09:00:14'),
 (27,4260,10,0,'2011-05-08','a','2011-05-08 15:01:42'),
 (28,1780,200,0,'2011-05-08','a','2011-05-08 15:48:38'),
 (29,2790,0,0,'2011-05-08','a','2011-05-08 15:53:31'),
 (30,3480,0,0,'2011-05-08','a','2011-05-08 16:00:07'),
 (31,3480,0,0,'2011-05-08','a','2011-05-08 16:01:30'),
 (32,9000,730,0,'2011-05-08','a','2011-05-08 16:07:25'),
 (33,7230,0,0,'2011-05-08','a','2011-05-08 16:12:13'),
 (34,9230,0,0,'2011-05-08','a','2011-05-08 16:16:03');
INSERT INTO `customer_invoice` (`invoiceId`,`totalAmount`,`discountAmount`,`discountPercent`,`invoicedDate`,`enteredBy`,`enteredDate`) VALUES 
 (35,10170,0,0,'2011-05-08','a','2011-05-08 16:24:20'),
 (36,12330,0,0,'2011-05-08','a','2011-05-08 16:36:21'),
 (37,790,0,0,'2011-05-08','a','2011-05-08 16:39:26'),
 (38,4440,0,0,'2011-05-08','a','2011-05-08 18:29:25'),
 (39,3040,0,0,'2011-05-08','a','2011-05-08 18:37:12'),
 (40,2250,0,0,'2011-05-08','a','2011-05-08 19:49:56'),
 (41,3000,380,0,'2011-05-09','a','2011-05-09 14:13:52'),
 (42,1815,35,0,'2011-05-15','a','2011-05-15 08:38:01'),
 (43,15393,158,0,'2011-05-19','a','2011-05-19 00:21:52'),
 (44,8290,0,0,'2011-05-19','a','2011-05-19 09:08:49'),
 (45,1161,50,0,'2011-05-19','a','2011-05-19 09:52:39'),
 (46,1161,50,0,'2011-05-19','a','2011-05-19 11:29:38'),
 (47,790,0,0,'2011-05-19','a','2011-05-19 12:53:30'),
 (48,1440.5,39.5,0,'2011-05-19','a','2011-05-19 12:56:26'),
 (49,790,0,0,'2011-05-19','a','2011-05-19 12:59:57'),
 (50,3500,0,0,'2011-11-16','a','2011-11-16 02:14:16');
/*!40000 ALTER TABLE `customer_invoice` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`expense`
--

DROP TABLE IF EXISTS `expense`;
CREATE TABLE `expense` (
  `expenseId` int(10) unsigned NOT NULL auto_increment,
  `expenseDate` datetime NOT NULL default '0000-00-00 00:00:00',
  `expenseDescription` varchar(200) NOT NULL default '',
  `expenseAmount` double NOT NULL default '0',
  `expenseMonth` varchar(45) NOT NULL default '',
  `expenseYear` varchar(45) NOT NULL default '',
  `enteredBy` varchar(45) NOT NULL default '',
  `enteredDate` datetime NOT NULL default '0000-00-00 00:00:00',
  `expenseType` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`expenseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fimsdb`.`expense`
--

/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` (`expenseId`,`expenseDate`,`expenseDescription`,`expenseAmount`,`expenseMonth`,`expenseYear`,`enteredBy`,`enteredDate`,`expenseType`) VALUES 
 (1,'2009-10-22 11:27:42','Azam',1000,'Octomber','2009','susantha','2009-10-22 14:35:22','Administrator Withdraw');
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`invoice_items`
--

DROP TABLE IF EXISTS `invoice_items`;
CREATE TABLE `invoice_items` (
  `invoiceId` int(10) unsigned NOT NULL default '0',
  `itemCode` int(10) unsigned NOT NULL default '0',
  `quantity` int(10) unsigned NOT NULL default '0',
  `salesPrice` double NOT NULL default '0',
  `realPrice` double NOT NULL default '0',
  `itemName` varchar(45) NOT NULL default '',
  `salesExpression` varchar(150) NOT NULL default '',
  `enteredDate` varchar(45) NOT NULL default '',
  `itemDiscount` double default NULL,
  PRIMARY KEY  (`invoiceId`,`itemCode`),
  KEY `FK_invoice_items_2` (`itemCode`),
  CONSTRAINT `FK_invoice_items_1` FOREIGN KEY (`invoiceId`) REFERENCES `customer_invoice` (`invoiceId`),
  CONSTRAINT `FK_invoice_items_2` FOREIGN KEY (`itemCode`) REFERENCES `items` (`itemCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 11264 kB; (`invoiceId`) REFER `fimsdb/customer_';

--
-- Dumping data for table `fimsdb`.`invoice_items`
--

/*!40000 ALTER TABLE `invoice_items` DISABLE KEYS */;
INSERT INTO `invoice_items` (`invoiceId`,`itemCode`,`quantity`,`salesPrice`,`realPrice`,`itemName`,`salesExpression`,`enteredDate`,`itemDiscount`) VALUES 
 (1,10,1,2000,1000,'Mix','1*Mix','2010-03-28',0),
 (2,1,1,690,690,'mix T','1 * 690.0','2010-04-03',0),
 (3,1,1,690,690,'mix T','1 * 690.0','2011-04-29',0),
 (4,1,1,690,690,'mix T','1 * 690.0','2011-05-01',0),
 (5,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-01',0),
 (6,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-01',0),
 (7,1,1,690,690,'mix T','1 * 690.0','2011-05-01',0),
 (8,1,1,690,690,'mix T','1 * 690.0','2011-05-01',0),
 (9,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-01',0),
 (10,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-01',0),
 (11,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-01',0),
 (12,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-02',0),
 (13,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-02',0),
 (14,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-02',0),
 (15,1,1,690,690,'mix T','1 * 690.0','2011-05-02',0);
INSERT INTO `invoice_items` (`invoiceId`,`itemCode`,`quantity`,`salesPrice`,`realPrice`,`itemName`,`salesExpression`,`enteredDate`,`itemDiscount`) VALUES 
 (16,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-02',0),
 (17,1,3,2070,2070,'mix T','3 * 690.0','2011-05-02',0),
 (17,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-02',0),
 (17,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-02',0),
 (17,120,1,500,500,'wwww','1 * 500.0','2011-05-02',0),
 (18,1,1,690,690,'mix T','1 * 690.0','2011-05-02',0),
 (18,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-02',0),
 (18,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-02',0),
 (18,120,1,500,500,'wwww','1 * 500.0','2011-05-02',0),
 (19,1,1,690,690,'mix T','1 * 690.0','2011-05-06',0),
 (19,10,2,1580,1580,'Coller T Intergrale','2 * 790.0','2011-05-06',0),
 (19,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-06',0),
 (19,120,1,500,500,'wwww','1 * 500.0','2011-05-06',0),
 (20,1,1,690,690,'mix T','1 * 690.0','2011-05-06',0),
 (20,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-06',0),
 (20,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-06',0);
INSERT INTO `invoice_items` (`invoiceId`,`itemCode`,`quantity`,`salesPrice`,`realPrice`,`itemName`,`salesExpression`,`enteredDate`,`itemDiscount`) VALUES 
 (20,120,1,500,500,'wwww','1 * 500.0','2011-05-06',0),
 (21,1,1,690,662.4,'mix T','1 * 662.4','2011-05-06',0),
 (21,10,1,790,758.4,'Coller T Intergrale','1 * 758.4','2011-05-06',0),
 (21,100,1,1500,1440,'aaa','1 * 1440.0','2011-05-06',0),
 (21,120,1,500,480,'wwww','1 * 480.0','2011-05-06',0),
 (22,1,1,690,690,'mix T','1 * 690.0','2011-05-06',0),
 (22,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-06',0),
 (22,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-06',0),
 (22,120,1,500,500,'wwww','1 * 500.0','2011-05-06',0),
 (23,1,1,690,690,'mix T','1 * 690.0','2011-05-06',0),
 (23,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-06',0),
 (23,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-06',0),
 (23,120,1,500,500,'wwww','1 * 500.0','2011-05-06',0),
 (24,100,2,3000,3000,'aaa','2 * 1500.0','2011-05-08',0),
 (24,120,1,500,500,'wwww','1 * 500.0','2011-05-08',0),
 (25,1,2,1380,1380,'mix T','2 * 690.0','2011-05-08',0);
INSERT INTO `invoice_items` (`invoiceId`,`itemCode`,`quantity`,`salesPrice`,`realPrice`,`itemName`,`salesExpression`,`enteredDate`,`itemDiscount`) VALUES 
 (26,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-08',0),
 (27,1,1,690,690,'mix T','1 * 690.0','2011-05-08',0),
 (27,10,2,1580,1580,'Coller T Intergrale','2 * 790.0','2011-05-08',0),
 (27,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-08',0),
 (27,120,1,500,500,'wwww','1 * 500.0','2011-05-08',0),
 (28,1,1,690,690,'mix T','1 * 690.0','2011-05-08',0),
 (28,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-08',0),
 (28,120,1,500,500,'wwww','1 * 500.0','2011-05-08',0),
 (29,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-08',0),
 (29,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-08',0),
 (29,120,1,500,500,'wwww','1 * 500.0','2011-05-08',0),
 (30,1,1,690,690,'mix T','1 * 690.0','2011-05-08',0),
 (30,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-08',0),
 (30,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-08',0),
 (30,120,1,500,500,'wwww','1 * 500.0','2011-05-08',0);
INSERT INTO `invoice_items` (`invoiceId`,`itemCode`,`quantity`,`salesPrice`,`realPrice`,`itemName`,`salesExpression`,`enteredDate`,`itemDiscount`) VALUES 
 (31,1,1,690,690,'mix T','1 * 690.0','2011-05-08',0),
 (31,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-08',0),
 (31,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-08',0),
 (31,120,1,500,500,'wwww','1 * 500.0','2011-05-08',0),
 (32,1,2,1380,1380,'mix T','2 * 690.0','2011-05-08',0),
 (32,10,2,1580,1580,'Coller T Intergrale','2 * 790.0','2011-05-08',0),
 (32,100,2,3000,3000,'aaa','2 * 1500.0','2011-05-08',0),
 (32,111,1,1400,1400,'jjj','1 * 1400.0','2011-05-08',0),
 (32,120,3,1500,1500,'wwww','3 * 500.0','2011-05-08',0),
 (32,122,1,2000,2000,'iii','1 * 2000.0','2011-05-08',0),
 (32,124,1,350,350,'jkj','1 * 350.0','2011-05-08',0),
 (32,234,1,2000,2000,'qqq','1 * 2000.0','2011-05-08',0),
 (33,1,1,690,690,'mix T','1 * 690.0','2011-05-08',0),
 (33,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-08',0),
 (33,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-08',0),
 (33,111,1,1400,1400,'jjj','1 * 1400.0','2011-05-08',0);
INSERT INTO `invoice_items` (`invoiceId`,`itemCode`,`quantity`,`salesPrice`,`realPrice`,`itemName`,`salesExpression`,`enteredDate`,`itemDiscount`) VALUES 
 (33,120,1,500,500,'wwww','1 * 500.0','2011-05-08',0),
 (33,122,1,2000,2000,'iii','1 * 2000.0','2011-05-08',0),
 (33,124,1,350,350,'jkj','1 * 350.0','2011-05-08',0),
 (34,1,1,690,690,'mix T','1 * 690.0','2011-05-08',0),
 (34,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-08',0),
 (34,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-08',0),
 (34,111,2,2800,2800,'jjj','2 * 1400.0','2011-05-08',0),
 (34,120,1,500,500,'wwww','1 * 500.0','2011-05-08',0),
 (34,124,2,700,700,'jkj','2 * 350.0','2011-05-08',0),
 (34,234,1,2000,2000,'qqq','1 * 2000.0','2011-05-08',0),
 (34,235,1,250,250,'jkj','1 * 250.0','2011-05-08',0),
 (35,1,2,1380,1380,'mix T','2 * 690.0','2011-05-08',0),
 (35,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-08',0),
 (35,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-08',0),
 (35,111,1,1400,1400,'jjj','1 * 1400.0','2011-05-08',0),
 (35,120,1,500,500,'wwww','1 * 500.0','2011-05-08',0);
INSERT INTO `invoice_items` (`invoiceId`,`itemCode`,`quantity`,`salesPrice`,`realPrice`,`itemName`,`salesExpression`,`enteredDate`,`itemDiscount`) VALUES 
 (35,122,1,2000,2000,'iii','1 * 2000.0','2011-05-08',0),
 (35,124,1,350,350,'jkj','1 * 350.0','2011-05-08',0),
 (35,234,1,2000,2000,'qqq','1 * 2000.0','2011-05-08',0),
 (35,235,1,250,250,'jkj','1 * 250.0','2011-05-08',0),
 (36,1,1,690,690,'mix T','1 * 690.0','2011-05-08',0),
 (36,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-08',0),
 (36,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-08',0),
 (36,120,1,500,500,'wwww','1 * 500.0','2011-05-08',0),
 (36,122,3,6000,6000,'iii','3 * 2000.0','2011-05-08',0),
 (36,124,1,350,350,'jkj','1 * 350.0','2011-05-08',0),
 (36,234,1,2000,2000,'qqq','1 * 2000.0','2011-05-08',0),
 (36,235,2,500,500,'jkj','2 * 250.0','2011-05-08',0),
 (37,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-08',0),
 (38,1,1,690,690,'mix T','1 * 690.0','2011-05-08',0),
 (38,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-08',0),
 (38,122,1,2000,2000,'iii','1 * 2000.0','2011-05-08',0);
INSERT INTO `invoice_items` (`invoiceId`,`itemCode`,`quantity`,`salesPrice`,`realPrice`,`itemName`,`salesExpression`,`enteredDate`,`itemDiscount`) VALUES 
 (38,235,1,250,250,'jkj','1 * 250.0','2011-05-08',0),
 (39,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-08',0),
 (39,122,1,2000,2000,'iii','1 * 2000.0','2011-05-08',0),
 (39,235,1,250,250,'jkj','1 * 250.0','2011-05-08',0),
 (40,111,1,1400,1400,'jjj','1 * 1400.0','2011-05-08',0),
 (40,120,1,500,500,'wwww','1 * 500.0','2011-05-08',0),
 (40,124,1,350,350,'jkj','1 * 350.0','2011-05-08',0),
 (41,1,2,1380,1380,'mix T','2 * 690.0','2011-05-09',0),
 (41,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-09',0),
 (41,120,1,500,500,'wwww','1 * 500.0','2011-05-09',0),
 (42,100,1,1500,1500,'aaa','1 * 1500.0','2011-05-15',0),
 (42,124,1,350,350,'jkj','1 * 350.0','2011-05-15',0),
 (43,1,1,690,690,'mix T','1 * 690.0','2011-05-19',0),
 (43,10,2,1580,1580,'Coller T Intergrale','2 * 790.0','2011-05-19',0),
 (43,100,3,4500,4500,'aaa','3 * 1500.0','2011-05-19',0),
 (43,111,4,5600,5600,'jjj','4 * 1400.0','2011-05-19',0);
INSERT INTO `invoice_items` (`invoiceId`,`itemCode`,`quantity`,`salesPrice`,`realPrice`,`itemName`,`salesExpression`,`enteredDate`,`itemDiscount`) VALUES 
 (43,124,5,1750,1750,'jkj','5 * 350.0','2011-05-19',0),
 (43,235,6,1500,1500,'jkj','6 * 250.0','2011-05-19',0),
 (44,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-19',0),
 (44,120,3,1500,1500,'wwww','3 * 500.0','2011-05-19',0),
 (44,122,3,6000,6000,'iii','3 * 2000.0','2011-05-19',0),
 (45,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-19',0),
 (45,120,1,500,500,'wwww','1 * 500.0','2011-05-19',0),
 (46,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-19',0),
 (46,120,1,500,500,'wwww','1 * 500.0','2011-05-19',0),
 (47,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-19',0),
 (48,1,1,690,690,'mix T','1 * 690.0','2011-05-19',0),
 (48,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-19',0),
 (49,10,1,790,790,'Coller T Intergrale','1 * 790.0','2011-05-19',0),
 (50,100,1,1500,1500,'aaa','1 * 1500.0','2011-11-16',0),
 (50,122,1,2000,2000,'iii','1 * 2000.0','2011-11-16',0);
/*!40000 ALTER TABLE `invoice_items` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`invoice_to`
--

DROP TABLE IF EXISTS `invoice_to`;
CREATE TABLE `invoice_to` (
  `invoicetoid` int(10) unsigned NOT NULL auto_increment,
  `invoiceto` varchar(155) NOT NULL default '',
  PRIMARY KEY  (`invoicetoid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fimsdb`.`invoice_to`
--

/*!40000 ALTER TABLE `invoice_to` DISABLE KEYS */;
INSERT INTO `invoice_to` (`invoicetoid`,`invoiceto`) VALUES 
 (1,'bbbbb'),
 (2,'ddd'),
 (3,'qqq'),
 (4,'qqdd');
/*!40000 ALTER TABLE `invoice_to` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`item_type`
--

DROP TABLE IF EXISTS `item_type`;
CREATE TABLE `item_type` (
  `itemtypename` varchar(155) NOT NULL default '',
  `typedisabled` tinyint(1) NOT NULL default '0',
  `enteredby` varchar(155) NOT NULL default '',
  `entereddate` datetime NOT NULL default '0000-00-00 00:00:00',
  `itemtypeid` int(10) unsigned NOT NULL auto_increment,
  PRIMARY KEY  (`itemtypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fimsdb`.`item_type`
--

/*!40000 ALTER TABLE `item_type` DISABLE KEYS */;
INSERT INTO `item_type` (`itemtypename`,`typedisabled`,`enteredby`,`entereddate`,`itemtypeid`) VALUES 
 ('Gents',0,'a','2011-11-16 02:07:42',1);
/*!40000 ALTER TABLE `item_type` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`items`
--

DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `itemCode` int(10) unsigned NOT NULL default '0',
  `itemName` varchar(100) NOT NULL default '',
  `salesPrice` double NOT NULL default '0',
  `itemType` varchar(45) NOT NULL default '',
  `itemCost` double NOT NULL default '0',
  `enteredBy` varchar(45) NOT NULL default '',
  `enteredDate` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`itemCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fimsdb`.`items`
--

/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` (`itemCode`,`itemName`,`salesPrice`,`itemType`,`itemCost`,`enteredBy`,`enteredDate`) VALUES 
 (1,'mix T',690,'Gents',590,'a','2010-04-03 21:09:34'),
 (10,'Coller T Intergrale',790,'Gents',575,'azam','2009-10-24 16:11:57'),
 (100,'aaa',1500,'Gents',0,'a','2011-05-02 08:51:04'),
 (111,'jjj',1400,'WOOD PRESERVATIVE',0,'a','2011-05-08 16:04:39'),
 (120,'wwww',500,'Gents',0,'a','2011-05-02 08:51:20'),
 (122,'iii',2000,'WOOD PRESERVATIVE',0,'a','2011-05-08 16:05:26'),
 (124,'jkj',350,'WOOD PRESERVATIVE',0,'a','2011-05-08 16:05:16'),
 (234,'qqq',2000,'WOOD PRESERVATIVE',0,'a','2011-05-08 16:04:50'),
 (235,'jkj',250,'WOOD PRESERVATIVE',0,'a','2011-05-08 16:05:07');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `username` varchar(45) NOT NULL default '',
  `password` varchar(45) NOT NULL default '',
  `userType` varchar(45) NOT NULL default '',
  `isDisabled` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fimsdb`.`login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`username`,`password`,`userType`,`isDisabled`) VALUES 
 ('a','b','Administrator',0),
 ('chethana','nangiya','Administrator',0);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`new_orders`
--

DROP TABLE IF EXISTS `new_orders`;
CREATE TABLE `new_orders` (
  `orderId` int(10) unsigned NOT NULL auto_increment,
  `customerName` varchar(45) NOT NULL default '',
  `contactNumber` varchar(45) NOT NULL default '',
  `orderDetails` varchar(45) NOT NULL default '',
  `orderMonth` varchar(45) NOT NULL default '',
  `orderYear` varchar(45) NOT NULL default '',
  `enteredBy` varchar(45) NOT NULL default '',
  `enteredDate` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fimsdb`.`new_orders`
--

/*!40000 ALTER TABLE `new_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `new_orders` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`return_exchange`
--

DROP TABLE IF EXISTS `return_exchange`;
CREATE TABLE `return_exchange` (
  `invoiceId` int(10) unsigned NOT NULL default '0',
  `itemCode` int(10) unsigned NOT NULL default '0',
  `returnItemCode` int(10) unsigned NOT NULL default '0',
  `numberOfItems` int(10) unsigned NOT NULL default '0',
  `enteredBy` varchar(45) NOT NULL default '',
  `enteredDate` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`invoiceId`,`itemCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fimsdb`.`return_exchange`
--

/*!40000 ALTER TABLE `return_exchange` DISABLE KEYS */;
INSERT INTO `return_exchange` (`invoiceId`,`itemCode`,`returnItemCode`,`numberOfItems`,`enteredBy`,`enteredDate`) VALUES 
 (1,1,2,2,'a','2010-04-03 21:06:56'),
 (1,11,22,1,'a','2010-04-03 21:03:51'),
 (1,43,342,1,'a','2010-04-03 21:02:46'),
 (2,1,2,2,'a','2010-04-03 21:09:13'),
 (293,507,434,1,'susanthap','2010-05-10 11:07:14');
/*!40000 ALTER TABLE `return_exchange` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`stores`
--

DROP TABLE IF EXISTS `stores`;
CREATE TABLE `stores` (
  `itemCode` int(10) unsigned NOT NULL default '0',
  `quantity` int(10) unsigned default NULL,
  `reOrder` int(10) unsigned default NULL,
  `enteredBy` varchar(45) NOT NULL default '',
  `enteredDate` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`itemCode`),
  CONSTRAINT `FK_stores_1` FOREIGN KEY (`itemCode`) REFERENCES `items` (`itemCode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 3072 kB; (`itemCode`) REFER `fimsdb/items`(`ite';

--
-- Dumping data for table `fimsdb`.`stores`
--

/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` (`itemCode`,`quantity`,`reOrder`,`enteredBy`,`enteredDate`) VALUES 
 (1,0,20,'Azam','2009-12-04 15:26:13'),
 (10,0,4,'Azam','2009-11-07 20:09:34'),
 (100,0,0,'a','2011-05-02 08:51:04'),
 (111,0,0,'a','2011-05-08 16:04:39'),
 (120,0,0,'a','2011-05-02 08:51:20'),
 (122,0,0,'a','2011-05-08 16:05:26'),
 (124,0,0,'a','2011-05-08 16:05:16'),
 (234,0,0,'a','2011-05-08 16:04:50'),
 (235,0,0,'a','2011-05-08 16:05:07');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`stores_mgt`
--

DROP TABLE IF EXISTS `stores_mgt`;
CREATE TABLE `stores_mgt` (
  `itemCode` varchar(45) NOT NULL default '',
  `itemName` varchar(45) NOT NULL default '',
  `purchaseDate` varchar(45) NOT NULL default '0000-00-00',
  `purchaseQuantity` int(10) unsigned NOT NULL default '0',
  `rejectedQuantity` int(10) unsigned NOT NULL default '0',
  `accptedQuantity` int(10) unsigned NOT NULL default '0',
  `sellQuantity` int(10) unsigned NOT NULL default '0',
  `enteredBy` varchar(45) NOT NULL default '',
  `enteredDate` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`itemCode`,`purchaseDate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fimsdb`.`stores_mgt`
--

/*!40000 ALTER TABLE `stores_mgt` DISABLE KEYS */;
INSERT INTO `stores_mgt` (`itemCode`,`itemName`,`purchaseDate`,`purchaseQuantity`,`rejectedQuantity`,`accptedQuantity`,`sellQuantity`,`enteredBy`,`enteredDate`) VALUES 
 ('0001','mix T','2010-03-28',10,1,9,2,'b','2010-03-28 18:34:07'),
 ('0001','mix T','2010-04-03',20,2,18,2,'a','2010-04-03 20:48:14'),
 ('0007','Coller t O & Y','2010-01-30',34,42,423,23,'a','2010-02-04 22:50:07'),
 ('0010','Coller T Intergrale','2010-01-30',3,3,234,423,'a','2010-02-04 22:49:58');
/*!40000 ALTER TABLE `stores_mgt` ENABLE KEYS */;


--
-- Table structure for table `fimsdb`.`user_activity`
--

DROP TABLE IF EXISTS `user_activity`;
CREATE TABLE `user_activity` (
  `activityId` int(10) unsigned NOT NULL auto_increment,
  `activityDetails` varchar(200) NOT NULL default '',
  `enteredBy` varchar(45) NOT NULL default '',
  `enteredDate` datetime NOT NULL default '0000-00-00 00:00:00',
  `enteredMonth` varchar(45) NOT NULL default '',
  `enteredYear` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`activityId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fimsdb`.`user_activity`
--

/*!40000 ALTER TABLE `user_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_activity` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
