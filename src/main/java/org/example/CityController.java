package main.java.org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CityController {

    @FXML
    private TextField txtCity;

    @FXML
    private Button btnSaveCity;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnDelete;


    @FXML
    private void saveCity() throws IOException {
        App.setRoot("/main/resources/org/example/initial");
    }
}