package com.demo.entrepreneur.mapping.mapper.impl;

import com.demo.entrepreneur.dto.PublicUserDto;
import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.mapping.mapper.Mapper;
import org.springframework.stereotype.Component;

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
