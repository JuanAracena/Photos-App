package com.example.photos.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

import com.example.photos.PhotoApp;
import com.example.photos.models.User;

public class AdminController {
    private PhotoApp photoApp;

    public void setPhotoApp(PhotoApp photoApp) {
        this.photoApp = photoApp;
        updateUserList();
    }

    @FXML
    private TextField newUserTextField;

    @FXML
    private Button createUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private ListView<String> userList;

    @FXML
    private void handleCreateUserButtonAction() {
        String newUser = newUserTextField.getText();
        if (newUser.isEmpty()) {
            // Show an error dialog if the user name is empty
            return;
        }

        if (photoApp.createUser(newUser)) {
            userList.getItems().add(newUser);
            newUserTextField.clear();
        } else {
            // Show an error dialog if the user name already exists
        }
    }

    @FXML
    private void handleDeleteUserButtonAction() {
        String selectedUser = userList.getSelectionModel().getSelectedItem();
        if (photoApp.deleteUser(selectedUser)) {
            userList.getItems().remove(selectedUser);
        } else {
            // Show an error dialog if the user deletion failed
        }
    }

    private void updateUserList() {
        userList.getItems().clear();
        for (User user : photoApp.getUsers()) {
            userList.getItems().add(user.getUsername());
        }
    }
}
