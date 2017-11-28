use projektquiz;


# Logowanie
insert into logowanie (login, haslo, rola) values('admin', '123', 'admin');

insert into logowanie (login, haslo, rola) values('maciek', 'm', 'user');
insert into logowanie (login, haslo, rola) values('marek', 'm', 'user');
insert into logowanie (login, haslo, rola) values('krzysiek', 'm', 'user');
insert into logowanie (login, haslo, rola) values('darek', 'm', 'user');

# Admins
insert into admins(imie, nazwisko, login) values('Admin', 'Adminowski', 'admin');


# Users
insert into users (imie, nazwisko, edycja, login) values('Maciej', 'Wójcik', 'edyjca_1', 'maciek');
insert into users (imie, nazwisko, edycja, login) values('Marek', 'Tomasik', 'edyjca_2', 'marek');
insert into users (imie, nazwisko, edycja, login) values('Krzysztof', 'Kozak', 'edyjca_1', 'krzysiek');
insert into users (imie, nazwisko, edycja, login) values('Dariusz', 'Cienias', 'edyjca_2', 'darek');

# Pytania
# tO DO

# Statystyki
insert into statystyki (login, procent_poprawnych) values('maciek', 20);
insert into statystyki (login, procent_poprawnych) values('maciek', 60);
insert into statystyki (login, procent_poprawnych) values('maciek', 40);

insert into statystyki (login, procent_poprawnych) values('marek', 20);
insert into statystyki (login, procent_poprawnych) values('marek', 60);
insert into statystyki (login, procent_poprawnych) values('marek', 40);

insert into statystyki (login, procent_poprawnych) values('krzysiek', 70);
insert into statystyki (login, procent_poprawnych) values('krzysiek', 80);
insert into statystyki (login, procent_poprawnych) values('krzysiek', 90);

insert into statystyki (login, procent_poprawnych) values('darek', 10);
insert into statystyki (login, procent_poprawnych) values('darek', 20);
insert into statystyki (login, procent_poprawnych) values('darek', 30);
