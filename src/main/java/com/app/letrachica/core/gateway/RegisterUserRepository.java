package com.app.letrachica.core.gateway;

public interface RegisterUserRepository {
    void registerUser(String userId, String name, String email, String password, String phoneNumber, String address);
}
