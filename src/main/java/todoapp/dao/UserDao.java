package todoapp.dao;

import java.util.List;
import todoapp.domain.User;

public interface UserDao {

    void create(User user);

    User findUsername(String username);

    List<User> getAll();

}
