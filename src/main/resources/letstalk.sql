CREATE DATABASE `letstalk` /*!40100 DEFAULT CHARACTER SET utf8 */;

#
# Source for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL DEFAULT '',
  `user_psw` varchar(20) NOT NULL,
  `user_regtime` datetime NOT NULL,
  `user_is_ol` int(11) NOT NULL,
  `user_role` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

#
# Source for table "message"
#

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  `content` varchar(5000) NOT NULL,
  `type` int(11) NOT NULL,
  `time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `is_received` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `from_id` (`from_id`),
  KEY `to_id` (`to_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

#
# Source for table "group_detail"
#

DROP TABLE IF EXISTS `group_detail`;
CREATE TABLE `group_detail` (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(20) NOT NULL,
  `creator_id` int(11) NOT NULL,
  `created_time` datetime NOT NULL,
  `group_intro` varchar(50) DEFAULT NULL,
  `members_count` int(11) NOT NULL,
  `group_members` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  KEY `creator_id` (`creator_id`),
  CONSTRAINT `group_detail_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Source for table "user_detail"
#

DROP TABLE IF EXISTS `user_detail`;
CREATE TABLE `user_detail` (
  `user_id` int(11) NOT NULL,
  `user_email` varchar(30) DEFAULT NULL,
  `user_addr` varchar(40) DEFAULT NULL,
  `user_gender` int(11) DEFAULT NULL,
  `user_intro` varchar(40) DEFAULT NULL,
  `user_avatar` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_detail_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_detail"
#

DROP TABLE IF EXISTS `user_group_relation`;
CREATE TABLE `user_group_relation` (
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `user_gname` varchar(20) DEFAULT NULL,
  `user_entime` datetime NOT NULL,
  PRIMARY KEY (`user_id`,`group_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `user_group_relation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_group_relation_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `group_detail` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Source for table "user_relation"
#

DROP TABLE IF EXISTS `user_relation`;
CREATE TABLE `user_relation` (
  `user_id_a` int(11) NOT NULL,
  `user_id_b` int(11) NOT NULL,
  `relation_status` int(11) NOT NULL,
  PRIMARY KEY (`user_id_a`,`user_id_b`),
  KEY `user_id_b` (`user_id_b`),
  CONSTRAINT `user_relation_ibfk_1` FOREIGN KEY (`user_id_a`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_relation_ibfk_2` FOREIGN KEY (`user_id_b`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
