package nl.inholland.javafx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class App extends Application implements MouseListener {

    PasswordField passwordField;
    Button loginButton;
    String passwordText;

    @Override
    public void start(Stage window) throws Exception {

        window.setHeight(200);
        window.setWidth(400);
        window.setTitle("Inholland JavaFX Starter Project");


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10); // Vertical spacing between grid items
        gridPane.setHgap(8); // Horizontal spacing between grid items
        loginButton = new Button();
       loginButton.setText("Log in");
        gridPane.add(loginButton, 2, 3);


        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        gridPane.add(usernameLabel, 1, 1);
        gridPane.add(passwordLabel, 1, 2);

        TextField usernameInput = new TextField();
        passwordField = new PasswordField();
        passwordText = passwordField.getText();
        gridPane.add(usernameInput, 2, 1);
        gridPane.add(passwordField, 2, 2);

        loginButton.setDisable(true);
        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();

        passwordField.getOnMouseClicked();
    }

    static boolean correctPassowrdLength(PasswordField passwordField) {
        return passwordField.getLength() < 8;
    }

    static boolean passowrdContainsLetter(String passwordText) {
        Pattern letter = Pattern.compile("(?=.*[A-Za-z])");
        Matcher matcher = letter.matcher(passwordText);
        return matcher.matches();
    }

    static boolean passowrdContainsSpecialCharacter(String passwordText) {
        Pattern specialChar = Pattern.compile("(?=.*[@#$%^&-+=()])");
        Matcher matcher = specialChar.matcher(passwordText);
        return matcher.matches();
    }

    static boolean passowrdContainsNumber(String passwordText) {
        Pattern number = Pattern.compile("(?=.*[0-9]) ");
        Matcher matcher = number.matcher(passwordText);
        return matcher.matches();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (correctPassowrdLength(passwordField) && passowrdContainsLetter(passwordText) && passowrdContainsSpecialCharacter(passwordText) && passowrdContainsNumber(passwordText)) {
            loginButton.setDisable(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
