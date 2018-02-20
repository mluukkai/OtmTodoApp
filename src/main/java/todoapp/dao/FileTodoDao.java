package todoapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import todoapp.domain.Todo;
import todoapp.domain.User;

public class FileTodoDao {
    public List<Todo> todos;
    private String file;

    public FileTodoDao(String file, FileUserDao users) {
        todos = new ArrayList<>();
        this.file = file;
        try{
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                boolean done = Boolean.parseBoolean(parts[2]);
                User user = users.getAll().stream().filter(u->u.getUsername().equals(parts[3])).findFirst().orElse(null); 
                Todo todo = new Todo(id, parts[1], done, user);
                todos.add(todo);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void save() {
        try {
            FileWriter writer = new FileWriter(new File(file));
            for (Todo todo : todos) {
                writer.write(todo.getId()+";"+todo.getContent()+";"+todo.isDone()+";"+todo.getUser().getUsername()+"\n");
            }
            writer.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }    
    
    private int generateId() {
        return todos.size()+1;
    }
    
    public List<Todo> getAll() {
        return todos;
    }
    
    public void create(Todo todo) {
        todo.setId(generateId());
        todos.add(todo);
        save();
    }   
    
    public void setDone(int id) {
        for (Todo t : todos) {
            if ( t.getId()==id) {
                t.setDone();
            }
        }
        save();
    }    


}


