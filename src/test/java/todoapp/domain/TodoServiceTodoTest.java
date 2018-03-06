/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapp.domain;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mluukkai
 */
public class TodoServiceTodoTest {
    
    FakeTodoDao todoDao;
    FakeUserDao userDao;
    TodoService service;
    
    @Before
    public void setUp() {
        todoDao = new FakeTodoDao();
        userDao = new FakeUserDao();
        User u1 = new User("tester1", "Teuvo Testaaja");
        User u2 = new User("tester2", "Tellervo Testaaja");
        userDao.create(u1);
        userDao.create(u2);        
        todoDao.create(new Todo(1, "puistele matot", false, new User("tester1", "")));
        service = new TodoService(todoDao, userDao);     
        service.login("tester1");
    }
    
    @Test
    public void atStartListContainsInitializedTodos() {
        List<Todo> todos = service.getUndone();
        
        assertEquals(1, todos.size());
        Todo todo = todos.get(0);
        assertEquals("puistele matot", todo.getContent());
        assertEquals("tester1", todo.getUser().getUsername());
    }
     
    @Test
    public void listEmpytIfNotLoggedIn() {
        service.logout();
        List<Todo> todos = service.getUndone();
        
        assertEquals(0, todos.size());
    }    
    
    @Test
    public void loggedUsersListContainsAddedTodo() {
        addTodo("imuroi");
        
        List<Todo> todos = service.getUndone();               
        assertEquals(2, todos.size());
        Todo todo = todos.get(1);
        
        assertEquals("imuroi", todo.getContent());
        assertEquals("tester1", todo.getUser().getUsername());
    }    

    @Test
    public void loggedUsersListDoesNotContainTodosOfOther() {
        addTodo("imuroi");
        service.logout();
        service.login("tester2");
        
        List<Todo> todos = service.getUndone();
        assertEquals(0, todos.size());
    }      
 
    @Test
    public void whenMarkedDoneIsNotListed() {    
        service.markDone(1);
        
        List<Todo> todos = service.getUndone();               
        assertEquals(0, todos.size());
    }      
    
    private void addTodo(String content) {
        service.createTodo(content);
    }
}
