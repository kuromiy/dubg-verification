package com.dubg.verification.datasource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dubg.verification.model.User;
import com.dubg.verification.repository.UserRepository;

@Repository
public class UserMocksource implements UserRepository {
	private final List<User> userList;

	public UserMocksource() {
		this.userList = Arrays.asList(new User("1", "test", "test@example.com", "test"));
	}

	@Override
	public Optional<User> findByUserMail(String userMail) {
		Optional<User> foundUser = this.userList.stream()
				.filter((value) -> value.getUserMail().equals(userMail))
				.findFirst();
		return foundUser;
	}

}
