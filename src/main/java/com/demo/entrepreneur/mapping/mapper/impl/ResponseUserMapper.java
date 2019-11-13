package com.demo.entrepreneur.mapping.mapper.impl;

import com.demo.entrepreneur.dto.user.ResponseUserDto;
import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.mapping.mapper.Mapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Component
@Slf4j
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
        log.info(data.toString());
        return data;
    }

}
