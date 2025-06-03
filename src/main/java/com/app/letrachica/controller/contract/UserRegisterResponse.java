package com.app.letrachica.controller.contract;

public class UserRegisterResponse {
    private String message;
    private String userId;

    public UserRegisterResponse() {}

    public UserRegisterResponse(String message, String id) {
        this.message = message;
        this.userId = id;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}
