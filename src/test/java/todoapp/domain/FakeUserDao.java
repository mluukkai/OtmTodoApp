package todoapp.domain;

import java.util.ArrayList;
import java.util.List;
import todoapp.dao.UserDao;

public class FakeUserDao implements UserDao {
    List<User> users = new ArrayList<>();

    public FakeUserDao() {
        users.add(new User("testertester", "Teppo Testaaja"));
    }
    
    @Override
    public User findUsername(String username) {
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().orElse(null);
    }
    
    @Override
    public void create(User user) {
        users.add(user);
    } 
    
    @Override
    public List<User> getAll() {
        return users;
    }

}
