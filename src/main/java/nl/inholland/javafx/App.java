package nl.inholland.javafx;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App<loginIsValidPassword> extends Application{

    Label userLabel,passwordLabel,errorMessage;
    TextField userInput;
    PasswordField passwordField;
    Button loginButton;
    @Override
    public void start(Stage window) throws Exception{

        window.setHeight(200);
        window.setWidth(320);
        window.setTitle("Login Window");

        userLabel = new Label("Username:");
        passwordLabel = new Label("Password:");
        userInput = new TextField();
        userInput.setPromptText("Username");
        passwordField = new PasswordField();
        passwordField.setPromptText("password");
        loginButton = new Button("log in");
        errorMessage = new Label();

        VBox container = new VBox();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10); // Vertical spacing between grid items
        gridPane.setHgap(8); // Horizontal spacing between grid items
        errorMessage.setPadding((new Insets(10)));

        GridPane.setConstraints(userLabel,0,0);
        GridPane.setConstraints(userInput,1,0);
        GridPane.setConstraints(passwordLabel,0,1);
        GridPane.setConstraints(passwordField,1,1);
        GridPane.setConstraints(loginButton,1,2);




        StringProperty passwordFieldProperty = passwordField.textProperty();
        //errorMessage.textProperty().bind(passwordFieldProperty);
        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldValue, String newValue) {
                   loginButton.setVisible(loginIsValidPassword(newValue));

            }

        });
        loginButton.setVisible(false);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (loginIsValidPassword(passwordField.getText()) && passwordField.getText().equalsIgnoreCase("p@ssword123"))
                {
                    MainWindow mainWindow = new MainWindow(userInput.getText());
                    window.close();
                }
                else
                {
                    errorMessage.setText("wrong password");
                }
            }
        });

        gridPane.getChildren().addAll(userLabel,userInput,passwordLabel,passwordField,loginButton,errorMessage);
        container.getChildren().addAll(gridPane,errorMessage);

        Scene scene = new Scene(container);
        window.setScene(scene);
        window.show();
    }

     private Boolean loginIsValidPassword(String password){
        boolean hasLetters = false;
        boolean hasDigits = false;
        boolean hasSpecialCharacters = false;

        for(char c : password.toCharArray())
        {
            if(Character.isDigit(c))
            {
                hasDigits = true;
            }
            else if(Character.isLetter(c))
            {
                 hasLetters = true;
            }
            else {
                hasSpecialCharacters = true;
            }

        }
        return password.length()>=8 &&(hasSpecialCharacters && hasLetters && hasDigits);
    }
}
