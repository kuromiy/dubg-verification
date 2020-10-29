package com.dubg.verification.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dubg.verification.service.auth.LogoutService;

@RestController
public final class LogoutController {
	private final LogoutService logoutService;

	public LogoutController(final LogoutService logoutService) {
		this.logoutService = logoutService;
	}

	@PostMapping("/auth/logout")
	public String logout() {
		throw new RuntimeException("Not Implements");
	}
}
