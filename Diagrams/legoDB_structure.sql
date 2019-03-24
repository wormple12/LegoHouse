-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema legoDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `legoDB` ;

-- -----------------------------------------------------
-- Schema legoDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `legoDB` DEFAULT CHARACTER SET utf8 ;
USE `legoDB` ;

-- -----------------------------------------------------
-- Table `legoDB`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `legoDB`.`users` ;

CREATE TABLE IF NOT EXISTS `legoDB`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(11) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `admin` TINYINT(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `legoDB`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `legoDB`.`orders` ;

CREATE TABLE IF NOT EXISTS `legoDB`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `width` INT(11) UNSIGNED NOT NULL,
  `length` INT(11) UNSIGNED NOT NULL,
  `height` INT(11) UNSIGNED NOT NULL,
  `ordered` DATETIME NOT NULL,
  `shipped` DATETIME NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE INDEX `order_id_UNIQUE` (`order_id` ASC) VISIBLE,
  INDEX `orders_users_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `orders_users_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `legoDB`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `legoDB`.`valid_brick_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `legoDB`.`valid_brick_types` ;

CREATE TABLE IF NOT EXISTS `legoDB`.`valid_brick_types` (
  `width` INT(11) UNSIGNED NOT NULL,
  `length` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`width`, `length`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
