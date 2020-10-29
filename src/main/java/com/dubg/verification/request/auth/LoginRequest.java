package com.dubg.verification.request.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public final class LoginRequest {
	private String userMail;
	private String userPassword;
}
