package com.demo.entrepreneur.mapping.mapper.impl;

import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.model.mapping.mapper.Mapper;
import org.springframework.stereotype.Component;

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
