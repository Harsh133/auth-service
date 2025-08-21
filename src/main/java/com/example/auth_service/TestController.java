package com.example.auth_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/get")
	public String get() {
		return "yes";
	}
	
	   @GetMapping("/secure-data")
	    public String secureData() {
	        return "This is protected data, you are logged in!";
	    }
}
