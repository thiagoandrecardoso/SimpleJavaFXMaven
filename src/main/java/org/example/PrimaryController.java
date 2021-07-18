package main.java.org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class PrimaryController {

    @FXML
    private Button btnCity;

    @FXML
    private Button btnArtist;

    @FXML
    private Button btnEventType;

    @FXML
    private Button btnEvent;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("/main/resources/org/example/secondary");
    }
}
