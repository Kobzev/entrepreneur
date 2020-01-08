package com.demo.entrepreneur.mapping.populator.impl;

import com.demo.entrepreneur.dto.currency.ExchangeRateDto;
import com.demo.entrepreneur.entity.ExchangeRate;
import com.demo.entrepreneur.enumeration.Currency;
import com.demo.entrepreneur.mapping.populator.Populator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExchangeRatePopulator implements Populator<ExchangeRateDto, ExchangeRate> {

    @Override
    public ExchangeRate populateDataToEntity(ExchangeRateDto data, ExchangeRate entity) {
        entity.setBaseCurrency(Currency.valueOf(data.getBaseCurrency()));
        entity.setBuyPrice(new BigDecimal(data.getBuyPrice()));
        entity.setCurrentCurrency(Currency.valueOf(data.getCurrentCurrency()));
        entity.setDate(data.getDate());
        entity.setSalePrice(new BigDecimal(data.getSalePrice()));
        return entity;
    }
}
