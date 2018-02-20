package todoapp.domain;

public class Todo {

    private int id;
    private String conent;
    private boolean done;
    private User user;

    public Todo(int id, String conent, boolean done, User user) {
        this.id = id;
        this.conent = conent;
        this.done = done;
        this.user = user;
    }
    
    public Todo(String conent, User user) {
        this.conent = conent;
        this.user = user;
        this.done = true;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return conent;
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
        Todo other = (Todo)obj;
        return id == other.id;
    }

    public void setDoneTo(boolean done) {
        this.done = done;
    }
}
