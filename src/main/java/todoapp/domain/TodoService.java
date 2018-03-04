package todoapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import todoapp.dao.TodoDao;
import todoapp.dao.UserDao;

/**
 * Sovelluslogiikasta vastaava luokka 
 */

public class TodoService {
    private TodoDao todoDao;
    private UserDao userDao;
    private User loggedIn;
    
    public TodoService(TodoDao todoDao, UserDao userDao) {
        this.userDao = userDao;
        this.todoDao = todoDao;
    }
    
    /**
    * Uuden todon lisääminen kirjautuneena olevalle käyttäjälle
    *
    * @param   content   luotavan todon sisältö
    */
    
    public void createTodo(String content) {
        Todo todo = new Todo(content, loggedIn);
        todoDao.create(todo);   
    }
    
    /**
    * kirjautuneen käyttäjän tekemättömät todot
    * 
    * @return kirjautuneen käyttäjän tekemättömät todot
    */
    
    public List<Todo> getUndone() {
        if (loggedIn==null) {
            return new ArrayList<>();
        }
          
        return todoDao.getAll()
                .stream()
                .filter(t->{
                    System.out.println(t.getUser());
                    return t.getUser().equals(loggedIn);
                })
                .filter(t->!t.isDone())
                .collect(Collectors.toList());
    }
   
    /**
    * todon merkitseminen tehdyksi
    * 
    * @param   id   tehdyksi merkittävän todon tunniste
    */    
    
    public void markDone(int id) {
        todoDao.setDone(id);
    }
    
    /**
    * sisäänkirjautuminen
    * 
    * @param   username   käyttäjätunnus
    * 
    * @return true jos käyttäjätunnus on olemassa, muuten false 
    */    
    
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if ( user==null) {
            return false;
        }
        
        loggedIn = user;
        
        return true;
    }
    
    /**
    * kirjautuneena oleva käyttäjä
    * 
    * @return kirjautuneena oleva käyttäjä 
    */   
    
    public User getLoggedUser() {
        return loggedIn;
    }
   
    /**
    * uloskirjautuminen
    */  
    
    public void logout(){
      loggedIn = null;  
    }
    
    /**
    * uuden käyttäjän luominen
    * 
    * @param   username   käyttäjätunnus
    * @param   name   käyttäjän nimi
    * 
    * @return true jos käyttäjätunnus on luotu onnistuneesti, muuten false 
    */ 
    
    public boolean createUser(String username, String name) {   
        User user = new User(username, name);
        if (userDao.findByUsername(username)!=null){
            return false;
        }
        userDao.create(user);
        return true;
    }
}
