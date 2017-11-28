drop database if exists projektquiz;
create database projektquiz;
use projektquiz;
drop table IF EXISTS Logowanie;
drop table IF EXISTS Egzaminator;
drop table IF EXISTS Kursant;
drop table IF EXISTS Pytania;
drop table IF EXISTS Statystyki;

create table Logowanie (
    id INT AUTO_INCREMENT,
    login VARCHAR(25) NOT NULL,
    haslo VARCHAR(25) NOT NULL,
	rola VARCHAR(25) NOT NULL,
	PRIMARY KEY (id),
    CONSTRAINT uq_loginRola UNIQUE (login, rola)
    );

create table Egzaminator (
    id INT AUTO_INCREMENT,
    imie VARCHAR(25) NOT NULL,
	nazwisko VARCHAR(35) NOT NULL,
    login VARCHAR(25) NOT NULL UNIQUE,
	PRIMARY KEY (id),
    FOREIGN KEY (login) REFERENCES Logowanie (login)
    );

create table Kursant (
    id INT AUTO_INCREMENT,
    imie VARCHAR(25) NOT NULL,
	nazwisko VARCHAR(35) NOT NULL,
    edycja VARCHAR(100) NOT NULL,
    login VARCHAR(25) NOT NULL UNIQUE,
	PRIMARY KEY (id),
    FOREIGN KEY (login) REFERENCES Logowanie (login)
    );

create table Pytania (
    id INT AUTO_INCREMENT,
    zakres VARCHAR(25) NOT NULL,
	pytanie VARCHAR(255) NOT NULL,
    odp_1 VARCHAR(255) NOT NULL,
    odp_2 VARCHAR(255) NOT NULL,
    odp_3 VARCHAR(255) NOT NULL,
    odp_4 VARCHAR(255) NOT NULL,
    odp_poprawna VARCHAR(10) NOT NULL,
	PRIMARY KEY (id)
    );
    
create table Statystyki (
    id INT AUTO_INCREMENT,
    login VARCHAR(25) NOT NULL,
	nr_testu VARCHAR(25) NOT NULL,
    procent_poprawnych INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (login) REFERENCES Kursant (login)
    );
    

