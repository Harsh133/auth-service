package com.example.auth_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestCacheController {

    private final TestCacheService testCacheService;

    // Get cached user (first time hits DB, then cache)
    // URL: http://localhost:8080/user?username=Aravind
    @GetMapping("/user")
    public User getUser(@RequestParam String username) {
        return testCacheService.getUserByUsername(username);
    }

    // Force refresh cache (always hits DB)
    // URL: http://localhost:8080/user/refresh?username=Aravind
    @GetMapping("/user/refresh")
    public User refreshUser(@RequestParam String username) {
        return testCacheService.refreshUserCache(username);
    }

    // Evict cache (clear cache for this user)
    // URL: http://localhost:8080/user/evict?username=Aravind
    @GetMapping("/user/evict")
    public String evictUser(@RequestParam String username) {
        testCacheService.evictUserCache(username);
        return "Cache evicted for " + username;
    }
}
