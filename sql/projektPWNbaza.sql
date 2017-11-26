create database projektPWN DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use projektPWN;
#drop database projektPWN;
#drop table users;
#drop table baza_pytan;
#drop table ankiety;
CREATE TABLE users (
    id_u INT AUTO_INCREMENT,
    imie VARCHAR(25) NOT NULL,
    nazwisko VARCHAR(25) NOT NULL,
    email VARCHAR(25) NOT NULL,
    haslo VARCHAR(25) NOT NULL,
    uprawnienia VARCHAR(10) DEFAULT 'user',
    PRIMARY KEY (id_u)
);
#drop table users;


CREATE TABLE baza_pytan (
    id_p INT AUTO_INCREMENT,
    pytanie text,
    odp_poprawna text,
    odp_n1 text,
    odp_n2 text,
    odp_n3 text,
    zakres text ,
    PRIMARY KEY (id_p)
);
#drop table pacjenci;


CREATE TABLE ankiety(
    id_a INT AUTO_INCREMENT,
    id_u int,
    id_p int,
    ilosc_bd int,
    ilosc_git int,
    ilosc_python int,
    ilosc_fe int,
    ilosc_spring int,
    PRIMARY KEY (id_a),
    foreign key (id_u) references users (id_u),
    foreign key (id_p) references baza_pytan (id_p)
);



insert into users (imie, nazwisko , email,haslo,uprawnienia) values ('Mariusz', 'Kowalski','MK@gmail.com','1234','user');
insert into users (imie, nazwisko , email,haslo,uprawnienia) values ('Marek', 'Tomasik','MT@gmail.com','1234','admin');


/*
Create view przyszle_wizyty as select id_w, date_format(date,'%Y-%m-%d') as date, time_format(time,'%H:%m') as hour, concat_ws(' ',lekarze.last,lekarze.name) as lekarz, concat_ws(' ',pacjenci.last,pacjenci.name) as pacjent 
	from 
    wizyty
    left join lekarze on lekarze.id_l = wizyty.id_l
    left join pacjenci on pacjenci.id_p = wizyty.id_p
	where wizyty.date >= curdate();
    
*/

