package com.demo.entrepreneur.model.mapping.mapper.impl;

import org.springframework.stereotype.Component;

import com.demo.entrepreneur.dto.PublicUserDto;
import com.demo.entrepreneur.model.User;
import com.demo.entrepreneur.model.mapping.mapper.Mapper;

@Component
public class PublicUserMapper implements Mapper<PublicUserDto, User> {

    @Override
    public PublicUserDto entityToData(User entity) {
        return PublicUserDto.builder()// @formatter:off
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .login(entity.getLogin()).build();// @formatter:on
    }

}
