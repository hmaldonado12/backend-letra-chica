package com.app.letrachica.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.letrachica.core.gateway.dto.UserRegisterRequest;
import com.app.letrachica.controller.contract.UserRegisterResponse;
import com.app.letrachica.core.usecase.impl.CreateUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class CreateUserController {
    
    private final CreateUser createUser;

    public CreateUserController(CreateUser createUser) {
        this.createUser = createUser;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        try {
            createUser.execute(
                null,
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getAddress()
            );
            return ResponseEntity.ok(new UserRegisterResponse("User registered successfully.", ""));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UserRegisterResponse("User registration failed: " + e.getMessage(), null));
        }
    }
}
