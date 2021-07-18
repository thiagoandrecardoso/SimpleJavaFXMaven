package main.java.org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import main.java.org.example.dao.EventTypeDAO;
import main.java.org.example.model.EventType;

import java.io.IOException;
import java.util.Optional;

public class EventTypeController {

    EventType eventType = null;

    @FXML
    private TextField txtEventType;

    @FXML
    private void btnSaveCity() throws IOException {
        EventTypeDAO eventTypeDAO = new EventTypeDAO();
        eventType = new EventType();
        eventType.setName(txtEventType.getText());
        eventType = eventTypeDAO.save(eventType);
        String inf = "Salvo com id " + eventType.getId();
        informationAction(inf);
    }

    public void btnSearch() {
        TextInputDialog dialog = new TextInputDialog("id");
        dialog.setHeaderText("Digite o id da cidade:");
        Optional<String> result = dialog.showAndWait();
        Long id = Long.parseLong(result.get());

        EventTypeDAO eventTypeDAO = new EventTypeDAO();
        eventType = eventTypeDAO.findById(id);
        if (eventType != null)
            txtEventType.setText(eventType.getName());
    }

    public void btnDelete() {
        EventTypeDAO eventTypeDAO = new EventTypeDAO();
        if (eventType != null) {
            eventTypeDAO.delete(eventType.getId());
            txtEventType.setText("");
        }
    }

    public void btnUpdate() {
        EventTypeDAO eventTypeDAO = new EventTypeDAO();
        if (eventType != null) {
            eventType.setName(txtEventType.getText());
            eventTypeDAO.update(eventType);
        }
    }

    public void informationAction(String inf) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(inf);
        alert.showAndWait();
    }

    public void backToMenu() throws IOException {
        App.setRoot("/main/resources/org/example/initial");
    }
}