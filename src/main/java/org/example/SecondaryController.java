package main.java.org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {

    public Button secondaryButton;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("/main/resources/org/example/primary");
    }
}