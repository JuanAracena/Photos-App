package com.example.photos;

import java.util.ArrayList;

import com.example.photos.models.User;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PhotoApp extends Application {
    ArrayList<User> users = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Photo App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
