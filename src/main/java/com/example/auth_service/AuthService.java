package com.example.auth_service;

public interface AuthService {

    void register(RegisterRequestDTO request);
    
    String login(LoginRequestDTO request);
     
}
