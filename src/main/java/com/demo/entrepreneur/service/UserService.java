package com.demo.entrepreneur.service;

import java.util.Collection;

import com.demo.entrepreneur.dto.RequestUserDto;
import com.demo.entrepreneur.entity.User;

public interface UserService {

	User registerNewUser(RequestUserDto user);

	Collection<User> getAllUsers();

	User getUserByLogin(String login);

	User updateUserByLogin(String login, RequestUserDto user);

	void deleteUserByLogin(String login);

	void confirmEmail(String token);
}
