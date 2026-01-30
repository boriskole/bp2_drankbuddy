DROP DATABASE IF EXISTS `bp2_drankbuddy`;
CREATE DATABASE `bp2_drankbuddy`;
USE `bp2_drankbuddy`;

CREATE TABLE `account` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE `category` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    account_id INT NOT NULL
);

CREATE TABLE `product` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    category_id INT NOT NULL
);

CREATE TABLE `stock_mutation` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    stock_change INT NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    mutation_type ENUM('SALE', 'DELIVERY', 'CORRECTION') NOT NULL,
    product_id INT NOT NULL
);

ALTER TABLE category
ADD CONSTRAINT fk_category_account
FOREIGN KEY (account_id) REFERENCES account(id);

ALTER TABLE product
ADD CONSTRAINT fk_product_category
FOREIGN KEY (category_id) REFERENCES category(id);

ALTER TABLE stock_mutation
ADD CONSTRAINT fk_stock_mutation_product
FOREIGN KEY (product_id) REFERENCES product(id);

CREATE INDEX idx_stock_mutation_date
ON stock_mutation(date);

CREATE INDEX idx_product_name
ON product(name);
