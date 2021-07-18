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
import main.java.org.example.dao.EventsDAO;
import main.java.org.example.model.Artist;
import main.java.org.example.model.Cities;
import main.java.org.example.model.EventType;
import main.java.org.example.model.Events;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventController implements Initializable {

    Events events = null;


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
        EventsDAO eventsDAO = new EventsDAO();
        events = new Events();
        events.setName(txtCity.getText());

        Cities cities = cbbCities.getSelectionModel().getSelectedItem();
        events.setId_city(Math.toIntExact(cities.getId()));
        Artist artist = cbbArtist.getSelectionModel().getSelectedItem();
        events.setId_artist(Math.toIntExact(artist.getId()));
        EventType eventType = cbbEventType.getSelectionModel().getSelectedItem();
        events.setId_eventType(Math.toIntExact(eventType.getId()));

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = dateFormat.parse(txtDate.getText());
            events.setDateEvent(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        events = eventsDAO.save(events);
        String inf = "Salvo com id " + events.getId();
        informationAction(inf);
    }

    public void btnSearch() {
        TextInputDialog dialog = new TextInputDialog("id");
        dialog.setHeaderText("Digite o id da cidade:");
        Optional<String> result = dialog.showAndWait();
        Long id = Long.parseLong(result.get());

        CitiesDAO citiesDAO = new CitiesDAO();
        ArtistDAO artistDAO = new ArtistDAO();
        EventTypeDAO eventTypeDAO = new EventTypeDAO();

        EventsDAO eventsDAO = new EventsDAO();
        events = eventsDAO.findById(id);
        if (events != null){
            txtCity.setText(events.getName());
            cbbCities.getSelectionModel().select(citiesDAO.findById((long) events.getId_city()));
            cbbArtist.getSelectionModel().select(artistDAO.findById((long) events.getId_artist()));
            cbbEventType.getSelectionModel().select(eventTypeDAO.findById((long) events.getId_eventType()));
        }

    }

    public void btnDelete() {
        EventsDAO eventsDAO = new EventsDAO();
        if (events != null) {
            eventsDAO.delete(events.getId());
            txtCity.setText("");
        }
    }

    public void btnUpdate() {
        EventsDAO eventsDAO = new EventsDAO();
        if (events != null) {
            events.setName(txtCity.getText());
            Cities cities = cbbCities.getSelectionModel().getSelectedItem();
            events.setId_city(Math.toIntExact(cities.getId()));
            Artist artist = cbbArtist.getSelectionModel().getSelectedItem();
            events.setId_artist(Math.toIntExact(artist.getId()));
            EventType eventType = cbbEventType.getSelectionModel().getSelectedItem();
            events.setId_eventType(Math.toIntExact(eventType.getId()));
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date date = dateFormat.parse(txtDate.getText());
                events.setDateEvent(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            eventsDAO.update(events);
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