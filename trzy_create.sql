create database trzy;
use trzy;

create table Klienci (
    id_k INT PRIMARY KEY AUTO_INCREMENT,
    imie VARCHAR(25) NOT NULL,
    nazwisko VARCHAR(35) NOT NULL,
	miasto VARCHAR(35) NOT NULL,
    NIP varchar(35) NOT NULL,
    id_o int NOT NULL
);


create table Produkty(
);