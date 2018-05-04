DROP DATABASE IF EXISTS licenta;
CREATE DATABASE licenta;

USE licenta;

DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user(
    id_user INT UNIQUE AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    favourite INT NOT NULL,
    rating FLOAT NOT NULL,
    role VARCHAR(50) NOT NULL,
    documente VARCHAR(100),
    PRIMARY KEY(id_user)
);

DROP TABLE IF EXISTS colaborare;
CREATE TABLE IF NOT EXISTS colaborare(
    id_colaborare INT UNIQUE AUTO_INCREMENT,
    id_user1 INT,
    id_user2 INT,
    PRIMARY KEY(id_colaborare),
    FOREIGN KEY (id_user1) REFERENCES user(id_user),
    FOREIGN KEY (id_user2) REFERENCES user(id_user)
);

DROP TABLE IF EXISTS categorie;
CREATE TABLE IF NOT EXISTS categorie(
    id_categorie INT UNIQUE AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY(id_categorie)
);

DROP TABLE IF EXISTS produs;
CREATE TABLE IF NOT EXISTS produs(
    id_produs INT UNIQUE AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    quantity DOUBLE NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    id_user INT,
    id_categorie INT,
    PRIMARY KEY(id_produs),
    FOREIGN KEY (id_user) REFERENCES user(id_user),
    FOREIGN KEY (id_categorie) REFERENCES categorie(id_categorie)
);

DROP TABLE IF EXISTS comentariu;
CREATE TABLE IF NOT EXISTS comentariu(
    id_comentariu INT UNIQUE AUTO_INCREMENT,
    descriere VARCHAR(255) NOT NULL,
    id_user1 INT,
    id_user2 INT,
    PRIMARY KEY(id_comentariu),
    FOREIGN KEY (id_user1) REFERENCES user(id_user),
    FOREIGN KEY (id_user2) REFERENCES user(id_user)
);

INSERT INTO user(id_user,name,email,password,phone,address,favourite,rating,role,documente)
    VALUE
    (1, "Feier Iulia","liafeier23@yahoo.com", "lia","0749605176","str.Aurel Vlaicu, nr.3",0,4.54,"FERMIER",""),
    (2, "Aldis","marcuaurel72@yahoo.com", "aldis","0749525171","str.Georce Cosbuc, nr.4",0,2.87,"FIRMA","");

INSERT INTO categorie(id_categorie,name)
    VALUE
    (1, "Lactate"),
    (2, "Zarzavaturi");