package com.demo.entrepreneur.model.mapping.populator.impl;

import org.springframework.stereotype.Component;

import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.model.User;
import com.demo.entrepreneur.model.mapping.populator.Populator;

@Component
public class UpdateUserPopulator implements Populator<UserDto, User> {

	@Override
	public User populateDataToEntity(UserDto data, User entity) {
		entity.setEmail(data.getEmail());
		entity.setFirstName(data.getFirstName());
		entity.setLastName(data.getLastName());
		entity.setPassword(data.getPassword());
		return entity;
	}

}
