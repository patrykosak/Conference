# Instrukcja uruchomienia projektu
1. Sklonuj repozytorium i otwórz w Intelij IDEA.
2. Odpal aplikacje z klasy DemoApplication.
3. Pobierz i zainstaluj postmana jeśli nie posiadasz.
4. Przetestuj aplikacje przykładowymi zapytaniami.

# Przykładowe zapytania
1. Użytkownik może obejrzeć plan konferencji. 

Metoda GET

        http://localhost:8090/lectures

2. Użytkownik po podaniu swojego loginu może obejrzeć prelekcje na które się zapisał. 

Metoda GET

        http://localhost:8090/users/lectures/{login}
        
W miejsce {login} wpisujemy login użytkownika który nas interesuje
        
3. Dodanie użytkownika. Jeżeli w systemie istnieje już użytkownik z danym loginem, ale z innym adresem e-mail, system powinien zaprezentować komunikat „Podany login jest już zajęty”.

Metoda POST

        http://localhost:8090/users/save
        
        
![Screenshot 2022-05-13 21-45-27](https://user-images.githubusercontent.com/69324884/168379144-7b5826d2-c0e2-4c17-ab6a-6ce61d4f8eb3.jpg)


4. Jeżeli prelekcja ma jeszcze wolne miejsca, użytkownik ma możliwość dokonania rezerwacji. Podczas dokonywania rezerwacji powinien podać swój login oraz adres e-mail.

Metoda POST

        http://localhost:8090/users/signup
        
![Screenshot 2022-05-13 21-41-01](https://user-images.githubusercontent.com/69324884/168378456-65b99dee-96da-45e9-b3cb-f1e9b29831ce.jpg)


5. Użytkownik może anulować rezerwację. 

Metoda POST

        http://localhost:8090/users/cancel


![Screenshot 2022-05-13 21-42-25](https://user-images.githubusercontent.com/69324884/168378813-277dddc1-94ce-4480-99e2-b702aa4241ca.jpg)

6. Użytkownik może zaktualizować swój adres e-mail. 

Metoda PUT

        http://localhost:8090/users/changeemail/{login}
        
        W miejsce {login} wpisujemy login użytkownika który nas interesuje
        
![Screenshot 2022-05-13 21-47-11](https://user-images.githubusercontent.com/69324884/168379321-e37e05ad-9a2f-41ad-864d-05e066403e90.jpg)


7. System umożliwia wyświetlenie listy zarejestrowanych użytkowników wraz z ich adresami e-mail. 

Metoda GET

        http://localhost:8090/users

8. Wygenerowanie zestawienia dla organizatora: 
	- zestawienie wykładów wg zainteresowania (procentowy udział uczestników w danym wykładzie)
	- zestawienie ścieżek tematycznych wg zainteresowania (procentowy udział)

Metoda GET

      http://localhost:8090/interests
