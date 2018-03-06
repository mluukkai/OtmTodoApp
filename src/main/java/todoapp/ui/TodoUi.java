package todoapp.ui;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import todoapp.domain.TodoService;
import todoapp.domain.Todo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import todoapp.dao.FileTodoDao;
import todoapp.dao.FileUserDao;

public class TodoUi extends Application {
    private TodoService todoService;
    
    private Scene todoScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private VBox todoNodes;
    private Label menuLabel = new Label();
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String todoFile = properties.getProperty("todoFile");
            
        FileUserDao userDao = new FileUserDao(userFile);
        FileTodoDao todoDao = new FileTodoDao(todoFile, userDao);
        todoService = new TodoService(todoDao, userDao);
    }
    
    public Node createTodoNode(Todo todo) {
        HBox box = new HBox(10);
        Label label  = new Label(todo.getContent());
        label.setMinHeight(28);
        Button button = new Button("done");
        button.setOnAction(e->{
            todoService.markDone(todo.getId());
            redrawTodolist();
        });
                
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0,5,0,5));
        
        box.getChildren().addAll(label, spacer, button);
        return box;
    }
    
    public void redrawTodolist() {
        todoNodes.getChildren().clear();     

        List<Todo> undoneTodos = todoService.getUndone();
        undoneTodos.forEach(todo->{
            todoNodes.getChildren().add(createTodoNode(todo));
        });     
    }
    
    @Override
    public void start(Stage primaryStage) {               
        // login scene
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(e->{
            String username = usernameInput.getText();
            menuLabel.setText(username + " logged in...");
            if ( todoService.login(username) ){
                loginMessage.setText("");
                redrawTodolist();
                primaryStage.setScene(todoScene);  
                usernameInput.setText("");
            } else {
                loginMessage.setText("use does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        });  
        
        createButton.setOnAction(e->{
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);   
        });  
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);       
        
        loginScene = new Scene(loginPane, 300, 250);    
   
        // new createNewUserScene
        
        VBox newUserPane = new VBox(10);
        
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField(); 
        Label newUsernameLabel = new Label("username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);
     
        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);        
        
        Label userCreationMessage = new Label();
        
        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e->{
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();
   
            if ( username.length()==2 || name.length()<2 ) {
                userCreationMessage.setText("username or name too short");
                userCreationMessage.setTextFill(Color.RED);              
            } else if ( todoService.createUser(username, name) ){
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }
 
        });  
        
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton); 
       
        newUserScene = new Scene(newUserPane, 300, 250);
        
        // main scene
        
        ScrollPane todoScollbar = new ScrollPane();       
        BorderPane mainPane = new BorderPane(todoScollbar);
        todoScene = new Scene(mainPane, 300, 250);
                
        HBox menuPane = new HBox(10);    
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");      
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e->{
            todoService.logout();
            primaryStage.setScene(loginScene);
        });        
        
        HBox createForm = new HBox(10);    
        Button createTodo = new Button("create");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newTodoInput = new TextField();
        createForm.getChildren().addAll(newTodoInput, spacer, createTodo);
        
        todoNodes = new VBox(10);
        todoNodes.setMaxWidth(280);
        todoNodes.setMinWidth(280);
        redrawTodolist();
        
        todoScollbar.setContent(todoNodes);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);
        
        createTodo.setOnAction(e->{
            todoService.createTodo(newTodoInput.getText());
            newTodoInput.setText("");       
            redrawTodolist();
        });
        
        // seutp primary stage
        
        primaryStage.setTitle("Todos");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            System.out.println("closing");
            System.out.println(todoService.getLoggedUser());
            if (todoService.getLoggedUser()!=null) {
                e.consume();   
            }
            
        });
    }

    @Override
    public void stop() {
      // tee lopetustoimenpiteet täällä
      System.out.println("sovellus sulkeutuu");
    }    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
