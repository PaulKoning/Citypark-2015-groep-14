-- MySQL Script generated by MySQL Workbench
-- 11/03/15 09:42:33
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Citypark
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Citypark` ;

-- -----------------------------------------------------
-- Schema Citypark
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Citypark` DEFAULT CHARACTER SET utf8 ;
USE `Citypark` ;

-- -----------------------------------------------------
-- Table `Citypark`.`Gebruiker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Citypark`.`Gebruiker` ;

CREATE TABLE IF NOT EXISTS `Citypark`.`Gebruiker` (
  `Gebruiker_ID` INT NOT NULL AUTO_INCREMENT,
  `Voornaam` VARCHAR(45) NOT NULL,
  `Achternaam` VARCHAR(45) NOT NULL,
  `Rekeningsnummer` INT NOT NULL,
  `Adres` VARCHAR(45) NOT NULL,
  `Woonplaats` VARCHAR(45) NOT NULL,
  `Postcode` VARCHAR(6) NOT NULL,
  `Telefoonnummer` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Wachtwoord` VARCHAR(45) NOT NULL,
  `Gebruikersnaam` VARCHAR(45) NOT NULL,
  `Gebruiker_niveau` INT NOT NULL,
  PRIMARY KEY (`Gebruiker_ID`),
  UNIQUE INDEX `Gebruiker_ID_UNIQUE` (`Gebruiker_ID` ASC),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC),
  UNIQUE INDEX `Gebruikersnaam_UNIQUE` (`Gebruikersnaam` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Citypark`.`Pastype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Citypark`.`Pastype` ;

CREATE TABLE IF NOT EXISTS `Citypark`.`Pastype` (
  `Pastype_ID` INT NOT NULL AUTO_INCREMENT,
  `Beschrijving Type` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`Pastype_ID`),
  UNIQUE INDEX `Pastype_ID_UNIQUE` (`Pastype_ID` ASC),
  UNIQUE INDEX `Beschrijving Type_UNIQUE` (`Beschrijving Type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Citypark`.`Pas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Citypark`.`Pas` ;

CREATE TABLE IF NOT EXISTS `Citypark`.`Pas` (
  `Pas_ID` INT NOT NULL AUTO_INCREMENT,
  `Pastype_Pastype_ID` INT NOT NULL,
  `Actief` TINYINT(1) NOT NULL,
  `Gebruiker_Gebruiker_ID` INT NOT NULL,
  PRIMARY KEY (`Pas_ID`),
  UNIQUE INDEX `Pasnummer_UNIQUE` (`Pas_ID` ASC),
  INDEX `fk_Pas_Pastype1_idx` (`Pastype_Pastype_ID` ASC),
  INDEX `fk_Pas_Gebruiker1_idx` (`Gebruiker_Gebruiker_ID` ASC),
  CONSTRAINT `fk_Pas_Pastype1`
    FOREIGN KEY (`Pastype_Pastype_ID`)
    REFERENCES `Citypark`.`Pastype` (`Pastype_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pas_Gebruiker1`
    FOREIGN KEY (`Gebruiker_Gebruiker_ID`)
    REFERENCES `Citypark`.`Gebruiker` (`Gebruiker_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Citypark`.`Abbonementtype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Citypark`.`Abbonementtype` ;

CREATE TABLE IF NOT EXISTS `Citypark`.`Abbonementtype` (
  `Abbonementtype` INT NOT NULL AUTO_INCREMENT,
  `Bedrag_p_maand` DOUBLE NOT NULL,
  `Beschrijving` VARCHAR(100) NOT NULL,
  `Uren` DOUBLE NOT NULL,
  PRIMARY KEY (`Abbonementtype`),
  UNIQUE INDEX `Abbonementtype_UNIQUE` (`Abbonementtype` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Citypark`.`Abbonementen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Citypark`.`Abbonementen` ;

CREATE TABLE IF NOT EXISTS `Citypark`.`Abbonementen` (
  `Abbonoment_ID` INT NOT NULL AUTO_INCREMENT,
  `Betaald` TINYINT(1) NOT NULL,
  `Contract tot` DATETIME NOT NULL,
  `Begin Contract` DATETIME NOT NULL,
  `Abbonementtype_Abbonementtype` INT NOT NULL,
  `Pas_Pas_ID` INT NOT NULL,
  `Uren_Dezeweek` DOUBLE NOT NULL,
  INDEX `fk_Abbonementen_Abbonementtype1_idx` (`Abbonementtype_Abbonementtype` ASC),
  PRIMARY KEY (`Abbonoment_ID`),
  UNIQUE INDEX `Abbonomenttype_UNIQUE` (`Abbonoment_ID` ASC),
  INDEX `fk_Abbonementen_Pas1_idx` (`Pas_Pas_ID` ASC),
  CONSTRAINT `fk_Abbonementen_Abbonementtype1`
    FOREIGN KEY (`Abbonementtype_Abbonementtype`)
    REFERENCES `Citypark`.`Abbonementtype` (`Abbonementtype`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Abbonementen_Pas1`
    FOREIGN KEY (`Pas_Pas_ID`)
    REFERENCES `Citypark`.`Pas` (`Pas_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Citypark`.`Inrijden`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Citypark`.`Inrijden` ;

CREATE TABLE IF NOT EXISTS `Citypark`.`Inrijden` (
  `inrijd_id` INT NOT NULL AUTO_INCREMENT,
  `Begintijd` DATETIME NULL,
  `Eindtijd` DATETIME NULL,
  `Betaald` TINYINT(1) NULL,
  `Abbonementen_Abbonoment_ID` INT,
  `Pas_Pas_ID` INT,
  PRIMARY KEY (`inrijd_id`),
  UNIQUE INDEX `inrijd_id_UNIQUE` (`inrijd_id` ASC),
  INDEX `fk_Inrijden_Abbonementen1_idx` (`Abbonementen_Abbonoment_ID` ASC),
  INDEX `fk_Inrijden_Pas1_idx` (`Pas_Pas_ID` ASC),
  CONSTRAINT `fk_Inrijden_Abbonementen1`
    FOREIGN KEY (`Abbonementen_Abbonoment_ID`)
    REFERENCES `Citypark`.`Abbonementen` (`Abbonoment_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Inrijden_Pas1`
    FOREIGN KEY (`Pas_Pas_ID`)
    REFERENCES `Citypark`.`Pas` (`Pas_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Citypark`.`Tarief`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Citypark`.`Tarief` ;

CREATE TABLE IF NOT EXISTS `Citypark`.`Tarief` (
  `tarief_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `bedragpuur` DOUBLE NOT NULL,
  `max` DOUBLE,
  PRIMARY KEY (`tarief_id`),
  UNIQUE INDEX `tarief_id_UNIQUE` (`tarief_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Citypark`.`Factuur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Citypark`.`Factuur` ;

CREATE TABLE IF NOT EXISTS `Citypark`.`Factuur` (
  `Factuur_ID` INT NOT NULL AUTO_INCREMENT,
  `Datum` DATETIME NOT NULL,
  `Inrijden_inrijd_id` INT NOT NULL,
  `Bedrag` DOUBLE NULL,
  `Voldaan` TINYINT(1) NULL,
  `Abbonementen_Abbonoment_ID` INT NOT NULL,
  PRIMARY KEY (`Factuur_ID`),
  UNIQUE INDEX `Factuur_ID_UNIQUE` (`Factuur_ID` ASC),
  INDEX `fk_Factuur_Inrijden1_idx` (`Inrijden_inrijd_id` ASC),
  INDEX `fk_Factuur_Abbonementen1_idx` (`Abbonementen_Abbonoment_ID` ASC),
  CONSTRAINT `fk_Factuur_Inrijden1`
    FOREIGN KEY (`Inrijden_inrijd_id`)
    REFERENCES `Citypark`.`Inrijden` (`inrijd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Factuur_Abbonementen1`
    FOREIGN KEY (`Abbonementen_Abbonoment_ID`)
    REFERENCES `Citypark`.`Abbonementen` (`Abbonoment_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;