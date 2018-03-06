# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testien ytimen moudostavat sovelluslogiikkaa, eli pakkauksen [todoapp.domain](https://github.com/mluukkai/OtmTodoApp/tree/master/src/main/java/todoapp/domain) luokkia testaavat integraatiotestit [TodoServiceUserTest](https://github.com/mluukkai/OtmTodoApp/blob/master/src/test/java/todoapp/domain/TodoServiceUserTest.java) ja [TodoServiceTodoTest](https://github.com/mluukkai/OtmTodoApp/blob/master/src/test/java/todoapp/domain/TodoServiceUserTest.java) joiden määrittelevät testitapaukset simuloivat käyttöliittymän [TodoService](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/domain/TodoService.java)-olin avulla suorittamia toiminnallisuuksia.

Integraatiotestit käyttävät datan pysyväistallennukseen DAO-rajapintojen keskusmuistitoteutuksia [FakeTodoDao](https://github.com/mluukkai/OtmTodoApp/blob/master/src/test/java/todoapp/domain/FakeTodoDao.java) ja [FakeUserDao](https://github.com/mluukkai/OtmTodoApp/blob/master/src/test/java/todoapp/domain/FakeTodoDao.java)

Sovelluslogiikkakerroksen luokille [User](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/domain/User.java) ja [Todo](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/domain/User.java) on tehty muutama yksikkötesti kattamaan tapaukset, joita integraatiotestit eivät kata (mm. olioiden _equals_-metodit).

### DAO-luokat

Molempien DAO-luokkien toiminnallisuus on testattu luomalla testeissä tilapäinen tiedosto hyödyntäen JUnitin [TemporaryFolder](https://junit.org/junit4/javadoc/4.12/org/junit/rules/TemporaryFolder.html)-ruleja.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauskattavuus on xx %:

testaamatta jäi:

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

- asennus

- toiminnallisuudet