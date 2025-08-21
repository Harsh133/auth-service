package com.example.auth_service;

import org.springframework.stereotype.Service;

@Service
public class AuthServicee {

    private UserDAO userRepository = null;

    public AuthServicee(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }
    public String login(String username, String password) {
        if ("admin".equals(username) && "1234".equals(password)) {
            return "SUCCESS";
        }
        return "FAIL";
    }
}
