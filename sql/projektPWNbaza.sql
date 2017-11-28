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
    odp_n4 text,
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
drop table pytania_wylosowane;
CREATE TABLE pytania_wylosowane(
id_p int
);



insert into users (imie, nazwisko , email,haslo,uprawnienia) values ('Mariusz', 'Kowalski','MK@gmail.com','1234','user');
insert into users (imie, nazwisko , email,haslo,uprawnienia) values ('a', 'a','a','a','user');
insert into users (imie, nazwisko , email,haslo,uprawnienia) values ('Marek', 'Tomasik','MT@gmail.com','1234','admin');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(1,'Pytanie python1','tak','tak','nie','może','kiedyś potrwafiły latać','python');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(2,'Pytanie python2','tak','tak','nie','może','kiedyś potrwafiły latać','python');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(3,'Pytanie python3','tak','tak','nie','może','kiedyś potrwafiły latać','python');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(4,'Pytanie python4','tak','tak','nie','może','kiedyś potrwafiły latać','python');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(5,'Pytanie python5','tak','tak','nie','może','kiedyś potrwafiły latać','python');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(6,'pytanie java1','tak','tak','nie','może','kiedyś potrwafiły latać','java');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(7,'pytanie java2','tak','tak','nie','może','kiedyś potrwafiły latać','java');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(8,'pytanie java3','tak','tak','nie','może','kiedyś potrwafiły latać','java');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(9,'pytanie java4','tak','tak','nie','może','kiedyś potrwafiły latać','java');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(10,'pytanie java5','tak','tak','nie','może','kiedyś potrwafiły latać','java');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(11,'Pytanie bd','tak','tak','nie','może','kiedyś potrwafiły latać','bd');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(12,'Pytanie spring','tak','tak','nie','może','kiedyś potrwafiły latać','spring');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(13,'pytanie git','tak','tak','nie','może','kiedyś potrwafiły latać','git');
insert into baza_pytan (id_p,pytanie,odp_poprawna,odp_n1,odp_n2,odp_n3,odp_n4,zakres)values(14,'pytanie fe','tak','tak','nie','może','kiedyś potrwafiły latać','fe');

select pytanie from baza_pytan where zakres;
/*
Create view przyszle_wizyty as select id_w, date_format(date,'%Y-%m-%d') as date, time_format(time,'%H:%m') as hour, concat_ws(' ',lekarze.last,lekarze.name) as lekarz, concat_ws(' ',pacjenci.last,pacjenci.name) as pacjent 
	from 
    wizyty
    left join lekarze on lekarze.id_l = wizyty.id_l
    left join pacjenci on pacjenci.id_p = wizyty.id_p
	where wizyty.date >= curdate();
    
*/
select *  from pytania_wylosowane;
select id_p, pytanie from baza_pytan where zakres='python' and id_p not in (select id_p from pytania_wylosowane) order by rand() asc limit 1 ;
select id_p,odp_n1,odp_n2,odp_n3,odp_n4 from pytanie where d_p = id_p ;
select * from baza_pytan ;

truncate pytania_wylosowane;

select id_p ,odp_n1, odp_n2, odp_n3, odp_n4 from baza_pytan where id_p = id_p ;
select id_p, coalesce(pytanie, 'asd') from baza_pytan where zakres='java' and id_p not in (select id_p from pytania_wylosowane) order by rand() asc limit 1;