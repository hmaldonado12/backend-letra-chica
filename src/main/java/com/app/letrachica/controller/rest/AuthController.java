package com.app.letrachica.controller.rest;

import com.app.letrachica.controller.contract.UserLoginResponse;
import com.app.letrachica.controller.contract.UserRegisterResponse;
import com.app.letrachica.core.domain.User;
import com.app.letrachica.core.gateway.UserRepository;
import com.app.letrachica.core.usecase.impl.JwtService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private JwtService jwtService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        if (userRepository.findFirstByEmail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new UserRegisterResponse("El usuario ya está registrado", null));
        }

        String passwordHash = passwordEncoder.encode(password);
        User user = new User(body.get("name"), email);
        user.setPasswordHash(passwordHash);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserRegisterResponse("Usuario registrado exitosamente", user.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || !passwordEncoder.matches(password, user.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserLoginResponse("Credenciales inválidas", null));
        }
        String token = jwtService.generateToken(user.getId(), user.getEmail());
        return ResponseEntity.ok(new UserLoginResponse("Inicio de sesión exitoso", token));
    }
}
