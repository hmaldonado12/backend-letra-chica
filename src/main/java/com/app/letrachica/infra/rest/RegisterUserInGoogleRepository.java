package com.app.letrachica.infra.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import com.app.letrachica.core.gateway.RegisterUserRepository;
import com.google.api.client.util.Value;

public class RegisterUserInGoogleRepository implements RegisterUserRepository {

    @Value("${google.identity.api.key}")
    private String apikey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void registerUser(String userId, String name, String email, String password, String phoneNumber, String address) {
        // Implement the logic to register the user in Google
        // This could involve making an API call to Google's user management service
        // For example:
        // googleApiClient.registerUser(userId, name, email, password, phoneNumber, address);
        
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + apikey;

        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("returnSecureToken", true);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error registering user in Google: " + response.getStatusCode());
        }
    }
}
