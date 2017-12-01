## DROP USER IF EXIST
DROP USER if exists'javaUserr'@'localhost';

## CREATE USER
CREATE USER 'javaUserr'@'localhost' IDENTIFIED BY '1qazXSW@';

## DROP DATABASE IF EXIST
drop database if exists projektquiz;

## CREATE DATABASE
create database projektquiz DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci; 
use projektquiz;



## CREATE TABLES
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
    procent_poprawnych INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (login) REFERENCES Kursant (login)
    );
    
CREATE TABLE pytania_wylosowane(
id int);
 

## GRANT ACCESS TO DATABASE FOR USER 
GRANT ALL ON projektquiz.* TO 'javaUser'@'localhost';
 
## INSERT
# Pytania
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (1,'python','Która z poniższych zmiennych środowiskowych dla Pythona to alternatywna ścieżka wyszukiwania modułu?','PYTHONPATH','PYTHONSTARTUP','PYTHONCASEOK','PYTHONHOME',4);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (2,'python','Co oznacza wyrażenie print()?":','Kończy pętlę while":','Wyświetla wpisany w nawiasach tekst','Oblicza równanie','żadne z powyrzszych',2);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (3,'python','Która z funkcji przekonwertuje ciąg do obiektu w Pythonie?','repr(x)','eval(str)','tuple(s)','list(s)',2);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (4,'python','Które z poniższych kontroli funkcji w ciąg, że wszystkie znaki są titlecased?','islower()','isnumeric()','isspace()','istitle()',4);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (5,'python','Która z funkcji zwraca długość listy?','cmp(list)','max(list)','len(list)','min(list)',3);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (6,'java','Jaki jest rozmiar podwójnej zmiennej?','8 bit','16 bit','32 bit','64 bit',4);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (7,'java','Które z poniższych interfejsów funkcjonalnych reprezentuje operację, która akceptuje pojedynczą int wartościami argumentu i nie zwraca wyników?','IntConsumer','IntFunction <R>','IntPredicate','IntSupplier',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (8,'java','Jaka jest wartość domyślna krótkiej zmiennej?','0,0','0','wartość null','niezdefiniowane',2);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (9,'java','Które z poniższych stwierdzeń jest szybsze, StringBuilder albo StringBuffer?','StringBuilder','StringBuffer','Obie powyższe.','żadna z powyższych.',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (10,'java','Co to jest serializacja?','Serializacja jest procesem zapisu stanu obiektu z innym obiektem.','Serializacji jest procesem zapisu stanu obiektu w strumieniu bajtów.','Obie powyższe.','Żadne z powyższych.',2);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (11,'bd','Do czego służy funkcja replace?','wycina z podanego ciągu określoną liczbę znaków zaczynając od znaku określonego parametrem', 'zwraca określoną ilość znaków licząc od lewej / prawej strony dla podanego ciągu', 'wycina z podanego ciągu określoną liczbę znaków.', 'zamienia w podanym ciągu wskazany ciąg znaków na inny określony ciąg znaków.',4);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (12,'bd','Instrukcja SELECT służy do:','wyświetlenia rekordów z bazy danych','wstawiania rekordów do bazy danych','usuwania rekordów z bazy danych','aktualizacji rekordów w bazie danych',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (13,'bd','Instrukcja ROLLBACK służy do:','wycofywania zmian w bazie danych?','zatwierdzania zmian w bazie danych','usuwania rekordów z bazy danych','aktualizacji rekordów w bazie danych',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (14,'bd','Która z poniższych funkcji generuje wartość losową?','rand()','round()','random()','los()',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (15,'bd','Jak inaczej nazywamy klucz podstawowy?',' klucz boczny','klucz główny','klucz sortowania','klucz obcy',2);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (16,'git','Czym jest Git?','Git to rozproszony system kontroli wersji','Git to program graficzny','Git to wyszukiwarka internetowa','Git to gra komputerowa',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (17,'git','Polecenie używane do sprawdzenia stanu plików to:','git status','git commit','git log','git show',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (18,'git','Co to jest gałąź? ','to suma kontrolna rewizji przechowywana w pliku','służy do śledzenia nowych plików','służy do dodwania zmian do poczekalni','to, przesuwalny wskaźnik na któryś z zestawów zmian(commitów).',4);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (19,'git','Do czego służy komenda ls?','wyświetla listę plików i katalogów','sprawdza status plików','pokazuje dane etykiet','usuwa plik z katalogu',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (20,'git','Do czego służy komenda git fetch?','wyświetla listę gałęzi','commit-uje zmiany','ściąga zmiany ze zdalnego repozytorium ale ich nie scala','dodaje zdale repozytorium',3);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (21,'fe','Który element nie należy do HTML5 ?','<section>','<header','<blink>','<main>',3);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (22,'fe','Które z poniższych stwierdzeń jest prawdziwe?','Atrybuty mogą mieć przypisaną tylko jedną wartość','Atrybuty są łatwo rozszerzalne w przypadku zmian w przyszłości','Atrybuty opisują struktury','Atrybuty są znacznie łatwiejsze przy manipulowaniu nimi w programie',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (23,'fe','Gdzie deklarujemy plik .css w dokumencie HTML ?','sekcja <head>','sekcja <body>','nie musimy podpinac pliku .css','żadne z powyższych',3);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (24,'spring','Spring to:','free framework','an open source framework','może','kiedyś potrwafiły latać',2);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (25,'spring','Co oznacza skrót AOP','Aspect Oriented Programming','Any Object Programming','Asset Oriented Programming','Asset Oriented Protocol',1);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (26,'spring','Czym jest aspekt?','Aspekt jest sposobem wykonania iniekcji zależności ','Moduł, który ma zestaw interfejsów API zapewniających przekrojowe wymagania','Aspekt służy do rejestrowania informacji o aplikacji.','Aspekt reprezentuje właściwości aplikacji na bazie sprężyny.',2);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (27,'spring','Jeśli komponent bean może być tworzony dowolną liczbę razy, zakres jest...?','sesją','sesją globalną','prototypem','wnioskiem',3);
insert into Pytania (id, zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values (28,'spring','Które z poniższych aspektów implementujących obsługuje Spring?','Implementacja aspektu opartego na schemacie XML',' Implementacja aspektu opartego na @AspectJ','Obie powyższe','Obie powyższe',3);

# logowanie
insert into logowanie (login, haslo, rola) values('egzaminator', 'm', 'egzaminator');

insert into logowanie (login, haslo, rola) values('maciek', 'm', 'kursant');
insert into logowanie (login, haslo, rola) values('marek', 'm', 'kursant');
insert into logowanie (login, haslo, rola) values('krzysiek', 'm', 'kursant');
insert into logowanie (login, haslo, rola) values('darek', 'm', 'kursant');

# egzaminator
insert into egzaminator(imie, nazwisko, login) values('egzaminator', 'egzaminatorowski', 'egzaminator');

# kursant
insert into kursant (imie, nazwisko, edycja, login) values('Maciej', 'Wójcik', 'edyjca_1', 'maciek');
insert into kursant (imie, nazwisko, edycja, login) values('Marek', 'Tomasik', 'edyjca_2', 'marek');
insert into kursant (imie, nazwisko, edycja, login) values('Krzysztof', 'Kozak', 'edyjca_1', 'krzysiek');
insert into kursant (imie, nazwisko, edycja, login) values('Dariusz', 'Cienias', 'edyjca_2', 'darek');

# Statystyki
insert into statystyki (login, procent_poprawnych) values('maciek', 20);
insert into statystyki (login, procent_poprawnych) values('maciek', 60);
insert into statystyki (login, procent_poprawnych) values('maciek', 40);

insert into statystyki (login, procent_poprawnych) values('marek',  20);
insert into statystyki (login, procent_poprawnych) values('marek', 60);
insert into statystyki (login, procent_poprawnych) values('marek', 40);

insert into statystyki (login, procent_poprawnych) values('krzysiek',  70);
insert into statystyki (login, procent_poprawnych) values('krzysiek', 80);
insert into statystyki (login, procent_poprawnych) values('krzysiek', 90);

insert into statystyki (login, procent_poprawnych) values('darek',  10);
insert into statystyki (login, procent_poprawnych) values('darek', 20);
insert into statystyki (login, procent_poprawnych) values('darek', 30);