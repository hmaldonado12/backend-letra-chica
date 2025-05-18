package com.app.letrachica.infra.rest;

import com.app.letrachica.core.gateway.RegisterUserRepository;

public class RegisterUserInGoogleRepository implements RegisterUserRepository {

    @Override
    public void registerUser(String userId, String name, String email, String password, String phoneNumber, String address) {
        // Implement the logic to register the user in Google
        // This could involve making an API call to Google's user management service
        // For example:
        // googleApiClient.registerUser(userId, name, email, password, phoneNumber, address);
    }
}
