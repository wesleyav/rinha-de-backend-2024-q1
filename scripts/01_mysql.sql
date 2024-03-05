-- -----------------------------------------------------
-- Schema db_api
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_api` DEFAULT CHARACTER SET utf8 ;
USE `db_api` ;

-- -----------------------------------------------------
-- Table `db_api`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_api`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `limite` INT NOT NULL,
  `saldo` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_api`.`transacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_api`.`transacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `version` INT NOT NULL DEFAULT 0,
  `valor` INT NOT NULL,
  `tipo` VARCHAR(1) NOT NULL,
  `descricao` VARCHAR(10) NOT NULL,
  `realizada_em` DATE NULL,
  `cliente_id` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO db_api.cliente (id, limite, saldo) VALUES(1, 100000, 0);
INSERT INTO db_api.cliente (id, limite, saldo) VALUES(2, 80000, 0);
INSERT INTO db_api.cliente (id, limite, saldo) VALUES(3, 1000000, 0);
INSERT INTO db_api.cliente (id, limite, saldo) VALUES(4, 10000000, 0);
INSERT INTO db_api.cliente (id, limite, saldo) VALUES(5, 500000, 0);

GRANT ALL PRIVILEGES ON db_api.* TO 'seiya'@'%';
ALTER USER 'seiya'@'%' IDENTIFIED BY 'asdf';
FLUSH PRIVILEGES;