package com.demo.entrepreneur.mapping.mapper.impl;

import com.demo.entrepreneur.dto.currency.ExchangeRateDto;
import com.demo.entrepreneur.entity.ExchangeRate;
import com.demo.entrepreneur.mapping.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateMapper implements Mapper<ExchangeRateDto, ExchangeRate> {

    @Override
    public ExchangeRateDto entityToData(ExchangeRate exchangeRate) {
        return ExchangeRateDto.builder()
                .currentCurrency(exchangeRate.getCurrentCurrency().name())
                .baseCurrency(exchangeRate.getBaseCurrency().name())
                .salePrice(exchangeRate.getSalePrice().toPlainString())
                .buyPrice(exchangeRate.getBuyPrice().toPlainString())
                .date(exchangeRate.getDate())
                .build();
    }
}
