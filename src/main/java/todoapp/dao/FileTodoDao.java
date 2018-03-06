package todoapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import todoapp.domain.Todo;
import todoapp.domain.User;

public class FileTodoDao implements TodoDao {
    public List<Todo> todos;
    private String file;

    public FileTodoDao(String file, UserDao users) throws Exception {
        todos = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                boolean done = Boolean.parseBoolean(parts[2]);
                User user = users.getAll().stream().filter(u->u.getUsername().equals(parts[3])).findFirst().orElse(null); 
                Todo todo = new Todo(id, parts[1], done, user);
                todos.add(todo);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }
    
    private void save() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Todo todo : todos) {
                writer.write(todo.getId() + ";" + todo.getContent() + ";" + todo.isDone() + ";" + todo.getUser().getUsername() + "\n");
            }
        }
    }    
    
    private int generateId() {
        return todos.size() + 1;
    }
    
    @Override
    public List<Todo> getAll() {
        return todos;
    }
    
    @Override
    public Todo create(Todo todo) throws Exception {
        todo.setId(generateId());
        todos.add(todo);
        save();
        return todo;
    }   
    
    @Override
    public void setDone(int id) throws Exception {
        for (Todo t : todos) {
            if (t.getId() == id) {
                t.setDone();
            }
        }
        save();
    }    


}


