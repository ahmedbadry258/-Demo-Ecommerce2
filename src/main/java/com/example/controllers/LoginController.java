package com.example.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String loginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "login";
		}
		return "/cart/";
	}
		
	
	@GetMapping("/welcome")
	public String welcome() {
		System.out.println("Welcome Page");
		return "welcome";
	}
	@GetMapping("/403")
	public String error403() {
		System.out.println("403 Page");
		return "403";
	}

}
