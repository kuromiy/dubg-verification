package com.dubg.verification.repository;

import java.util.Optional;

import com.dubg.verification.model.User;

public interface UserRepository {
	public Optional<User> findByUserMail(String userMail);
}
