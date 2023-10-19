package com.example.photos.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import com.example.photos.models.User;

public class AdminViewController {

    @FXML
    private ListView<User> userList;

    @FXML
    private TextField newUserTextField;

    
    private ObservableList<User> users = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Populate the user list with existing users
        userList.setItems(users);
    }

    @FXML
    void addUser(MouseEvent event) {
        String username = newUserTextField.getText().trim();
        if (!username.isEmpty()) {
            User newUser = new User(username);
            users.add(newUser);
            newUserTextField.clear();
        }
    }

    @FXML
    void deleteUser(MouseEvent event) {
        User selectedUser = userList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            users.remove(selectedUser);
        }
    }
}
