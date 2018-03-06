
package todoapp.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    @Test
    public void equalWhenSameUsername() {
        User u1 = new User("tester", "Teppo");
        User u2 = new User("tester", "Teppo");
        assertTrue(u1.equals(u2));
    }
    
    @Test
    public void nonEqualWhenDifferentUsername() {
        User u1 = new User("tester", "Teppo");
        User u2 = new User("hellas", "Arto");
        assertFalse(u1.equals(u2));
    } 
    
    @Test
    public void nonEqualWhenDifferentType() {
        User u = new User("tester", "Teppo");
        Object o = new Object();
        assertFalse(u.equals(o));
    }     
}
