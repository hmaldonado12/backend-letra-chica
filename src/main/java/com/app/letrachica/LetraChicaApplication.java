package com.app.letrachica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LetraChicaApplication {

    public static void main(String[] args) {
        System.out.println("ENV PORT = " + System.getenv("PORT"));
        SpringApplication.run(LetraChicaApplication.class, args);
    }

}
