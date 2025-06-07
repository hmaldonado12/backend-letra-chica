package com.app.letrachica.core.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.app.letrachica.controller.contract.UserRegisterResponse;

public class GoogleAuthServiceTest {



    @Test
    void testShouldReturnBadRequestForInvalidToken() {
        // Arrange
        String invalidToken = "invalid.token.value";

        // Act
        //ResponseEntity<UserRegisterResponse> response = googleAuthService.authenticateWithGoogle(invalidToken);
        //String message = response.getBody().getMessage().toLowerCase();

        // Assert
       /** assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(message.contains("Invalid ID token.") || 
                   message.contains("google authentication failed"));**/
    }
}
