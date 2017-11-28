# Statystyki: procent poprawnych dla konkretnego usera
select users.imie, users.nazwisko, round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki 
join users on users.login = statystyki.login
where statystyki.login = 'maciek';

# Statystyki: procent poprawnych dla edycji
select users.edycja, round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki
join users on users.login = statystyki.login
group by users.edycja;

# Statystyki: procent poprawnych dla wszystkich userów per user
select users.imie, users.nazwisko, users.edycja, round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki
join users on users.login = statystyki.login
group by users.imie, users.nazwisko, users.edycja;

# Statystyki: procent poprawnych dla wszystkich ogółem
select round(sum(procent_poprawnych)/count(id)) from statystyki;