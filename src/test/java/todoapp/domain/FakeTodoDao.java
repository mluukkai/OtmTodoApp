package todoapp.domain;

import java.util.ArrayList;
import java.util.List;
import todoapp.dao.TodoDao;

public class FakeTodoDao implements TodoDao {
    List<Todo> todos;

    public FakeTodoDao() {
        todos = new ArrayList<>();
    }   
   
    @Override
    public List<Todo> getAll() {
        return todos;
    }
    
    @Override
    public Todo create(Todo todo) {
        todo.setId(todos.size()+1);
        todos.add(todo);
        return todo;
    }   
    
    @Override
    public void setDone(int id) {
        for (Todo t : todos) {
            if ( t.getId()==id) {
                t.setDone();
            }
        }
    }   

}
