CREATE TABLE `blog_article` (
  `id` int(11) NOT NULL auto_increment,
  `category` varchar(255) default NULL,
  `content` varchar(255) default NULL,
  `create_time` datetime default NULL,
  `reading_number` int(11) default NULL,
  `thumbnail` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `user_id` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKrqlm5t7p1ksv1t66ltlwb6gd1` (`user_id`),
  CONSTRAINT `FKrqlm5t7p1ksv1t66ltlwb6gd1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
