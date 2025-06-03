package com.app.letrachica.core.domain;

public class Category {
    private String id;
    private String name;
    private String email;
    private String userId;

    public Category(String name, String userId, String email) {
        this.id = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.userId = userId;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(String id) {
        return this.id.equals(id);
    }
}
