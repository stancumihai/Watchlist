create schema watchlist;
/*movie table*/
CREATE TABLE `watchlist`.`movie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `year` INT NULL,
  `genre` VARCHAR(45) NULL,
  `director` VARCHAR(45) NULL,
  `language` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
  /*user table*/
  CREATE TABLE `watchlist`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
  /*actor table*/
  CREATE TABLE `watchlist`.`actor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `birthdate` DATE NULL,
  PRIMARY KEY (`id`));	
  