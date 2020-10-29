package com.dubg.verification.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dubg.verification.request.auth.LoginRequest;
import com.dubg.verification.response.auth.LoginResponse;
import com.dubg.verification.service.auth.LoginService;

@RestController
public final class LoginController {
	private final LoginService loginService;

	public LoginController(final LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/auth/login")
	public LoginResponse login(final LoginRequest request) {
		return this.loginService.execute(request);
	}
}
