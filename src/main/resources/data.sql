DROP DATABASE IF EXISTS security_authentication;

CREATE DATABASE security_authentication;

use security_authentication;

-- schema.sql
CREATE TABLE IF NOT EXISTS `security_authentication`.`user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` TEXT NOT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `security_authentication`.`authority` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `name` VARCHAR(45) NOT NULL,
    `user` INT NOT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `security_authentication`.`product` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `name` VARCHAR(45) NOT NULL,
    `price` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`));

-- data.sql
-- 비밀번호: 12345
INSERT IGNORE INTO `security_authentication`.`user` (`id`, `username`, `password`) VALUES ('1', 'gugu', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG');
INSERT IGNORE INTO `security_authentication`.`user` (`id`, `username`, `password`) VALUES ('2', 'cucu', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG');

INSERT IGNORE INTO `security_authentication`.`authority` (`id`, `name`, `user`) VALUES ('1', 'READ', '1');
INSERT IGNORE INTO `security_authentication`u.`authority` (`id`, `name`, `user`) VALUES ('2', 'WRITE', '1');

INSERT IGNORE INTO `security_authentication`.`product` (`id`, `name`, `price`) VALUES ('1', 'Black noodle', '10');

SELECT * FROM user;