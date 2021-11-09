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

/*cinema table*/
CREATE TABLE `watchlist`.`cinema` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(45) NULL,
                                      `capacity` INT NULL,
                                      PRIMARY KEY (`id`));

/* cinema_movies table*/
CREATE TABLE `watchlist`.`cinema_movies` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `movieId` INT NULL,
                                             `cinemaId` INT NULL,
                                             PRIMARY KEY (`id`),
                                             INDEX `movieFk_idx` (`movieId` ASC) ,
                                             INDEX `cinemaFk_idx` (`cinemaId` ASC) ,
                                             CONSTRAINT `movieFk`
                                                 FOREIGN KEY (`movieId`)
                                                     REFERENCES `watchlist`.`movie` (`id`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION,
                                             CONSTRAINT `cinemaFk`
                                                 FOREIGN KEY (`cinemaId`)
                                                     REFERENCES `watchlist`.`cinema` (`id`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION);

/*movie_actors table */
CREATE TABLE `watchlist`.`movie_actors` (
                                            `id` INT NOT NULL AUTO_INCREMENT,
                                            `movieId` INT NULL,
                                            `actorId` INT NULL,
                                            PRIMARY KEY (`id`),
                                            INDEX `movieActFk_idx` (`movieId` ASC) ,
                                            INDEX `actorFk_idx` (`actorId` ASC) ,
                                            CONSTRAINT `movieActFk`
                                                FOREIGN KEY (`movieId`)
                                                    REFERENCES `watchlist`.`movie` (`id`)
                                                    ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION,
                                            CONSTRAINT `actorFk`
                                                FOREIGN KEY (`actorId`)
                                                    REFERENCES `watchlist`.`actor` (`id`)
                                                    ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION);

/*address table */
CREATE TABLE `watchlist`.`address` (
                                       `id` INT NOT NULL AUTO_INCREMENT,
                                       `city` VARCHAR(45) NULL,
                                       `country` VARCHAR(45) NULL,
                                       PRIMARY KEY (`id`));

ALTER TABLE `watchlist`.`address`
    ADD COLUMN `actorId` INT NULL AFTER `country`,
    ADD INDEX `actorAddressFk_idx` (`actorId` ASC) ;
;
ALTER TABLE `watchlist`.`address`
    ADD CONSTRAINT `actorAddressFk`
        FOREIGN KEY (`actorId`)
            REFERENCES `watchlist`.`actor` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
