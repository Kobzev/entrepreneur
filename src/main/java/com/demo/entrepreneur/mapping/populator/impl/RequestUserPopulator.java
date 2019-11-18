package com.demo.entrepreneur.mapping.populator.impl;

import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.enumeration.TaxGroup;

import org.springframework.stereotype.Component;

import com.demo.entrepreneur.dto.user.RequestUserDto;
import com.demo.entrepreneur.mapping.populator.Populator;

@Component
public class RequestUserPopulator implements Populator<RequestUserDto, User> {

    @Override
    public User populateDataToEntity(RequestUserDto data, User entity) {
        entity.setLogin(data.getLogin());
        entity.setEmail(data.getEmail());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setPassword(data.getPassword());
        entity.setApproximateIncomeInUsd(data.getApproximateIncomeInUsd());
        entity.setTaxGroup(TaxGroup.valueOfGroupNumber(data.getTaxGroup()));
        return entity;
    }

}
