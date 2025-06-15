package com.app.letrachica.core.gateway.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String name;
    private String phoneNumber;
    private String address;

}
