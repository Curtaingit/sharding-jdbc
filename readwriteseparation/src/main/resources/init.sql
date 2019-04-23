CREATE DATABASE shardingrw0;
USE shardingrw0;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
    id bigint(64) not null,
    city varchar(20) not null,
    name varchar(20) not null,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE DATABASE shardingrw1;
USE shardingrw1;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
    id bigint(64) not null,
    city varchar(20) not null,
    name varchar(20) not null,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `shardingrw1`.`user`(`id`, `city`, `name`) VALUES (101, 'beijing', 'dalaoyang1');

CREATE DATABASE shardingrw2;
USE shardingrw2;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
    id bigint(64) not null,
    city varchar(20) not null,
    name varchar(20) not null,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `shardingrw2`.`user`(`id`, `city`, `name`) VALUES (102, 'beijing', 'dalaoyang2');