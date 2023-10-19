package com.example.photos.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Photo implements Serializable {
    private String filePath;
    private LocalDateTime dateTaken;
    private String caption;
    private HashMap<String, String> tags;

    public Photo(String filePath, LocalDateTime dateTaken) {
        this.filePath = filePath;
        this.dateTaken = dateTaken;
        this.caption = "";
        this.tags = new HashMap<>();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDateTime dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public HashMap<String, String> getTags() {
        return tags;
    }

    public void setTags(HashMap<String, String> tags) {
        this.tags = tags;
    }

    public void addTag(String tagName, String tagValue) {
        this.tags.put(tagName, tagValue);
    }

    public void removeTag(String tagName) {
        this.tags.remove(tagName);
    }

    public String getTagValue(String tagName) {
        return this.tags.get(tagName);
    }
}
