package com.demo.entrepreneur.service;

import com.demo.entrepreneur.dto.user.RequestUserDto;
import com.demo.entrepreneur.entity.User;

import java.util.Collection;

public interface UserService {

	User registerNewUser(RequestUserDto user);

	Collection<User> getAllUsers();

	User getUserByLogin(String login);

	User updateUserByLogin(String login, RequestUserDto user);

	void deleteUserByLogin(String login);
}
