package com.demo.entrepreneur.mapping.populator.impl;

import com.demo.entrepreneur.dto.ExchangeRateDto;

import com.demo.entrepreneur.entity.ExchangeRate;
import com.demo.entrepreneur.enumeration.Currency;
import com.demo.entrepreneur.mapping.populator.Populator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExchangeRatePopulator implements Populator<ExchangeRateDto, ExchangeRate> {

    @Override
    public ExchangeRate populateDataToEntity(ExchangeRateDto data, ExchangeRate entity) {
        entity.setCurrentCurrency(Currency.valueOf(data.getCurrentCurrency()));
        entity.setBaseCurrency(Currency.valueOf(data.getBaseCurrency()));
        entity.setSalePrice(new BigDecimal(data.getSalePrice()));
        entity.setBuyPrice(new BigDecimal(data.getBuyPrice()));
        entity.setDate(data.getDate());
        return entity;
    }

    public ExchangeRate populateDataToEntity(ExchangeRateDto dto) {
        return ExchangeRate.builder()
                .currentCurrency(Currency.valueOf(dto.getCurrentCurrency()))
                .baseCurrency(Currency.valueOf(dto.getBaseCurrency()))
                .salePrice(new BigDecimal(dto.getSalePrice()))
                .buyPrice(new BigDecimal(dto.getBuyPrice()))
                .date(dto.getDate())
                .build();
    }

}
