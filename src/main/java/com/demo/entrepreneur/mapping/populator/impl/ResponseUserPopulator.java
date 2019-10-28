package com.demo.entrepreneur.mapping.populator.impl;

import com.demo.entrepreneur.dto.ResponseUserDto;
import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.mapping.populator.Populator;

public class ResponseUserPopulator implements Populator<ResponseUserDto, User> {

    @Override
    public User populateDataToEntity(ResponseUserDto data, User entity) {
        entity.setLogin(data.getLogin());
        entity.setEmail(data.getEmail());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        return entity;
    }
}
