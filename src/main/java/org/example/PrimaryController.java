package main.java.org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PrimaryController {

    public Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("/main/resources/org/example/secondary");
    }
}
