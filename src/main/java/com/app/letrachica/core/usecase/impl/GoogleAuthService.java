package com.app.letrachica.core.usecase.impl;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.letrachica.controller.contract.UserRegisterResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@Service
public class GoogleAuthService {
    
    private static final String CLIENT_ID = "889927933084-6o5i9bet4eemuovr7de4boa17a5gpkku.apps.googleusercontent.com";

    public ResponseEntity<UserRegisterResponse> authenticateWithGoogle(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                String name = (String) payload.get("name");

                return ResponseEntity.ok(new UserRegisterResponse("User authenticated successfully with Google. Name: " + name + ", Email: " + email));
            } else {
                return ResponseEntity.badRequest().body(new UserRegisterResponse("Invalid ID token."));
            }
                
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UserRegisterResponse("Google authentication failed: " + e.getMessage()));
        }
    }
}
