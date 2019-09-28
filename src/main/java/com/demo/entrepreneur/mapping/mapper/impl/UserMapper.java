package com.demo.entrepreneur.model.mapping.mapper.impl;

import org.springframework.stereotype.Component;

import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.model.User;
import com.demo.entrepreneur.model.mapping.mapper.Mapper;

@Component
public class UserMapper implements Mapper<UserDto, User> {

    @Override
    public UserDto entityToData(User entity) {
        return UserDto.builder()// @formatter:off
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .login(entity.getLogin())
                .password(entity.getPassword()).build();// @formatter:on
    }

}
