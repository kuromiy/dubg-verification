package com.dubg.verification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	private String userId;
	private String userName;
	private String userMail;
	private String userPassword;
}
