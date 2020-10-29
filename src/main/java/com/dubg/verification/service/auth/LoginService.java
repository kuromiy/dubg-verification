package com.dubg.verification.service.auth;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.dubg.verification.repository.UserRepository;

@Service
public final class LoginService {
	private final UserRepository userRepository;
	private final HttpSession httpSession;

	public LoginService(final UserRepository userRepository, final HttpSession httpSession) {
		this.userRepository = userRepository;
		this.httpSession = httpSession;
	}

	public void execute() {
		
	}
}
