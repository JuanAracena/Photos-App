package com.example.photos.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import com.example.photos.models.Album;
import com.example.photos.models.Photo;

public class PhotoController {
    private Album album;
    private List<Album> allAlbums;

    public void setAlbum(Album album, List<Album> allAlbums) {
        this.album = album;
        this.allAlbums = allAlbums;
        updatePhotoList();
    }

    @FXML
    private Button addPhotoButton, removePhotoButton, captionPhotoButton, addTagButton, deleteTagButton, copyPhotoButton;

    @FXML
    private ListView<String> photoList;

    @FXML
    private ImageView photodisplay_imageview;

    @FXML
    private Label photoCaptionLabel, photoDateLabel, photoTagsLabel;

    @FXML
    private TextField captionTextField, tagNameTextField, tagValueTextField;

    @FXML
    private ComboBox<String> albumComboBox;

    @FXML
    private void initialize() {
        for (Album album : allAlbums) {
            albumComboBox.getItems().add(album.getName());
        }
    }

    // Add a photo
    @FXML
    private void handleAddPhotoButtonAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Photo newPhoto = new Photo(file.toURI().toString(), LocalDateTime.now());
            album.addPhoto(newPhoto);
            photoList.getItems().add(newPhoto.getCaption());
        }
    }

    // Remove a photo
    @FXML
    private void handleRemovePhotoButtonAction() {
        int selectedIndex = photoList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            album.deletePhoto(selectedIndex);
            photoList.getItems().remove(selectedIndex);
            clearPhotoDetails();
        }
    }

    // Caption/recaption a photo
    @FXML
    private void handleCaptionPhotoButtonAction() {
        int selectedIndex = photoList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Photo selectedPhoto = album.getPhoto(selectedIndex);
            selectedPhoto.setCaption(captionTextField.getText());
            photoList.getItems().set(selectedIndex, selectedPhoto.getCaption());
            photoCaptionLabel.setText(selectedPhoto.getCaption());
        }
    }

    // Add a tag to a photo
    @FXML
    private void handleAddTagButtonAction() {
        int selectedIndex = photoList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Photo selectedPhoto = album.getPhoto(selectedIndex);
            selectedPhoto.addTag(tagNameTextField.getText(), tagValueTextField.getText());
            photoTagsLabel.setText(selectedPhoto.getTags().toString());
        }
    }

    // Delete a tag from a photo
    @FXML
    private void handleDeleteTagButtonAction() {
        int selectedIndex = photoList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Photo selectedPhoto = album.getPhoto(selectedIndex);
            selectedPhoto.deleteTag(tagNameTextField.getText());
            photoTagsLabel.setText(selectedPhoto.getTags().toString());
        }
    }

    // Copy a photo from one album to another
    @FXML
    private void handleCopyPhotoButtonAction() {
        int selectedIndex = photoList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Photo selectedPhoto = album.getPhoto(selectedIndex);
            String targetAlbumName = albumComboBox.getValue();
            if (targetAlbumName != null) {
                for (Album targetAlbum : allAlbums) {
                    if (targetAlbum.getName().equals(targetAlbumName)) {
                        targetAlbum.addPhoto(selectedPhoto);
                        break;
                    }
                }
            }
        }
    }

    // Update the photo list in the UI
    private void updatePhotoList() {
        photoList.getItems().clear();
        for (Photo photo : album.getPhotos()) {
            photoList.getItems().add(photo.getCaption());
        }

        photoList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int selectedIndex = photoList.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Photo selectedPhoto = album.getPhoto(selectedIndex);
                Image image = new Image(selectedPhoto.getPath());
                photoImageView.setImage(image);
                photoCaptionLabel.setText(selectedPhoto.getCaption());
                photoDateLabel.setText(selectedPhoto.getDate().toString());
                photoTagsLabel.setText(selectedPhoto.getTags().toString());
            } else {
                clearPhotoDetails();
            }
        });
    }

    // Clear photo details when no photo is selected
    private void clearPhotoDetails() {
        photoImageView.setImage(null);
        photoCaptionLabel.setText("");
        photoDateLabel.setText("");
        photoTagsLabel.setText("");
    }
}

