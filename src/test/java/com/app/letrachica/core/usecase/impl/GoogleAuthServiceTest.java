package com.app.letrachica.core.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.app.letrachica.controller.contract.UserRegisterResponse;

public class GoogleAuthServiceTest {

    private final GoogleAuthService googleAuthService = new GoogleAuthService();

    @Test
    void testShouldReturnBadRequestForInvalidToken() {
        // Arrange
        String invalidToken = "invalid.token.value";

        // Act
        ResponseEntity<UserRegisterResponse> response = googleAuthService.authenticateWithGoogle(invalidToken);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().getMessage().contains("Invalid ID token.")
            || response.getBody().getMessage().toLowerCase().contains("google authentication failed"));
    }
}
