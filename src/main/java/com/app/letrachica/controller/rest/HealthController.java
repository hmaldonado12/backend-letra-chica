package com.app.letrachica.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/")
    public String root() {
        return "Letra Chica backend running";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
