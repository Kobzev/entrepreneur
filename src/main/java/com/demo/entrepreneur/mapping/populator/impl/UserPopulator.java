package com.demo.entrepreneur.mapping.populator.impl;

import com.demo.entrepreneur.entity.User;
import org.springframework.stereotype.Component;

import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.model.mapping.populator.Populator;

@Component
public class UserPopulator implements Populator<UserDto, User> {

	@Override
	public User populateDataToEntity(UserDto data, User entity) {
	    entity.setLogin(data.getLogin());
		entity.setEmail(data.getEmail());
		entity.setFirstName(data.getFirstName());
		entity.setLastName(data.getLastName());
		entity.setPassword(data.getPassword());
		return entity;
	}

}
