package com.app.letrachica.controller.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.letrachica.controller.contract.UserRegisterResponse;
import com.app.letrachica.core.usecase.impl.GoogleAuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8080")
public class GoogleAuthController {

    private final GoogleAuthService googleAuthService;

    public GoogleAuthController(GoogleAuthService googleAuthService) {
        this.googleAuthService = googleAuthService;
    }

    @PostMapping("/google")
    public ResponseEntity<UserRegisterResponse> googleAuth(@RequestBody Map<String, String> body) {
        System.out.println("Google Auth Request Body: " + body);
        String idToken = body.get("idToken");
        return googleAuthService.authenticateWithGoogle(idToken);
    }
}
