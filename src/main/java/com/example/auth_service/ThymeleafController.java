package com.example.auth_service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Prem");
        return "hello"; 
    }
}
