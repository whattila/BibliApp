# Architektúra

Az alkalmazás architektúrája az MVVM mintát követi, mivel ez manapság az általános az adatvezérelt alkalmazásoknál.<br>
A View-k és ViewModelek feature-ök szerint vannak csoportosítva.<br>
A több réteg által használt data classok a domain mappában találhatók.<br>
A data/repositories mappában találhatók a repositoryk, mivel ezeket több nézet is használja (a data mappában lesznek még az adatbázissal és hálózaton kommunikáló osztályok, database és network mappákban).<br>

<img src="./assets/commits.PNG" width="200">
<img src="./assets/build.PNG" width="200">

# Házi feladat specifikáció

Információk [itt](https://viaumb02.github.io/laborok/android/A01/)

## Mobilszoftver Laboratórium
### 2024.03.23.
### Hidán Attila Szabolcs - (nwvrra)
### Laborvezető: Hideg Attila

## BibliApp

<img src="./assets/book_icon.png" width="160">

## Bemutatás

Az alkalmazás egyetlen helyen összegyűjti a Biblia számtalan különböző (nyelvű) változatát. Segítségével bárhol olvashatjuk az Ó- és az Újszövetséget, a kedvenc részeinket internetkapcsolat nélkül is.

## Főbb funkciók

Az alkalmazással a Biblia böngészésére van lehetőségünk az American Bible Society (ABS) API-ja, az API.Bible (https://docs.api.bible/) segítségével.<br>
A nyitóoldalon egy listából választhatunk az API.Bible elérhető fordításai közül. Ezután két újabb listázó oldalon kiválasztjuk az adott fordításnak azt a könyvét és a könyv fejezetét, amit olvasni szeretnénk. Egy részletező oldalon pedig olvashatjuk a választott fejezetet.<br>
Lehetőségünk van a kedvenc fejezeteinknek a lementésére egy lokális adatbázisba, melyeket így offline is olvashatunk. Ezt az olvasó nézeten tehetjük meg. A lementett fejezeteket egy külön listában láthatjuk, ahol megnyithatjuk és olvashatjuk őket, illetve kivehetjük őket a mentettek közül. Legfeljebb 5 fejezetet menthetünk le egyszerre, utána az újonnan mentett fejezetek felülírják a legrégebben mentetteket. 

### User story-k:

Én mint Aladár, el akarom olvasni János evangéliumát, hogy felkészüljek lélekben karácsonyra.<br>
Én mint Béla, le akarom menteni az Apostolok cselekedeteit, hogy olvashassam őket a repülőn is.

### Use case-ek:

<b>Cím</b>: Fejezet olvasása.<br>
<b>Leírás</b>: Kiválasztjuk az adott fordítás egy elérhető fejezetét és olvassuk azt.<br>
<b>Aktorok</b>: Felhasználó<br>
<b>Főforgatókönyv</b>: 1. A felhasználó a változatlistázó képernyőn kiválasztja a megfelelő változatot. 2. A felhasználó a könyvlistázó képernyőn kiválasztja a megfelelő könyvet. 3. A felhasználó a fejezetlistázó képernyőn kiválasztja a megfelelő fejezetet. 4. A felhasználó a fejezetolvasó nézeten olvassa a választott fejezetet.<br>
<b>Alternatív forgatókönyv</b>: Az 1-4. lépések során valahol hiba miatt nem töltődik be a tartalom.

<b>Cím</b>: Fejezet mentése.<br>
<b>Leírás</b>: Kiválasztjuk az adott fordítás egy elérhető fejezetét és elmentjük azt az offline adatbázisba.<br>
<b>Aktorok</b>: Felhasználó<br>
<b>Főforgatókönyv</b>: 1. A felhasználó a változatlistázó képernyőn kiválasztja a megfelelő változatot. 2. A felhasználó a könyvlistázó képernyőn kiválasztja a megfelelő könyvet. 3. A felhasználó a fejezetlistázó képernyőn kiválasztja a megfelelő fejezetet. 4. A felhasználó a fejezetolvasó nézeten megnyomja a mentés gombot.<br>
<b>Alternatív forgatókönyv</b>: Az 1-4. lépések során valahol hiba miatt nem töltődik be a tartalom.

<b>Cím</b>: Mentett fejezet olvasása.<br>
<b>Leírás</b>: Kiválasztjuk az egyik korábban mentett fejezetet és olvassuk azt.<br>
<b>Aktorok</b>: Felhasználó<br>
<b>Főforgatókönyv</b>: 1. A felhasználó a mentett fejezetek képernyőn kiválasztja az egyik fejezetet. 2. A felhasználó a fejezetolvasó nézeten olvassa a választott fejezetet.

<b>Cím</b>: Fejezet kivétele a mentettek közül.<br>
<b>Leírás</b>: Az egyik korábban mentett fejezetet kivesszük a mentettek közül.<br>
<b>Aktorok</b>: Felhasználó<br>
<b>Főforgatókönyv</b>: 1. A felhasználó a mentett fejezetek képernyőn kiválasztja az egyik fejezetet törlésre.

### Use case diagram

<img src="./assets/Main.png" width="600">

## Képernyőtervek

<img src="./assets/bible_select@1x.png" width="200">
<img src="./assets/book_select@1x.png" width="200">
<img src="./assets/chapter_select@1x.png" width="200">
<img src="./assets/chapter_reading@1x.png" width="200">
<img src="./assets/saved@1x.png" width="200">