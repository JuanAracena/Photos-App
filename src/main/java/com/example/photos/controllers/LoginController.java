package com.example.photos.controllers;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField usernameTextField;

    public void handleLoginButtonAction() {
        String username = usernameTextField.getText();
        if (username.equals("admin")) {
            loadAdminView();
        } else {
            loadUserView(username);
        }
    }

    private void loadAdminView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AdminView.fxml"));
            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUserView(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserView.fxml"));
            Parent root = loader.load();
            UserViewController controller = loader.getController();
            controller.initUser(username);
            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
