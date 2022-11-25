# BankApplication
Projekt zaliczeniowy:  BankAccount – COVID19 

Data publikacji: czwartek, 12 listopada 2022 r.
Data realizacji: 26 listopada 2022 r. (do 23:59). 
Prośba o przesłanie spakowanego projektu jako .zip na Moodle
Grupy 2-os możliwe.

W oparciu o BankAccount oraz inne projekty o których sobie mówiliśmy – kody źródłowe dostępne na Moodle w ramach naszego kursu.

I.	UZASADNIENIE PROJEKTU:
Wyobraźmy sobie, że mamy koniec marca 2020 r. i pracujemy dla instytucji finansowej jako informatycy. 
Mamy zlecenie aby od dnia 1 kwietnia 2020 r. systemy informatyczne uniemożliwiały wypłaty gotówki większe niż 1000 PLN dziennie.
Jednocześnie rachunki bankowe dla obcokrajowców nie obowiązują te restrykcje (prawo międzynarodowe - zasada wzajemności etc.).
Jeżeli mamy do czynienia z rachunkiem firmowym, w czasie pandemii, to pierwszy depozyt to bezzwrotna pożyczka rządowa w wysokości 5000 PLN ale nie istnieje możliwość zamknięcia rachunku – do 2 lat po końcu pandemii.

Program powinien  tworzyć rachunek w oparciu o class: 
•	BankAccount jeżeli mamy datę przed 1 kwietnia 2020 r. lub po 1 stycznia 2022 r. (przed początkiem pandemii lub po końcu pandemii),
•	BankAccount_COVID19 jeżeli mamy datę między 1 kwietnia 2020 r. a przed 1 stycznia 2022 r.,
•	BankAccount_INT - jeżeli mamy do czynienia z klientem międzynarodowym,
•	BankAccount_COVID19_firma – dla klientów korporacyjnych jeżeli jeżeli mamy datę między 1 kwietnia 2020 r. a przed 1 stycznia 2022 r.,
•	BankAccount_firma – dla klientów korporacyjnych jeżeli mamy datę przed 1 kwietnia 2020 r. lub po 1 stycznia 2022 r. (przed początkiem pandemii lub po końcu pandemii).

II.	SPECYFIKACJA FUNKCONALNA:
Założenia Projektu: 
1.	Utworzyć: 
•	1 superclass : BankAccount 
•	4 subclass: 
o	BankAccount_INT, BankAccount_firma, BankAccount_COVID19, BankAccount_COVID19_firma
•	Pola atrybutowe:
BankAccount: double balance, String id, String name (saldo rachunku, identyfikator klienta, nazwa klienta)
BankAccount_INT: dodatkowe pole String origin (kraj pochodzenia)
BankAccount_Firma: dodatkowe pole String REGON (wartość ID wtedy pusta)
•	Metody dostępne w ramach class BankAccount:
o	Metoda deposit() - Zdeponowanie środków na rachunku, 
o	Metoda withdraw() - Wyciągnięcie środków z rachunku (z limitem poniżej) 
o	Metoda close() - Zamknięcie rachunku (uruchomienie metody withdraw (wyciągnięcia środków z rachunku) oraz skasowanie obiektu). 
o	Metoda toString() – do wydrukowania informacji o kliencie (wartości pól atrybutowych wraz ze spójnym tekstem)
2.	Utworzyć driver - program główny. 
W ramach programu głównego - pracownicy Banku powinni mieć możliwość stworzenia rachunku
dla klienta (PL lub INT lub Firma) oraz powinien klient być zapytany czy chce zdeponować lub wyciągnąć środki z rachunku (metody withdraw() i deposit())
3.	Dane powinny być pobierane za pomocą okienek dialogowych i, przed utworzeniem obiektów, mają być utrzymywane w tablicach: array[] lub ArrayList<>*
4.	Zapis obiektów (klientów)  do plików tekstowych (jeden plik dla wszystkich klientów).
5.	Wczytanie danych z plików tekstowych do tablic: : array[] lub ArrayList<>*.
6.	Wyświetlenie danych w ramach okienek dialogowych.
W razie pytań jestem do Waszej dyspozycji via Teams. Pozdrawiam Was uprzejmie.
