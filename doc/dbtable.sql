CREATE TABLE IF NOT EXISTS `policy` (
    `id`                                INT            AUTO_INCREMENT                        ,
    `name`                              VARCHAR(128)   NOT NULL                              ,
    `content`                           VARCHAR(512)   NOT NULL                              ,
	`topic`                             VARCHAR(128)                                         ,
    `create_time`                       datetime                                             ,
    `comment`                           VARCHAR(512)                                         ,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `admin` (
    `id`                                INT            AUTO_INCREMENT                        ,
    `name`                              VARCHAR(128)   NOT NULL                              ,
    `passwd`                            VARCHAR(512)   NOT NULL                              , 
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `user` (
    `id`                                INT            AUTO_INCREMENT                        ,
    `name`                              VARCHAR(128)   NOT NULL                              ,
	`account`                           VARCHAR(128)   NOT NULL                              ,
    `passwd`                            VARCHAR(512)   NOT NULL                              ,
	`header`                            VARCHAR(128)                                         ,
    `sex`                               bit                                                  ,
    `phone`                             VARCHAR(512)                                         ,
	`isSingle`                          INT                                                  ,
	`school`                            VARCHAR(128)                                         ,
	`country`                           VARCHAR(64)                                          ,
	`sign`                              VARCHAR(128)                                         ,
	`isOnline`                          bit                                                  ,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;
##sex 0 male 1 female

CREATE TABLE IF NOT EXISTS `topic` (
    `id`                                INT            AUTO_INCREMENT                        ,
    `classId`                           INT  NOT NULL                                        ,
    `title`                             VARCHAR(512)   NOT NULL                              ,
	`userId`                            INT                                                  ,
    `description`                       VARCHAR(512)                                         ,
    `dateTime`                          DATETIME                                             ,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `class` (
    `id`                                INT            AUTO_INCREMENT                        ,
	`name`                              VARCHAR(128)   NOT NULL                              ,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `reply` (
    `id`                                INT            AUTO_INCREMENT                        ,
    `userId`                            INT  NOT NULL                                        ,
    `topicId`                           VARCHAR(512)   NOT NULL                              ,
	`dateTime`                          DATETIME                                             ,
    `message`                           VARCHAR(512)                                         ,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

