package com.dubg.verification.model;

import java.util.Arrays;

import org.springframework.security.core.userdetails.User;

import lombok.Getter;

public class LoginUser extends User {
	private static final long serialVersionUID = 1L;

	@Getter
	private final com.dubg.verification.model.User user;

	public LoginUser(com.dubg.verification.model.User user) {
		super(user.getUserName(), user.getUserPass(), Arrays.asList());
		this.user = user;
		System.out.println("LoginUser init");
	}
}
