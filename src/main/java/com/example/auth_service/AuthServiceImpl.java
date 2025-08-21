package com.example.auth_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthServiceImpl implements AuthService {

	private final UserDAO userDao;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	  private final AuthenticationManager authManager;


	@Override
	public void register(RegisterRequestDTO request) {
		log.debug("Registering user: {}", request.getUsername());

		if (userDao.existsByUsername(request.getUsername())) {
			throw new IllegalArgumentException("Username already taken");
		}

		String encodedPassword = passwordEncoder.encode(request.getPassword());

		String role = request.getRole().startsWith("ROLE_") ? request.getRole().toUpperCase()
				: "ROLE_" + request.getRole().toUpperCase();

		User user = new User(null, request.getUsername(), encodedPassword, role);

		userDao.save(user);

		log.info("User registered successfully: {}", request.getUsername());
	}

	@Override
	public String login(LoginRequestDTO request) {
	    UsernamePasswordAuthenticationToken authToken =
	            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
	    org.springframework.security.core.Authentication auth = authManager.authenticate(authToken);
	    SecurityContextHolder.getContext().setAuthentication(auth);
	    User userDetails = (User) auth.getPrincipal();
	    String token = jwtUtil.generateToken(userDetails.getUsername());
	    return token;
	}


}
