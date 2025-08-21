package com.example.auth_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Enable Mockito annotations
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserDAO userRepository; // Fake repository

    @InjectMocks
    private AuthServicee authService; // Class under test

    @Test
    void testRegisterUser() {
        // Prepare input
        User user = new User(null, "Prem", null, null);

        // Define behavior of mock
        when(userRepository.save(user)).thenReturn(user);

        // Call method under test
        User savedUser = authService.registerUser(user);

        // Verify result
        assertEquals("Prem", savedUser.getUsername());
    }
}
