
package todoapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import todoapp.domain.FakeUserDao;
import todoapp.domain.Todo;
import todoapp.domain.User;

public class FileTodoDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();    
  
    File userFile;  
    TodoDao dao;    
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        UserDao userDao = new FakeUserDao();
        userDao.create(new User("testertester", "Teppo Testaaja"));
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("1;siivoa;false;testertester\n");
        }
        
        dao = new FileTodoDao(userFile.getAbsolutePath(), userDao);        
    }
   
    @Test
    public void todosAreReadCorrectlyFromFile() {
        List<Todo> todos = dao.getAll();
        assertEquals(1, todos.size());
        Todo todo = todos.get(0);
        assertEquals("siivoa", todo.getContent());
        assertFalse(todo.isDone());
        assertEquals(1, todo.getId());
        assertEquals("testertester", todo.getUser().getUsername());
    }    
    
    @Test
    public void todosCanBeSetDone() throws Exception {
        dao.setDone(1);
        Todo todo = dao.getAll().get(0);
        assertTrue(todo.isDone());
    }       
    
    @Test
    public void createdTodosAreListed() throws Exception {    
        dao.create(new Todo("lue kokeeseen", new User("testertester", "")));
        
        List<Todo> todos = dao.getAll();
        assertEquals(2, todos.size());
        Todo todo = todos.get(1);
        assertEquals("lue kokeeseen", todo.getContent());
        assertFalse(todo.isDone());
        assertNotEquals(1, todo.getId());
        assertEquals("testertester", todo.getUser().getUsername());
    }     
    
    @After
    public void tearDown() {
        userFile.delete();
    }
    
}
