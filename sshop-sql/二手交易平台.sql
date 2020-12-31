/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.20 : Database - secondhandtrade
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`secondhandtrade` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `secondhandtrade`;

/*Table structure for table `activity` */

DROP TABLE IF EXISTS `activity`;

CREATE TABLE `activity` (
  `aid` int(10) NOT NULL AUTO_INCREMENT,
  `title` char(100) NOT NULL,
  `descripition` varchar(400) DEFAULT NULL,
  `type` int(11) NOT NULL COMMENT '活动的类型，0表示是优惠的活动，1表示是平台保障的活动',
  `status` int(11) DEFAULT NULL COMMENT '表示当前活动的状态，0表示未开始、1表示正在进行、2表示已经结束，3表示其它',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `activity` */

/*Table structure for table `activity-sellers` */

DROP TABLE IF EXISTS `activity-sellers`;

CREATE TABLE `activity-sellers` (
  `aid` int(10) NOT NULL,
  `userid` int(10) NOT NULL,
  KEY `userid` (`userid`),
  KEY `activity-sellers_ibfk_1` (`aid`),
  CONSTRAINT `activity-sellers_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `activity` (`aid`),
  CONSTRAINT `activity-sellers_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `sellers` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `activity-sellers` */


/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `cid` int(10) NOT NULL AUTO_INCREMENT COMMENT '类别的id',
  `title` char(100) NOT NULL COMMENT '类别名称',
  `link` varchar(400) DEFAULT NULL COMMENT '图片地址',
  `descripition` varchar(400) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '该类别的状态，1表示可用。2表示不可用',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `categories` */


/*Table structure for table `good` */

DROP TABLE IF EXISTS `good`;

CREATE TABLE `good` (
  `goodid` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `title` char(100) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '商品的数量',
  `price` float NOT NULL COMMENT '价格',
  `comment` varchar(400) DEFAULT NULL COMMENT '商品的说明',
  `imglink` varchar(400) DEFAULT NULL COMMENT '商品的照片地址',
  `sid` int(11) DEFAULT NULL,
  `cid` int(10) DEFAULT NULL COMMENT '表示该商品属于哪一种类别',
  `likes` int(100) DEFAULT NULL COMMENT '被收藏量',
  `score` float DEFAULT NULL COMMENT '系统的评分',
  `sellnumber` int(11) DEFAULT NULL COMMENT '销量',
  PRIMARY KEY (`goodid`),
  KEY `good_categories` (`cid`),
  KEY `good_sellers` (`sid`),
  CONSTRAINT `good_categories` FOREIGN KEY (`cid`) REFERENCES `categories` (`cid`),
  CONSTRAINT `good_sellers` FOREIGN KEY (`sid`) REFERENCES `sellers` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=3655 DEFAULT CHARSET=utf8;

/*Data for the table `good` */


/*Table structure for table `good-rights` */

DROP TABLE IF EXISTS `good-rights`;

CREATE TABLE `good-rights` (
  `goodid` int(10) NOT NULL,
  `rid` int(10) NOT NULL,
  KEY `goodid` (`goodid`),
  KEY `good-rights_ibfk_2` (`rid`),
  CONSTRAINT `good-rights_ibfk_1` FOREIGN KEY (`goodid`) REFERENCES `good` (`goodid`),
  CONSTRAINT `good-rights_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `rights` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `good-rights` */


/*Table structure for table `images` */

DROP TABLE IF EXISTS `images`;

CREATE TABLE `images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `referid` int(10) NOT NULL COMMENT '表示该照片表示的是referid使用的',
  `type` int(11) DEFAULT NULL COMMENT '表示商品为0，表示用户为1',
  `link` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `images` */


/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `userbuyid` int(11) NOT NULL COMMENT '购买者的id',
  `usersellid` int(11) NOT NULL COMMENT '卖方的id',
  `goodid` int(11) NOT NULL COMMENT '商品的id',
  `number` int(10) NOT NULL COMMENT '订单中成交的商品的数目',
  `singleprice` float NOT NULL COMMENT '单个商品的价格',
  `totalprice` float NOT NULL COMMENT '总的成交价',
  `status` int(1) DEFAULT '1' COMMENT '订单的状态，0为未付款，1为正在进行，2为未评价。3为已完成',
  `comments` varchar(400) DEFAULT NULL COMMENT '备注',
  `ordertime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderid`),
  KEY `order_buyer` (`userbuyid`),
  KEY `order_seller` (`usersellid`),
  KEY `order_good` (`goodid`),
  CONSTRAINT `order_buyer` FOREIGN KEY (`userbuyid`) REFERENCES `user` (`userid`),
  CONSTRAINT `order_good` FOREIGN KEY (`goodid`) REFERENCES `good` (`goodid`),
  CONSTRAINT `order_seller` FOREIGN KEY (`usersellid`) REFERENCES `sellers` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

/*Data for the table `order` */


/*Table structure for table `phones` */

DROP TABLE IF EXISTS `phones`;

CREATE TABLE `phones` (
  `number` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `phones` */

/*Table structure for table `ratings` */

DROP TABLE IF EXISTS `ratings`;

CREATE TABLE `ratings` (
  `ratingid` int(10) NOT NULL AUTO_INCREMENT COMMENT '评价的id',
  `orderid` int(11) NOT NULL COMMENT '相关的订单的id',
  `ratetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评价的时间',
  `ratescore` float DEFAULT NULL COMMENT '用户打的分数',
  `ratetype` int(11) DEFAULT NULL COMMENT '评价的种类，0表示差评，1表示好评',
  `text` varchar(400) DEFAULT '(系统默认好评)' COMMENT '用户评价的内容',
  PRIMARY KEY (`ratingid`),
  KEY `orderid` (`orderid`),
  CONSTRAINT `ratings_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `order` (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `ratings` */


/*Table structure for table `rights` */

DROP TABLE IF EXISTS `rights`;

CREATE TABLE `rights` (
  `rid` int(10) NOT NULL AUTO_INCREMENT COMMENT '权益的id',
  `title` char(100) NOT NULL COMMENT '权益的名称',
  `descripition` varchar(400) DEFAULT NULL COMMENT '权益的描述',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `rights` */


/*Table structure for table `sellers` */

DROP TABLE IF EXISTS `sellers`;

CREATE TABLE `sellers` (
  `sid` int(10) NOT NULL,
  `school` char(100) DEFAULT NULL COMMENT '学校',
  `ispass` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否通过认证',
  `totalnumber` int(11) DEFAULT NULL COMMENT '卖出商品的总的数量',
  `reliability` float DEFAULT NULL COMMENT '可靠度，由系统打分',
  `surprise` varchar(400) DEFAULT NULL COMMENT '彩蛋',
  PRIMARY KEY (`sid`),
  CONSTRAINT `sellers_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sellers` */


/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `pwd` varchar(40) NOT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `score` float DEFAULT '60',
  `bio` varchar(200) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `status` int(1) DEFAULT '1' COMMENT '表示用户的状态，1为正常用户。2为用户账号已注销，3表示是管理员',
  PRIMARY KEY (`phone`),
  UNIQUE KEY `id` (`userid`),
  KEY `id_2` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

/*Data for the table `user` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
