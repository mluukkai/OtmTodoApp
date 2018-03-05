# TodoApp

Referenssisovellus kurssille Ohjelmistotekniikan menetelmät

## Komentorivitoiminnot

### testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

### suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _OtmTodoApp-1.0-SNAPSHOT.jar_

Ohjelma suoritetaan komennolla 

```
java -jar tiedostonnimi.jar
```

Ohjelma olettaa, että samassa hakemistossa on konfiguraatiotietoo _config.properties_, joka määrittelee käyttäjät ja todot tallettavien tidostojen nimet, katso tarkemmin 
[käyttöohjeesta](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/kayttoohje.md)

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc
```

## Dokumentaatio

[Käyttöohje](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/tuntikirjanpito.md)