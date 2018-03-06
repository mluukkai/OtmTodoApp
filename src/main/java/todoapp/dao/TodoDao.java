package todoapp.dao;

import java.util.List;
import todoapp.domain.Todo;

public interface TodoDao {

    Todo create(Todo todo) throws Exception;

    List<Todo> getAll();

    void setDone(int id) throws Exception;

}
