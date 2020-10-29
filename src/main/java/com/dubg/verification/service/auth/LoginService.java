package com.dubg.verification.service.auth;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.dubg.verification.model.User;
import com.dubg.verification.repository.UserRepository;
import com.dubg.verification.request.auth.LoginRequest;
import com.dubg.verification.response.auth.LoginResponse;

@Service
public final class LoginService {
	private final UserRepository userRepository;
	private final HttpSession httpSession;

	public LoginService(final UserRepository userRepository, final HttpSession httpSession) {
		this.userRepository = userRepository;
		this.httpSession = httpSession;
	}

	public LoginResponse execute(final LoginRequest request) {
		Optional<User> foundUser = this.userRepository.findByUserMail(request.getUserMail());
		User user = foundUser.get(); // TODO 存在しない場合処理実装すること

		boolean result = user.getUserPassword().equals(request.getUserPassword());
		if (!result) {
			// TODO パスワードが異なる場合処理実装すること
		}
		this.httpSession.setAttribute("userId", user.getUserId());

		return new LoginResponse();
	}
}
