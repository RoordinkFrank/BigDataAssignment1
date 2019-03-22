DROP DATABASE if exists BigDataMysql;
create database BigDataMysql;
 
use BigDataMysql;

CREATE TABLE `producten` (
    `product_key` varchar(45) NOT NULL,
    `omschrijving` varchar(45) NOT NULL,
    `aantal` int(15) NOT NULL,
    PRIMARY KEY (`product_key`)
);
CREATE TABLE `filialen` (
    `filiaal_key` varchar(45) NOT NULL,
    PRIMARY KEY (`filiaal_key`)
);
CREATE TABLE `klanten` (
    `klant_key` varchar(45) NOT NULL,
    PRIMARY KEY (`klant_key`)
);
CREATE TABLE `aankopen` (
    `aankoop_key` varchar(45) NOT NULL,
    `datum` DateTime NOT NULL,
    `aantal` int(15) NOT NULL,
    `product_key` varchar(45) NOT NULL,
    `filiaal_key` varchar(45) NOT NULL,
    `klant_key` varchar(45) NOT NULL,
    PRIMARY KEY (`aankoop_key`),
    FOREIGN KEY (product_key) REFERENCES producten(product_key),
    FOREIGN KEY (filiaal_key) REFERENCES filialen(filiaal_key),
    FOREIGN KEY (klant_key) REFERENCES klanten(klant_key)
);