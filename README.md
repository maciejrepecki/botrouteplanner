#BOT ROUTE PLANNER

Bot Route Planner jest aplikacją służącą do wyliczania najszybszej (czasowo) trasy dla bota, 
który z wybranego miejsca startowego ładuje wybrany produkt i dostarcza go do stacji odbiorczej.

###UROCHOMIENIE

W celu uruchomienia aplikacji należy zbudować plik wykonawczy (botrouteplanner.jar). 
Można wykonać to przy użyciu narzędzia Maven, poprzez komendę:
```shell script
mvn clean package
```
Po wywołaniu komendy plik jar
utworzy się w katalogu target.
Aby poprawnie uruchomić program, plik "botrouteplanner.jar" musi znajdować się w tym samym katalogu co pliki z danymi.
Dane powinny być podzielone na dwa pliki txt:
* GRID - plik zawierający informacje o gridzie: liczbę rzędów i kolumn, liczbę warstw
, rozmieszczenie modułów (H,B,O,S) oraz rozmieszczenie produktów na gridzie
* JOB - plik zawierający informacje o zadaniu dla bota: punkt startowy bota, punkt końcowy oraz produkt do przetransportowania

Aby uruchomić program dla zadanych plików z danymi należy wywołać komendę:

```shell script
java -jar botrouteplanner.jar grid-1.txt job-1.txt
```
###ALGORYTM

W aplikacji wykorzystano algorytm Dijkstry, służący do znajdowania najkrótszej drogi na grafie o zmiennych wagach krawędzi.
Algorytm znajduje zastosowanie przy trasowaniu oraz wyznaczaniu najkrótszej drogi do danej miejscowości.
Algorytm Dijkstry znajduje w grafie wszystkie najkrótsze ścieżki pomiędzy wybranym wierzchołkiem a wszystkimi pozostałymi, 
przy okazji wyliczając również koszt przejścia każdej z tych ścieżek.