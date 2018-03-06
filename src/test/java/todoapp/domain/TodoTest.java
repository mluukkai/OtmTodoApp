
package todoapp.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TodoTest {
    
    @Test
    public void equalWhenSameId() {
        Todo t1 = new Todo(1, null, true, null);
        Todo t2 = new Todo(1, null, true, null);
        assertTrue(t1.equals(t2));
    }
  
    @Test
    public void notEqualWhenDifferentId() {
        Todo t1 = new Todo(1, null, true, null);
        Todo t2 = new Todo(55, null, true, null);
        assertFalse(t1.equals(t2));
    }   
    
    @Test
    public void nonEqualWhenDifferentType() {
        Todo t = new Todo(1, null, true, null);
        Object o = new Object();
        assertFalse(t.equals(o));
    }      
}
