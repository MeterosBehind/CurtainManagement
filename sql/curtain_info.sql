/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.17-log : Database - curtain_info
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`curtain_info` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `curtain_info`;

/*Table structure for table `curtain_info` */

DROP TABLE IF EXISTS `curtain_info`;

CREATE TABLE `curtain_info` (
  `id` int(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `texture` varchar(50) DEFAULT NULL,
  `market_time` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `introduction` text,
  `created_time` date DEFAULT NULL,
  `last_updated_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `curtain_info` */

insert  into `curtain_info`(`id`,`name`,`type`,`texture`,`market_time`,`price`,`introduction`,`created_time`,`last_updated_time`) values 
(1,'窗帘1','1','2','2022-11-18',123,'123呃呃呃呃','2022-11-18','2022-11-18'),
(2,'窗帘2','大船','石化','2022-11-17',23.5,'123谔谔委屈好窗帘，惺已照','2022-11-30','2022-11-29');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
