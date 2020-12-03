# TodoApp

Sovelluksen avulla käyttäjien on mahdollista pitää kirjaa tekemättömistään töistä eli todoista. Sovellusta on mahdollista käyttää useamman rekisteröityneen käyttäjän, joilla kaikilla on oma yksilöllinen tehtävälistansa.

Sovellus toimii myös Helsingin yliopiston Tietojenkäsittelytieteen kurssin Ohjelmistotekniikan menetelmät referenssisovelluksena. Sovelluksen tarkoituksena on demonstroida erästä tapaa tehdä suurin piirtein täysiin pisteisiin riittävä dokumentaatio sekä testaus projektillesi. Itse ohjelma on sen verran suppea, että saadaksesi kurssilta arvosanan 5 joudut tekemään hieman laajemman sovelluksen.

## Huomio Javan versioista

JavaFX aiheuttaa päänvaivaa ohjelmiston konfiguroinnin suhteen. Tässä repositoriossa olevan koodin _pitäisi_ toimia riippumatta käyttämästäsi Javan versiosta. Koodi toimii ainakin laitoksen cubbli-Linuxeissa olevilla Java:n versiolla 8 ja 11. 

Jos käytät Javan versiota 8 ja tämä koodi _ei toimi_, niin repositorion _haarassa_ [java8](https://github.com/mluukkai/OtmTodoApp/blob/java8) on versio ohjelmasta, jonka pitäisi toimia myös Java 8:lla. Ainoat erot ovat tiedostossa [pom.xml](https://github.com/mluukkai/OtmTodoApp/blob/java8/pom.xml)
- [mainClass](https://github.com/mluukkai/OtmTodoApp/blob/java8/pom.xml#L73) määritys on erilainen
- osaista [dependencies](https://github.com/mluukkai/OtmTodoApp/blob/java8/pom.xml#L8) ja [plugins](https://github.com/mluukkai/OtmTodoApp/blob/java8/pom.xml#L23) puuttuu JavaFX:n liittyvät elementit.

JavaFX:n liittyvistä ongelmista ja niiden kiertämisestä lisää [täällä](https://github.com/mluukkai/ohjelmistotekniikka-syksy-2019/blob/master/web/maven.md#javafx).

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

## Javan ja Mavenin asennusohjeita Macille Homebrew'n kautta

Homebrew on Linuxin pakettimanagereita vastaava pakettimanageri MacOS-käyttöjärjestelmälle. Nämä ohjeet toimivat ainakin MacOS:n versiolle 10.15. [Asennusohjeet Homebrew'lle.](https://brew.sh/index_fi)

### Javan asennus
Homebrew'n asennuksen jälkeen Javan saa asennettua Macille yksinkertaisesti esimerkiksi komennolla

```
brew install adoptopenjdk
```
### Mavenin asennus ja paluu Javan versioon 11
Mavenin saa asennettua komennolla

```
brew install maven
```

Tällöin Mavenin oletuksena käyttämä Java-versio on Java 15. Java-versioon 11 päästään asentamalla Java 11 komennolla

```
brew install java11
```
Lisäksi täytyy osoittaa Mavenille Javan versio 11. Mavenin versiolla 3.6.3_1 tämä tapahtuu muokkaamalla tiedostoa: /usr/local/Cellar/maven/3.6.3_1/bin/mvn esim. nanolla komennolla

```
sudo nano /usr/local/Cellar/maven/3.6.3_1/bin/mvn
```
HUOM Muista tarkistaa mikä versio Mavenista asentui ja muokkaa tiedostopolkuun oikea versio version 3.6.3_1 tilalle

Muokkaa rivi
```
JAVA_HOME="${JAVA_HOME:-/usr/local/opt/openjdk/libexec/openjdk.jdk/Contents/Home}" exec "/usr/local/Cellar/maven/
```
Muotoon
```
JAVA_HOME="${JAVA_HOME:-/usr/local/opt/openjdk@11/libexec/openjdk.jdk/Contents/Home}" exec "/usr/local/Cellar/maven/
```
Eli muokkaa polkuun ```openjdk``` ```openjdk@11``` ja tallenna tiedosto. Nyt voit tarkistaa komennolla ```mvn --version```, että Maven käyttää Javan versiota 11.

Käyttöjärjestelmän Java version vaihtaminen onnistuu esimerkiksi lisäämällä tiedoston: ```~/.zshrc``` (vanhemmilla MacOS-käyttöjärjestelmillä ```~/.bashrc```) loppuun rivi
```
export JAVA_HOME=/usr/local/opt/openjdk@11/libexec/openjdk.jdk/Contents/Home/
```
Muista käynnistää lisäyksen jälkeen terminaali uudestaan, jolloin komento ```java --version``` näyttää versioksi 11.

