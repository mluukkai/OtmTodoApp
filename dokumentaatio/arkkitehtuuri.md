# Arkkitehtuurikuvat

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria:

![](https://raw.githubusercontent.com/mluukkai/otm-2018/master/web/images/l-10.png)
Pakkaus _todoapp.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _todoapp.domain_ sovelluslogiikan ja _todoapp.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää kolme erillistä näkymää
- kirjautuminen
- uuden käyttäjän luominen
- todolista

jokainen näistä on toteutettu omana _scene_-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen _stageen_.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion _todoServicen_ metodeja.

Kun sovelluksen todolistan tilanne muuttuu, eli uusi käyttäjä kirjautuu, todoja merkitään tehdyksi tai niitä luodaan, renderöi sovellus todolistanäkymän uudelleen sovelluslogiikalta saamansa näytettävien todojen listan perusteella.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat _User_ ja _Todo_, jotka kuvaavat käyttäjiä ja käyttäjien tehtäviä:

![](https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/a-2.png)

Toiminnallisista kokonaisuuksista vastaa luokkan _TodoService_ ainoa olio. Luokka toimii rajapintana käyttöliittymälle ja tarjoaa kaikille käyttäliittymän toiminnoille oman metodin. Näitä ovat esim.
- boolean login(String username)
- List<Todo> getUndone() 
- void createTodo(String content, User user)
- void markDone(int id)

_TodoService_ pääsee käsiksi käyttäjiin ja todoihin tietojen tallennuksesta vastaavan luokkien kautta:

![](https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/a-3.png)

## Tietojen pysyväistallennus

Pakkauksen _todoapp.dao_ luokat _FileTodoDao_ ja _FileUserDao_ huolehtivat tietojen tallettamisesta sovelluksen juuressa oleviin tiedostoihin _users.txt_ ja _todos.txt_.

Luokat noudattavat Data Access Object -suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa. Luokat onkin eristetty rajapintojen _TodoDao_ ja _UserDao_ taakse ja sovelluslogiikka ei käytä luokkia suoraan. 

Sovelluslogiikan testauksessa hyödynnetäänkin tätä siten, että testeissä käytetään tiedostoon tallentavien DAO-olioiden sijaan keskusmuistiin tallentavia toteutuksia.

### Tiedostot

Sovellus tallettaa käyttäjät tiedostoon _users.txt_ seuraavassa formaatissa

<pre>
mluukkai;Matti Luukkainen
hellas;Arto Hellas
</pre>

eli ensin käyttäjätunnus ja puolipisteellä erotettuna käyttäjän nimi.

Käyttäjien työt taletetaan tiedostoon _todos.txt_ jonka formaatti seuraava

<pre>
1;Tiskaa;true;mluukkai
2;Valmistele OhPen luento;false;hellas
3;Imuroi ja käy kaupassa;false;mluukkai
</pre>

Kentät on eroteltu puolipistein. Ensimmäisenä todon tunniste eli _id_, toisena kuvaus, kolmantena tieto siitä onko tehtävä jo suoritettu ja viimeisenä tehtävän luoneen käyttäjän käyttäjätunnus.

### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman pääkäyttötapauksen osalta sekvenssikaaviona.

#### käyttäjän kirjaanutuminen

Kun kirjautumisnäkymässä oleva käyttäjä kirjoitettuaan käyttäjätunnuksen klikkaa painiketta _loginButton_ etenee sovelluksen kontrolli seuraavasti:

![](https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/a-4.png)

Painikkeen painamiseen reagoiva tapahtumankuuntelija kutsuu sovelluslogiikan _appService_ metodia _login_ antaen parametriksi kirjautuneen käyttäjän. Sovelluslogiikka selvittää _userDao_:n avulla onko käyttäjä validi. Jos on, on seurauksena se että käyttöliittymä vaihtaa näkymäksi _todoScenen_, eli sovelluksen varsinaisen päänäkymän.

#### uuden käyttäjän luominen

tbd

#### todon merkkaaminen tehdyksi

tbd