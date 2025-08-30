package com.app.letrachica.controller.contract;

public class UserLoginResponse {
    private String message;
    private String token;

    public UserLoginResponse() {}

    public UserLoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
