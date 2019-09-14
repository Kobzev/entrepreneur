package com.demo.entrepreneur.rest;

import com.demo.entrepreneur.dto.UserDto;

public interface UserController {

	UserDto getUserByLogin(String login);

	Iterable<UserDto> getAllUsers();

	void createUser(UserDto user);

	UserDto updateUserByLogin(String login, UserDto user);

	void deleteUserByLogin(String login);
}
