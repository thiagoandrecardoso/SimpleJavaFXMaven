package main.java.org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.org.example.dao.CitiesDAO;
import main.java.org.example.model.Cities;

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
        CitiesDAO citiesDAO = new CitiesDAO();
        Cities cities = new Cities();
        cities.setName(txtCity.getText());
        cities.setEvents(null);
        citiesDAO.save(cities);
        App.setRoot("/main/resources/org/example/initial");
    }
}