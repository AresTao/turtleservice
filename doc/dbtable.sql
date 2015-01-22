CREATE TABLE IF NOT EXISTS `policy` (
    `id`                                INT            AUTO_INCREMENT                        ,
    `name`                              VARCHAR(128)   NOT NULL                              ,
    `content`                           VARCHAR(512)   NOT NULL                              ,
	`topic`                             VARCHAR(128)                                         ,
    `create_time`                       datetime                                             ,
    `comment`                           VARCHAR(512)                                         ,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;