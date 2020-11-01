package com.dubg.verification.datasource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.dubg.verification.model.User;
import com.dubg.verification.repository.UserRepository;

@Repository
public class UserMocksource implements UserRepository {
	private List<User> userList;

	public UserMocksource() {
		// this.passwordEncoder = new BCryptPasswordEncoder();
		this.userList = Arrays.asList(
			new User("1", "test", "test@example.com", "test")
		);
	}

	@Override
	public Optional<User> findByUserMail(String userMail) {
		return this.userList.stream()
				.filter((value) -> value.getUserMail().equals(userMail))
				.findFirst();
	}
}
