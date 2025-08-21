package com.example.auth_service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestCacheService {

	private final UserDAO userDAO;

	public TestCacheService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User getUserByUsername(String username) {
		System.out.println("Fetching from DB for: " + username);
		return userDAO.findByUsername(username).orElse(null);
	}

	@CachePut(value = "userCache", key = "#username")
	public User refreshUserCache(String username) {
		System.out.println("Fetching fresh data from DB for: " + username);
		return userDAO.findByUsername(username).orElse(null);
	}

	@CacheEvict(value = "userCache", key = "#username")
	public void evictUserCache(String username) {
		System.out.println("Evicting cache for: " + username);
	}
}
