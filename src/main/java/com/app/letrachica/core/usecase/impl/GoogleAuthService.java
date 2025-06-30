package com.app.letrachica.core.usecase.impl;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.letrachica.controller.contract.UserRegisterResponse;
import com.app.letrachica.core.domain.User;
import com.app.letrachica.core.gateway.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@Service
public class GoogleAuthService {
    
    // CLIENT_ID original (comentado por si es necesario después)
    // private static final String CLIENT_ID = "889927933084-6o5i9bet4eemuovr7de4boa17a5gpkku.apps.googleusercontent.com";
    
    // CLIENT_ID de Firebase (el que está generando los tokens) - CORREGIDO
    private static final String CLIENT_ID = "889927933084-c213n51cnolov6ec0rgo40579fstqahb.apps.googleusercontent.com";
    
    private final UserRepository userRepository;

    public GoogleAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

                User user = userRepository.findFirstByEmail(email)
                        .orElseGet(() -> {
                            User newUser = new User(name, email);
                            userRepository.save(newUser);
                            return newUser;
                        });

                System.out.println("User authenticated: " + user.getId());
                return ResponseEntity.ok(new UserRegisterResponse(user.getId()));
            } else {
                return ResponseEntity.badRequest().body(new UserRegisterResponse("Invalid ID token.", null));
            }
                
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UserRegisterResponse("Google authentication failed: " + e.getMessage(), null));
        }
    }
}
