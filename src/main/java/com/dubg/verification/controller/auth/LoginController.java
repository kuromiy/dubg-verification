package com.dubg.verification.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dubg.verification.service.auth.LoginService;

@RestController
public final class LoginController {
	private final LoginService loginService;

	public LoginController(final LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/auth/login")
	public String login() {
		throw new RuntimeException("Not Implements");
	}
}
