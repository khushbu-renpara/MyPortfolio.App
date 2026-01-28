package com.example.myportfolio;

public class Certificate {

    String title, description, year, imageUri;

    public Certificate(String title, String description, String year, String imageUri) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.imageUri = imageUri;
    }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public String getYear() { return year; }

    public String getImageUri() { return imageUri; }
}
