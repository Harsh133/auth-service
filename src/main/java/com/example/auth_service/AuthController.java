package com.example.auth_service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
@Validated
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDTO request) {
		log.info("Received registration request for username: {}", request.getUsername());
		authService.register(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/login")
	public String login(@Valid @RequestBody LoginRequestDTO request) {
		log.info("Authentication attempt for username: {}", request.getUsername());
		String token = authService.login(request);
		return token;
	}
	
	

}
