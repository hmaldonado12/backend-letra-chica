package com.app.letrachica.core.usecase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.letrachica.core.gateway.RegisterUserRepository;

@Service
public class CreateUser {

        private final RegisterUserRepository registerUserRepository;

        @Autowired
        public CreateUser(RegisterUserRepository registerUserRepository) {
                this.registerUserRepository = registerUserRepository;
        }

        public void execute(String userId, String name, String email, String password, String phoneNumber, String address) {
                registerUserRepository.registerUser(userId, name, email, password, phoneNumber, address);
        }
}
