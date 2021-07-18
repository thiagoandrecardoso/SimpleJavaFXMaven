package main.java.org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import main.java.org.example.dao.ArtistDAO;
import main.java.org.example.model.Artist;

import java.io.IOException;
import java.util.Optional;

public class ArtistController {

    Artist artist = null;

    @FXML
    private TextField txtArtist;

    @FXML
    private TextField txtValue;

    @FXML
    private void btnSaveCity() throws IOException {
        ArtistDAO artistDAO = new ArtistDAO();
        artist = new Artist();
        artist.setName(txtArtist.getText());
        artist.setCache(Float.parseFloat(txtValue.getText()));
        artist = artistDAO.save(artist);
        String inf = "Salvo com id " + artist.getId();
        informationAction(inf);
    }

    public void btnSearch() {
        TextInputDialog dialog = new TextInputDialog("id");
        dialog.setHeaderText("Digite o id do artista:");
        Optional<String> result = dialog.showAndWait();
        Long id = Long.parseLong(result.get());

        ArtistDAO artistDAO = new ArtistDAO();
        artist = artistDAO.findById(id);
        if (artist!= null){
            txtArtist.setText(artist.getName());
            txtValue.setText(String.valueOf(artist.getCache()));
        }
    }

    public void btnDelete() {
        ArtistDAO artistDAO = new ArtistDAO();
        if(artist != null){
            artistDAO.delete(artist.getId());
            txtArtist.setText("");
            txtValue.setText("");
        }
    }

    public void btnUpdate() {
        ArtistDAO artistDAO = new ArtistDAO();
        if(artist != null){
            artist.setName(txtArtist.getText());
            artist.setCache(Float.parseFloat(txtValue.getText()));
            artistDAO.update(artist);
        }
    }

    public void informationAction(String inf){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(inf);
        alert.showAndWait();
    }

    public void backToMenu() throws IOException {
        App.setRoot("/main/resources/org/example/initial");
    }
}