package com.java.projectfinal.model.payload;

public record ApiResponse(boolean success ,String message) {

    public boolean success() {
        return success;
    }

    public String message() {
        return message;
    }
}
