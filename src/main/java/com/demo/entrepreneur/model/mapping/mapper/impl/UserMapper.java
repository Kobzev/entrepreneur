package com.demo.entrepreneur.model.mapping.mapper.impl;

import org.springframework.stereotype.Component;

import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.model.User;
import com.demo.entrepreneur.model.mapping.mapper.Mapper;

@Component
public class UserMapper implements Mapper<UserDto, User>{

	@Override
	public User dataToEntity(UserDto data) {
		final User user = new User();
		user.setEmail(data.getEmail());
		user.setFirstName(data.getFirstName());
		user.setLastName(data.getLastName());
		user.setLogin(data.getLogin());
		user.setPassword(data.getPassword());
		return user;
	}

	@Override
	public UserDto entityToData(User entity) {
		return UserDto.builder()
				.email(entity.getEmail())
				.firstName(entity.getFirstName())
				.lastName(entity.getLastName())
				.login(entity.getLogin())
				.password(entity.getPassword())
				.build();
	}
	
}
