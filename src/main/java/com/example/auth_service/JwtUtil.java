package com.example.auth_service;

import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final String secretKey = "MY_SUPER_SECRET_KEY_32_BYTES_MINIMUM_123!";
	private final long expirationTime = 1000 * 60 * 60;

	@SuppressWarnings("unused")
	private Key getKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(getKey(), SignatureAlgorithm.HS256).compact();
	}

	public String extractUsername(String token) throws java.io.IOException {

		return parseToken(token).getBody().getSubject();

	}

	public boolean validateToken(String token) throws java.io.IOException {
		try {
			parseToken(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}

	private Jws<Claims> parseToken(String token) throws java.io.IOException {
			return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
	
	}

}
