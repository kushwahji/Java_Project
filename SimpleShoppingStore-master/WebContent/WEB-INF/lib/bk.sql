/*
SQLyog Community v11.31 (64 bit)
MySQL - 5.1.39-community : Database - cmss_task
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cmss_task` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `cmss_task`;

/*Table structure for table `herodetails` */

DROP TABLE IF EXISTS `herodetails`;

CREATE TABLE `herodetails` (
  `heroID` int(10) NOT NULL AUTO_INCREMENT,
  `HeroName` varchar(20) DEFAULT NULL,
  `HeroAttribute` varchar(30) DEFAULT NULL,
  `HeroDescription` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`heroID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `herodetails` */

insert  into `herodetails`(`heroID`,`HeroName`,`HeroAttribute`,`HeroDescription`) values (1,'Mobile','12','Super'),(2,'Jeans','12','Good'),(3,'Shirts','15','M-Size');

/*Table structure for table `itemdetails` */

DROP TABLE IF EXISTS `itemdetails`;

CREATE TABLE `itemdetails` (
  `ItemID` int(10) NOT NULL AUTO_INCREMENT,
  `ItemName` varchar(20) DEFAULT NULL,
  `ItemPrice` double DEFAULT NULL,
  `ItemQuality` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ItemID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `itemdetails` */

insert  into `itemdetails`(`ItemID`,`ItemName`,`ItemPrice`,`ItemQuality`) values (1,'Mobile',14999,'1'),(2,'Cloth',799,'2'),(3,'Jeans',1199,'7'),(4,'T-Shirts',599,'3');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `user_type` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`username`,`password`,`user_type`) values ('abc123','abc123','Admin'),('abc123','abc123','Admin'),('abc','abc','Hr');

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `menu` varchar(30) DEFAULT NULL,
  `user_type` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `menu` */

insert  into `menu`(`menu`,`user_type`) values ('Administrater','Admin'),('Hr Policy','Admin'),('Salary','Admin'),('Accountant','Admin'),('Attendance','Admin'),('View','Admin'),('Mantainance','Admin'),('Leave','Hr');

/*Table structure for table `setdetails` */

DROP TABLE IF EXISTS `setdetails`;

CREATE TABLE `setdetails` (
  `SetID` int(10) NOT NULL AUTO_INCREMENT,
  `SetName` varchar(20) DEFAULT NULL,
  `SetPrice` double DEFAULT NULL,
  `SetQuality` varchar(30) DEFAULT NULL,
  `heroID` int(10) DEFAULT NULL,
  PRIMARY KEY (`SetID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `setdetails` */

/*Table structure for table `useractivation` */

DROP TABLE IF EXISTS `useractivation`;

CREATE TABLE `useractivation` (
  `emailKey` mediumtext,
  `UserId` int(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `useractivation` */

insert  into `useractivation`(`emailKey`,`UserId`) values ('4176555012455026572',31),('4948889078782119021',31),('8048654022113709237',33),('-6614742214744586868',34),('4502458162068212986',35),('-7450371591423898393',36),('5453760792759716104',37),('-2751549273291603235',38),('-5682727424108049667',39),('944174505713749269',40);

/*Table structure for table `userdetails` */

DROP TABLE IF EXISTS `userdetails`;

CREATE TABLE `userdetails` (
  `UserId` int(10) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(30) DEFAULT NULL,
  `Password` varchar(40) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `active` int(11) DEFAULT '0',
  PRIMARY KEY (`UserId`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

/*Data for the table `userdetails` */

insert  into `userdetails`(`UserId`,`UserName`,`Password`,`Email`,`PhoneNumber`,`active`) values (40,'Santosh','Santosh','santoshkumar021990@gmail.com','9752572357',0),(39,'Santosh','Santosh','santoshkumar021990@gmail.com','9752572357',0),(34,'Gouresh','abc123','santoshkumar021990@gmail.com','9752572357',1),(35,'Santosh','Santosh','santoshkumar021990@gmail.com','9752572357',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
