package com.demo.entrepreneur.mapping.mapper.impl;

import com.demo.entrepreneur.dto.ResponseUserDto;
import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.mapping.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ResponseUserMapper implements Mapper<ResponseUserDto, User> {

    @Override
    public ResponseUserDto entityToData(User entity) {
        return ResponseUserDto.builder()// @formatter:off
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .login(entity.getLogin()).build();// @formatter:on
    }

    @Override
    public User dataToEntity(ResponseUserDto data) {
        return User.builder()
                .login(data.getLogin())
                .email(data.getEmail())
                .firstName(data.getFirstName())
                .lastName(data.getLastName()).build();
    }

}
