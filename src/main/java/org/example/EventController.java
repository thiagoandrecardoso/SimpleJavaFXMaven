package main.java.org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import main.java.org.example.dao.ArtistDAO;
import main.java.org.example.dao.CitiesDAO;
import main.java.org.example.dao.EventTypeDAO;
import main.java.org.example.model.Artist;
import main.java.org.example.model.Cities;
import main.java.org.example.model.EventType;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventController implements Initializable {

    Cities cities = null;


    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtDate;

    @FXML
    private ComboBox<Cities> cbbCities;

    @FXML
    private ComboBox<Artist> cbbArtist;

    @FXML
    private ComboBox<EventType> cbbEventType;

    @FXML
    private void btnSaveCity() {
        CitiesDAO citiesDAO = new CitiesDAO();
        cities = new Cities();
        cities.setName(txtCity.getText());
        cities = citiesDAO.save(cities);
        String inf = "Salvo com id " + cities.getId();
        informationAction(inf);
    }

    public void btnSearch() {
        TextInputDialog dialog = new TextInputDialog("id");
        dialog.setHeaderText("Digite o id da cidade:");
        Optional<String> result = dialog.showAndWait();
        Long id = Long.parseLong(result.get());

        CitiesDAO citiesDAO = new CitiesDAO();
        cities = citiesDAO.findById(id);
        if (cities != null)
            txtCity.setText(cities.getName());
    }

    public void btnDelete() {
        CitiesDAO citiesDAO = new CitiesDAO();
        if (cities != null) {
            citiesDAO.delete(cities.getId());
            txtCity.setText("");
        }
    }

    public void btnUpdate() {
        CitiesDAO citiesDAO = new CitiesDAO();
        if (cities != null) {
            cities.setName(txtCity.getText());
            citiesDAO.update(cities);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCbbCities();
        setCbbArtist();
        setCbbEventType();
    }

    public void setCbbCities() {
        try {
            CitiesDAO citiesDAO = new CitiesDAO();
            ObservableList<Cities> citiesObservableList = FXCollections.observableArrayList(citiesDAO.getList());
            if (!citiesObservableList.isEmpty()) {
                cbbCities.setItems(citiesObservableList);
            }
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
    }

    public void setCbbArtist() {
        try {
            ArtistDAO artistDAO = new ArtistDAO();
            ObservableList<Artist> artistObservableList = FXCollections.observableArrayList(artistDAO.getList());
            if (!artistObservableList.isEmpty()) {
                cbbArtist.setItems(artistObservableList);
            }
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
    }

    public void setCbbEventType() {
        try {
            EventTypeDAO eventTypeDAO = new EventTypeDAO();
            ObservableList<EventType> eventTypeObservableList = FXCollections.observableArrayList(eventTypeDAO.getList());
            if (!eventTypeObservableList.isEmpty()) {
                cbbEventType.setItems(eventTypeObservableList);
            }
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
    }
}