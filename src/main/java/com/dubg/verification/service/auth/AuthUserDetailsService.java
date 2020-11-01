package com.dubg.verification.service.auth;

import com.dubg.verification.model.LoginUser;
import com.dubg.verification.model.User;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dubg.verification.repository.UserRepository;

@Service("authUserDetailsService")
public class AuthUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	public AuthUserDetailsService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {
		System.out.println("UserDetails");
		System.out.println(userMail);
		LoginUser loginUser = this.userRepository.findByUserMail(userMail)
				.map(LoginUser::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		return loginUser;
	}
}
