create database projektPWN DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use projektPWN;
#drop database projektPWN;
#drop table IF EXISTS Logowanie;
#drop table IF EXISTS Egzaminator;
#drop table IF EXISTS Kursant;
#drop table IF EXISTS Pytania;
#drop table IF EXISTS Statystyki;
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
    
#drop table pytania_wylosowane;

CREATE TABLE pytania_wylosowane(
id int
);
 select * from pytania_wylosowane;
 truncate pytania_wylosowane;

insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (1,'python','Które z poniższych zmienną środowiskową dla Pythona to alternatywna ścieżka wyszukiwania moduł?','PYTHONPATH','PYTHONSTARTUP','PYTHONCASEOK','PYTHONHOME',4);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (2,'python','Jakie jest wyjście z listy drukowania [0] jeśli lista = [ abcd, 786, 2.23, Jan, 70,2]?','[ ABCD, 786, 2,23, John ", 70,2]','ABCD','Błąd','Żaden z powyższych.',2);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (3,'python','Które z następujących funkcji przekonwertować ciąg do obiektu w Pythonie?','repr(x)','eval(str)','tuple(s)','list(s)',2);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (4,'python','Które z poniższych kontroli funkcji w ciąg, że wszystkie znaki są titlecased?','islower()','isnumeric()','isspace()','istitle()',4);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (5,'python','Co jest następująca funkcja daje łączną długość listy?','cmp(list)','len(list)','max(list)','min(list)',2);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (6,'java','Jaki jest rozmiar podwójnego zmiennej?','8 bit','16 bit','32 bit','64 bit',4);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (7,'java','Co to jest serializacji?','Serializacji jest proces zapisu stanu obiektu z innym obiektem.','Serializacji jest proces zapisu stanu obiektu w strumieniu bajtów.','Obie powyższe.','Żadne z powyższych.',2);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (8,'bd','Pytanie bd1','tak','nie','może','kiedyś potrwafiły latać',3);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (9,'bd','Pytanie bd2','tak','nie','może','kiedyś potrwafiły latać',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (10,'bd','Pytanie bd3','tak','nie','może','kiedyś potrwafiły latać',3);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (11,'git','Pytanie git1','tak','nie','może','kiedyś potrwafiły latać',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (12,'git','Pytanie git2','tak','nie','może','kiedyś potrwafiły latać',3);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (13,'git','Pytanie git3','tak','nie','może','kiedyś potrwafiły latać',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (14,'fe','Pytanie fe1','tak','nie','może','kiedyś potrwafiły latać',3);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (15,'fe','Pytanie fe2','tak','nie','może','kiedyś potrwafiły latać',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (16,'spring','Pytanie spring1','tak','nie','może','kiedyś potrwafiły latać',3);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (17,'spring','Pytanie spring2','tak','nie','może','kiedyś potrwafiły latać',3);


select pytanie from Pytania;


truncate pytania_wylosowane;

# Logowanie
insert into logowanie (login, haslo, rola) values('egzaminator', 'm', 'egzaminator');

insert into logowanie (login, haslo, rola) values('maciek', 'm', 'kursant');
insert into logowanie (login, haslo, rola) values('marek', 'm', 'kursant');
insert into logowanie (login, haslo, rola) values('krzysiek', 'm', 'kursant');
insert into logowanie (login, haslo, rola) values('darek', 'm', 'kursant');

# egzaminators
insert into egzaminator(imie, nazwisko, login) values('egzaminator', 'egzaminatorowski', 'egzaminator');


# kursants
insert into kursant (imie, nazwisko, edycja, login) values('Maciej', 'Wójcik', 'edyjca_1', 'maciek');
insert into kursant (imie, nazwisko, edycja, login) values('Marek', 'Tomasik', 'edyjca_2', 'marek');
insert into kursant (imie, nazwisko, edycja, login) values('Krzysztof', 'Kozak', 'edyjca_1', 'krzysiek');
insert into kursant (imie, nazwisko, edycja, login) values('Dariusz', 'Cienias', 'edyjca_2', 'darek');