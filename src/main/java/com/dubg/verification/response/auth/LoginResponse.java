package com.dubg.verification.response.auth;

import lombok.Data;

@Data
public final class LoginResponse {
	private final String message;

	public LoginResponse() {
		this.message = "success";
	}
}
