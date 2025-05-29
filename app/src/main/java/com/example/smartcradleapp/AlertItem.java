package com.example.smartcradleapp;

public class AlertItem {
    private String message;
    private String timestamp;

    public AlertItem() {
        // Default constructor required for Firebase
    }

    public AlertItem(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}