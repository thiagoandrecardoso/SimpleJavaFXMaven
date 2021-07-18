package main.java.org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import main.java.org.example.dao.CitiesDAO;
import main.java.org.example.model.Cities;

import java.io.IOException;
import java.util.Optional;

public class CityController {

    Cities cities = null;

    @FXML
    private TextField txtCity;

    @FXML
    private void btnSaveCity() throws IOException {
        CitiesDAO citiesDAO = new CitiesDAO();
        cities = new Cities();
        cities.setName(txtCity.getText());
        cities = citiesDAO.save(cities);
        String inf = "Salvo com id " + cities.getId();
        informationAction(inf);
        App.setRoot("/main/resources/org/example/initial");
    }

    public void btnSearch() {
        TextInputDialog dialog = new TextInputDialog("id");
        dialog.setHeaderText("Digite o id da cidade:");
        Optional<String> result = dialog.showAndWait();
        Long id = Long.parseLong(result.get());

        CitiesDAO citiesDAO = new CitiesDAO();
        cities = citiesDAO.findById(id);
        txtCity.setText(cities.getName());
    }

    public void btnDelete() {
        CitiesDAO citiesDAO = new CitiesDAO();
        if(cities != null){
            citiesDAO.delete(cities.getId());
            txtCity.setText("");
        }
    }

    public void btnUpdate() {
        CitiesDAO citiesDAO = new CitiesDAO();
        if(cities != null){
            System.out.println(cities.getId());
            citiesDAO.update(cities);
        }
    }

    public void informationAction(String inf){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(inf);
        alert.showAndWait();
    }
}