package nl.inholland.javafx;

import Database.Database;
import Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MainWindow {
    private Stage window;
    public MainWindow(String username) {
        Database db = new Database();
        ObservableList<Person> people = FXCollections.observableArrayList(db.getPeople());
        window = new Stage();
       // window.setWidth(1024);
       // window.setHeight(800);
        window.setTitle("People manager");
        window.setMinWidth(300);

        TableView<Person> personTableView = new TableView<>();
        personTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        VBox box = new VBox();
        box.setPadding(new Insets(10));

        TextField firstNameInput = new TextField();
        firstNameInput.setPromptText("First name");
        TextField lastNameInput = new TextField();
        lastNameInput.setPromptText("Last name");
        DatePicker birthdateInput = new DatePicker();
        birthdateInput.setPromptText("Birth date");
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");

        HBox form = new HBox();
        form.setPadding(new Insets(10));
        form.setSpacing(10);
        form.getChildren()
                .addAll(firstNameInput, lastNameInput, birthdateInput, addButton, deleteButton);
        box.getChildren().add(personTableView);
        box.getChildren().add(form);

        TableColumn<Person, String> firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Person, String> birthDateColumn = new TableColumn<>("Birth date");
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        personTableView.setItems(people);
        personTableView.getColumns().addAll(firstNameColumn, lastNameColumn, birthDateColumn);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            // create a new person with the data from the input fields

                    Person person = new Person(firstNameInput.getText(),lastNameInput.getText(),birthdateInput.getValue());
                    // add the person to the list
                    people.add(person);
                    // clear the input fields. When done
                    firstNameInput.clear();
                    lastNameInput.clear();
                    birthdateInput.getEditor().clear();

            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<Person> selectedPeople =
                        personTableView.getSelectionModel().getSelectedItems();
                people.removeAll(selectedPeople);
            }
        });


// create the scene and show the window
        Scene scene = new Scene(box);
        window.setScene(scene);
        window.show();
    }
}
