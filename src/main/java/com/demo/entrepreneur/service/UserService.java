package com.demo.entrepreneur.service;

import java.util.Collection;

import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.model.User;

public interface UserService {

	User registerNewUser(UserDto user);

	Collection<User> getAllUsers();

	User getUserByLogin(String login);

	User updateUserByLogin(String login, UserDto user);

	void deleteUserByLogin(String login);
}
