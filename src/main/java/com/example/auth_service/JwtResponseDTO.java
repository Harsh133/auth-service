package com.example.auth_service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JwtResponseDTO {
	public JwtResponseDTO(String token2) {
	this.token = token2;
	}

	private String token;
}
