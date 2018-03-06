package todoapp.domain;

/**
 * Yksittäistä työtä kuvaava luokka 
 */

public class Todo {

    private int id;
    private String content;
    private boolean done;
    private User user;

    public Todo(int id, String content, boolean done, User user) {
        this.id = id;
        this.content = content;
        this.done = done;
        this.user = user;
    }
    
    public Todo(String content, User user) {
        this.content = content;
        this.user = user;
        this.done = false;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone() {
        done = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo)) {
            return false;
        }
        Todo other = (Todo) obj;
        return id == other.id;
    }

}
