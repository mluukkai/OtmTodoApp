
package todoapp.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TodoServiceUserTest {
    
    FakeTodoDao todoDao;
    FakeUserDao userDao;
    TodoService service;
    
    @Before
    public void setUp() {
        todoDao = new FakeTodoDao();
        userDao = new FakeUserDao();
        service = new TodoService(todoDao, userDao);     
    }
    
    @Test
    public void nonExistingUserCanLogIn() {
        boolean result = service.login("nonexisting");
        assertFalse(result);
        
        assertEquals(null, service.getLoggedUser());
    }    
    
    @Test
    public void existingUserCanLogIn() {
        boolean result = service.login("testertester");
        assertTrue(result);
        
        User loggedIn = service.getLoggedUser();
        assertEquals("Teppo Testaaja", loggedIn.getName() );
    }
    
    @Test
    public void loggedInUserCanLogout() {
        service.login("testertester");
        service.logout();
        
        assertEquals(null, service.getLoggedUser());
    }    
    
    @Test
    public void userCreationFailsIfNameNotUnique() throws Exception {
        boolean result = service.createUser("testertester", "Teuvo Testaaja");
        assertFalse(result);
    }
    
    @Test
    public void succesfullyCreatedUserCanLogIn() throws Exception {
        boolean result = service.createUser("dijkstra", "Edsger Dijkstra");
        assertTrue(result);
        
        boolean loginOk = service.login("dijkstra");
        assertTrue(loginOk);
        
        User loggedIn = service.getLoggedUser();
        assertEquals("Edsger Dijkstra", loggedIn.getName() );
    } 
}
