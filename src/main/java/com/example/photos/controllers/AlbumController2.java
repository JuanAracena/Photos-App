package com.example.photos.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

import com.example.photos.models.Album;
import com.example.photos.models.User;

public class AlbumController {
    private User user;

    public void setUser(User user) {
        this.user = user;
        updateAlbumList();
    }

    @FXML
    private TextField newAlbumTextField;

    @FXML
    private Button createAlbumButton;

    @FXML
    private Button deleteAlbumButton;

    @FXML
    private Button renameAlbumButton;

    @FXML
    private ListView<String> albumList;

    @FXML
    private void handleCreateAlbumButtonAction() {
        String newAlbum = newAlbumTextField.getText();
        if (newAlbum.isEmpty()) {
            // Show an error dialog if the album name is empty
            return;
        }

        if (user.addAlbum(newAlbum)) {
            albumList.getItems().add(newAlbum);
            newAlbumTextField.clear();
        } else {
            // Show an error dialog if the album name already exists
        }
    }

    @FXML
    private void handleDeleteAlbumButtonAction() {
        String selectedAlbum = albumList.getSelectionModel().getSelectedItem();
        if (user.removeAlbum(selectedAlbum)) {
            albumList.getItems().remove(selectedAlbum);
        } else {
            // Show an error dialog if the album deletion failed
        }
    }

    @FXML
    private void handleRenameAlbumButtonAction() {
        String selectedAlbum = albumList.getSelectionModel().getSelectedItem();
        TextInputDialog dialog = new TextInputDialog(selectedAlbum);
        dialog.setTitle("Rename Album");
        dialog.setHeaderText("Enter a new name for the album:");
        dialog.setContentText("New name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            if (user.renameAlbum(selectedAlbum, newName)) {
                albumList.getItems().set(albumList.getSelectionModel().getSelectedIndex(), newName);
            } else {
                // Show an error dialog if the album renaming failed
            }
        });
    }

    private void updateAlbumList() {
        albumList.getItems().clear();
        for (Album album : user.getAlbums()) {
            albumList.getItems().add(album.getName());
        }
    }
}
