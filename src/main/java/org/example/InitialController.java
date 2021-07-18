package main.java.org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class InitialController {

    @FXML
    private Button btnCity;

    @FXML
    private Button btnArtist;

    @FXML
    private Button btnEventType;

    @FXML
    private Button btnEvent;

    @FXML
    private void switchToCity() throws IOException {
        App.setRoot("/main/resources/org/example/city");
    }

    @FXML
    public void switchToArtist() throws IOException {
        App.setRoot("/main/resources/org/example/artist");
    }

    public void switchToEventType() throws IOException {
        App.setRoot("/main/resources/org/example/eventtype");
    }

    public void switchToEvent() throws IOException {
        App.setRoot("/main/resources/org/example/event");
    }
}
