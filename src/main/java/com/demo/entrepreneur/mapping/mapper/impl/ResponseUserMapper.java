package com.demo.entrepreneur.mapping.mapper.impl;

import org.springframework.stereotype.Component;

import com.demo.entrepreneur.dto.user.ResponseUserDto;
import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.mapping.mapper.Mapper;

@Component
public class ResponseUserMapper implements Mapper<ResponseUserDto, User> {

    @Override
    public ResponseUserDto entityToData(User entity) {
        ResponseUserDto data = ResponseUserDto.builder()// @formatter:off
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .login(entity.getLogin())
                .approximateIncomeInUsd(entity.getApproximateIncomeInUsd())
                .taxGroup(entity.getTaxGroup().getGroupNumber()).build();// @formatter:on
        return data;
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
