package com.demo.entrepreneur.service;

import java.util.Collection;

import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.model.User;

public interface UserService {

	void registerNewUser(UserDto user);

	Collection<User> getAllUsers();

	User getUserByLogin(String login);

	User updateUserByLogin(UserDto user);

	void deleteUserByLogin(String login);
}
