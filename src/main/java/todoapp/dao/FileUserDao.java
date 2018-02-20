package todoapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import todoapp.domain.User;

public class FileUserDao implements UserDao {
    private List<User> users;
    private String file;

    public FileUserDao(String file) {
        users = new ArrayList<>();
        this.file = file;
        load();
        
    }

    private void load() {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                User u = new User(parts[0], parts[1]);
                users.add(u);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void save() {
        try {
            FileWriter writer = new FileWriter(new File(file));
            for (User user : users) {
                writer.write(user.getUsername()+";"+user.getName()+"\n");
            }
            writer.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public List<User> getAll() {
        return users;
    }
    
    @Override
    public User findUsername(String username) {
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().orElse(null);
    }
    
    @Override
    public void create(User user) {
        users.add(user);
        save();
    }    
}
