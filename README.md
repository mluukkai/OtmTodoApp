# TodoApp

Sovelluksen avulla käyttäjien on mahdollista pitää kirjaa tekemättömistään töistä eli todoista. Sovellusta on mahdollista käyttää useamman rekisteröityneen käyttäjän, joilla kaikilla on oma yksilöllinen tehtävälistansa.

Sovellus toimii myös Helsingin yliopiston Tietojenkäsittelytieteen kurssin Ohjelmistotekniikan menetelmät referenssisovelluksena. Sovelluksen tarkoituksena on demonstroida erästä tapaa tehdä suurin piirtein täysiin pisteisiin riittävä dokumentaatio sekä testaus projektillesi. Itse ohjelma on sen verran suppea, että saadaksesi kurssilta arvosanan 5 joudut tekemään hieman laajemman sovelluksen.

## Huomio Javan versioista

Tässä repositoriossa olevan koodin _pitäisi_ toimia riippumatta käyttämästäsi Javan versiosta. Koodi toimii ainakin laitoksen cubbli-Linuxeissa olevilla Java:n versiolla 8 ja 11. 

Jos käytät Javan versiota 8 ja tämä koodi _ei toimi_, niin repositorion _haarassa_ [java8](https://github.com/mluukkai/OtmTodoApp/blob/java8) on versio ohjelmasta, jonka pitäisi toimia myös Java 8:lla. Ainoat erot ovat tiedostossa [pom.xml](https://github.com/mluukkai/OtmTodoApp/blob/java8/pom.xml)
- [mainClass](https://github.com/mluukkai/OtmTodoApp/blob/java8/pom.xml#L73) määritys on erilainen
- osaista [dependencies](https://github.com/mluukkai/OtmTodoApp/blob/java8/pom.xml#L8) ja [plugins](https://github.com/mluukkai/OtmTodoApp/blob/java8/pom.xml#L23) puuttuu JavaFX:n liittyvät elementit.

Jos et saa projektia toimimaan koneellasi, saat apua pajassa.

## Dokumentaatio

[Käyttöohje](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/mluukkai/OtmTodoApp/releases/tag/viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _OtmTodoApp-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/mluukkai/OtmTodoApp/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
