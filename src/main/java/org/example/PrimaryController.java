package main.java.org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.java.org.example.App;

public class PrimaryController {

    public Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("/main/resources/org/example/secondary");
    }
}
