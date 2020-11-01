package com.dubg.verification.service.auth;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public final class LogoutService {
	private final HttpSession httpSession;

	public LogoutService(final HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	public void execute() {
		this.httpSession.invalidate();
	}
}
