create database trzy;
use trzy;
drop table Klienci;
#drop table Klienci;
drop table Opiekunowie;

create table Klienci (
    id_k INT PRIMARY KEY AUTO_INCREMENT,
    imie VARCHAR(25) NOT NULL,
    nazwisko VARCHAR(35) NOT NULL,
	miasto VARCHAR(35) NOT NULL,
    NIP varchar(35) NOT NULL,
    id_o int NOT NULL
);


create table Opiekunowie (
    id_o INT PRIMARY KEY AUTO_INCREMENT,
    imie VARCHAR(25) NOT NULL,
    nazwisko VARCHAR(35) NOT NULL,
    id_k int NOT NULL
);


create table Produkty(
    id_p INT PRIMARY KEY AUTO_INCREMENT,
    nazwa VARCHAR(25) NOT NULL,
	cena int NOT NULL,
    id_o int NOT NULL
);