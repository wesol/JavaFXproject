#-*-coding: utf-8 -*-
import pymysql
import python.pssw

class Projekt:
    def __init__(self):
        passwd = python.pssw.password
        self.conn = pymysql.connect('localhost','root',passwd,'projekt',charset ='utf8') 
        self.cursor = self.conn.cursor() 
        while(True):
            wybor = input('(L)-Logowanie \n(R)-Rejestracja \n:').upper()
            if(wybor == 'L'):
                self.login()
            elif(wybor == 'R'):
                self.insert()
                
            else: 
                print('Bye')
                break
            
    def login(self):
        e_mail_r = input('Podaj e-mail:')
        haslo_r = input('Podaj hasło:')    
        self.cursor.execute(" select * from logowanie where e_mail_r='%s' and haslo_r='%s'" % (e_mail_r,haslo_r))
        RS = self.cursor.fetchall()
        if(len(RS) == 0):
            print('Błędne hasło lub login')
            return False
        else: 
            print('Witaj!')  
            '''
            id_r =  self.cursor.execute(" select id_r from logowanie where e_mail_r='%s' and haslo_r='%s'" % (e_mail_r,haslo_r))
            id_r = self.cursor.fetchall()
            id_r = int(((id_r[0])[0]))  
            print(id_r)
            '''
            
            
            while(True):
                wybor = input('Co chcesz wybrać: \n(M) - miejsca \n(W) - wydarzenia \n(D) - usuń konto \n:').upper()
                if(wybor == 'M'):
                    self.miejsca()
                elif(wybor == 'W'):
                    self.wydarzenia()
                elif(wybor == 'D'):
                    self.usun()  
                else: 
                    print('Bye')
                    break    
        
    def usun(self):
        while(True):
            try:
                id_r = int(input('Podaj id które usuwamy:'))
                break
            except:
                print('Błędny id!')      
        self.cursor.execute("delete from logowanie where id_r=%i" % (id_r))
        self.cursor.execute("delete from dziecko where id_r=%i" % (id_r))
        self.cursor.execute("delete from rodzic where id_r=%i" % (id_r))
        self.conn.commit()  
        
    def insert(self):
        print('######UZUPEŁNIJ DANE UŻYTKOWNIKA######')
        imie_r = input('Podaj imie:')
        wiek_r = input('Podaj wiek:')
        adres_r = input('Podaj ulicę:')
        kod_pocztowy_r = input('Podaj kod pocztowy:')
        self.cursor.execute("insert into rodzic (imie_r, wiek_r, adres_r, kod_pocztowy_r) values ('%s','%s','%s','%s');" % (imie_r, wiek_r,adres_r,kod_pocztowy_r))
        self.conn.commit()
        print('######UZUPEŁNIJ DANE LOGOWANIA######')
        id_r = self.cursor.execute(("select id_r from rodzic where (imie_r='%s' and wiek_r='%s' and adres_r='%s' and kod_pocztowy_r='%s');") % (imie_r, wiek_r, adres_r, kod_pocztowy_r))
        id_r = self.cursor.fetchall()
        id_r = int(((id_r[0])[0]))
        e_mail_r = input('Podaj e-mail:')
        haslo_r = input('Podaj hasło:')    
        self.cursor.execute("insert into logowanie (e_mail_r, haslo_r, id_r) values ('%s','%s', '%s')" % (e_mail_r,haslo_r,id_r))
        self.conn.commit()
        print('######UZUPEŁNIJ DANE DZIECKA######')      
        imie_dz = input('Podaj imię dziecka:')
        wiek_dz = input('Podaj wiek dziecka:')
        self.cursor.execute("insert into dziecko (imie_dz, wiek_dz, id_r) values ('%s','%s', '%s')" % (imie_dz,wiek_dz,id_r))
        self.conn.commit()               
    def miejsca (self):
        self.cursor.execute("select * from miejsca")
        RS = self.cursor.fetchall()
        for v in RS:
            id_mi = v[0]
            nazwa_mi = v[1]
            adres_mi = v[2]
            kod_pocztowy_mi = v[3]
            print('|%3i|%30s|%30s|%8s|' % (id_mi,nazwa_mi,adres_mi,kod_pocztowy_mi)) 
            
    def wydarzenia(self):
        self.cursor.execute("select * from wydarzenia")
        RS = self.cursor.fetchall()
        for v in RS:
            id_w = v[0]
            nazwa_w = v[1]
            data_w = v[2]
            godz_w = v[3]
            link_w = v[4]
            adres_w = v[5]
            kod_pocztowy_w = v[6]
            print('|%3i|%-65s|%10s|%10s|%-65s|%-35s|%8s|' % (id_w,nazwa_w, data_w, godz_w, link_w, adres_w, kod_pocztowy_w))
           
log1 = Projekt()