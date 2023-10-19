package com.example.photos.controllers;

// Import the necessary JavaFX components at the top of your UserViewController.java file
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

import com.example.photos.models.Album;
import com.example.photos.models.User;
public class UserViewController {
private User user;

    // Constructor
    public UserViewController(User user) {
        this.user = user;
    }

@FXML
private ListView<Album> albumListView;

@FXML
private void handleLogout() {
    // Load the login view and set it as the active scene
    mainApp.loadLoginView();
}
private void loadView(String fxmlFile, Consumer<Scene> sceneHandler) {
    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        sceneHandler.accept(scene);

    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
private void handleCreateAlbum() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Create Album");
    dialog.setHeaderText("Enter a name for the new album:");
    Optional<String> result = dialog.showAndWait();

    if (result.isPresent()) {
        String albumName = result.get();

        Album newAlbum = new Album(albumName);
        user.addAlbum(newAlbum);

        albumListView.getItems().add(newAlbum);
    }
}

@FXML
private void handleDeleteAlbum() {
    Album selectedAlbum = albumListView.getSelectionModel().getSelectedItem();
    if (selectedAlbum != null) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Album");
        alert.setHeaderText("Are you sure you want to delete the album '" + selectedAlbum.getName() + "'?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            user.removeAlbum(selectedAlbum);
            albumListView.getItems().remove(selectedAlbum);
        }
    }
}

@FXML
private void handleRenameAlbum() {
    Album selectedAlbum = albumListView.getSelectionModel().getSelectedItem();
    if (selectedAlbum != null) {
        TextInputDialog dialog = new TextInputDialog(selectedAlbum.getName());
        dialog.setTitle("Rename Album");
        dialog.setHeaderText("Enter a new name for the album:");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String newName = result.get();
            selectedAlbum.setName(newName);
            albumListView.refresh();
        }
    }
}

@FXML
private void handleOpenAlbum() {
    Album selectedAlbum = albumListView.getSelectionModel().getSelectedItem();
    if (selectedAlbum != null) {
        // Load the album view and set it as the active scene
        loadView("/com/example/photos/views/AlbumView.fxml", scene -> {
            AlbumController controller = loader.getController();
            controller.setAlbum(selectedAlbum);
            primaryStage.setScene(scene);
        });
        
    }
}
}