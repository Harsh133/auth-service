package com.example.auth_service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AuthServiceApplicationTests {

    @Autowired
    private AuthServicee authService; // Inject the service bean

    @Test
    void contextLoads() {
        // Checks if the Spring context loads correctly
    }

    @Test
    void testLoginSuccess() {
        String result = authService.login("admin", "1234");
        assertEquals("SUCCESS", result);
    }

    @Test
    void testLoginFail() {
        String result = authService.login("user", "wrong");
        assertEquals("FAIL", result);
    }
}

