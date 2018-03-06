package todoapp.dao;

import java.util.List;
import todoapp.domain.User;

public interface UserDao {

    User create(User user);

    User findByUsername(String username);

    List<User> getAll();

}
