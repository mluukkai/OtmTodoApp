package todoapp.dao;

import java.util.List;
import todoapp.domain.User;

public interface UserDao {

    User create(User user) throws Exception;

    User findByUsername(String username);

    List<User> getAll();

}
