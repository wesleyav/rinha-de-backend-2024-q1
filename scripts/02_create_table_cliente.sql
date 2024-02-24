CREATE TABLE IF NOT EXISTS `db_api`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `limite` INT NULL,
  `saldo` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;