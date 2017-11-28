# Statystyki: procent poprawnych dla konkretnego usera sumaryczne
select kursant.imie, kursant.nazwisko, kursant.edycja, count(statystyki.id), round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki 
join kursant on kursant.login = statystyki.login
where statystyki.login = 'maciek';

# Statystyki: procent poprawnych dla konkretnego usera per test
select kursant.imie, kursant.nazwisko, statystyki.nr_testu, statystyki.procent_poprawnych
from statystyki 
join kursant on kursant.login = statystyki.login
where statystyki.login = 'maciek';

# Statystyki: procent poprawnych dla edycji
select kursant.edycja, round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki
join kursant on kursant.login = statystyki.login
group by kursant.edycja;

# Statystyki: procent poprawnych dla wszystkich userów per user
select kursant.imie, kursant.nazwisko, kursant.edycja, count(statystyki.id), round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki 
join kursant on kursant.login = statystyki.login
group by kursant.imie, kursant.nazwisko, kursant.edycja;

# Statystyki: procent poprawnych dla wszystkich ogółem
select round(sum(procent_poprawnych)/count(id)) from statystyki;

