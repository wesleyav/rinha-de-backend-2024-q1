CREATE TABLE IF NOT EXISTS `db_api`.`transacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `valor` INT NULL,
  `tipo` VARCHAR(1) NULL,
  `descricao` VARCHAR(10) NULL,
  `realizada_em` DATE NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transacao_cliente_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_transacao_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `db_api`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;