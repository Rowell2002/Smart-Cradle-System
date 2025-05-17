package com.example.smartcradleapp;

public class AlertModel {
    private String title;
    private String message;

    public AlertModel() {} // Needed for Firebase

    public AlertModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() { return title; }
    public String getMessage() { return message; }
}