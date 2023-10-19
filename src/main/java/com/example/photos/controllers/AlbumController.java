package com.example.photos.controllers;

import com.example.photos.models.Album;
import com.example.photos.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class AlbumController {
    @FXML
    private ListView<Album> albumsListView;
    @FXML
    private Button createAlbumButton;
    @FXML
    private Button renameAlbumButton;
    @FXML
    private Button deleteAlbumButton;
    @FXML
    private TextArea inputTextArea;

    private User user;

    public void setUser(User user) {
        this.user = user;
        albumsListView.setItems(user.getAlbums());
    }

    public void initialize() {
        createAlbumButton.setOnAction(event -> createAlbum());
        renameAlbumButton.setOnAction(event -> renameAlbum());
        deleteAlbumButton.setOnAction(event -> deleteAlbum());
    }

    private void createAlbum() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create Album");
        dialog.setHeaderText("Enter album name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            Album newAlbum = new Album(name);
            user.addAlbum(newAlbum);
        });
    }

    private void renameAlbum() {
        Album selectedAlbum = albumsListView.getSelectionModel().getSelectedItem();
        if (selectedAlbum != null) {
            TextInputDialog dialog = new TextInputDialog(selectedAlbum.getName());
            dialog.setTitle("Rename Album");
            dialog.setHeaderText("Enter new album name:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> selectedAlbum.setName(name));
        }
    }

    private void deleteAlbum() {
        Album selectedAlbum = albumsListView.getSelectionModel().getSelectedItem();
        if (selectedAlbum != null) {
            user.removeAlbum(selectedAlbum);
        }
    }
}
