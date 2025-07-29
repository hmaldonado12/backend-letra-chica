package com.app.letrachica.infra.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Value("${mobile.host}")
    private String portMobile;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        System.out.println("Configuring CORS for mobile host: " + portMobile);
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
             .allowedOrigins(portMobile.split(","))
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(false); // Cambiar a false cuando se permite *
            }
        };
    }
}