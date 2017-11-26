create database projektPWN DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use projektPWN;
#drop database projektPWN;

CREATE TABLE users (
    id_u INT AUTO_INCREMENT,
    imie VARCHAR(25) NOT NULL,
    nazwisko VARCHAR(25) NOT NULL,
    email VARCHAR(25) NOT NULL,
    pesel VARCHAR(11) NOT NULL,
    uprawnienia VARCHAR(10) DEFAULT 'user',
    PRIMARY KEY (id_u)
);
#drop table users;


CREATE TABLE baza_pytan (
    id_p INT AUTO_INCREMENT,
    pytanie VARCHAR(160),
    odp_poprawna VARCHAR(100),
    odp_n1 VARCHAR(100),
    odp_n2 VARCHAR(100),
    odp_n3 VARCHAR(100),
    zakres varchar (35),
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
    foreign key (id_l) references lekarze (id_l),
    foreign key (id_p) references pacjenci (id_p)
);


/*
insert into lekarze (name, last , cellphone) values ('Mariusz', 'Kowalski','667 668 633');



Create view przyszle_wizyty as select id_w, date_format(date,'%Y-%m-%d') as date, time_format(time,'%H:%m') as hour, concat_ws(' ',lekarze.last,lekarze.name) as lekarz, concat_ws(' ',pacjenci.last,pacjenci.name) as pacjent 
	from 
    wizyty
    left join lekarze on lekarze.id_l = wizyty.id_l
    left join pacjenci on pacjenci.id_p = wizyty.id_p
	where wizyty.date >= curdate();
    
*/

