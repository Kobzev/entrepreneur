package com.demo.entrepreneur.mapping;

import com.demo.entrepreneur.dto.ExchangeRateDto;
import com.demo.entrepreneur.entity.ExchangeRate;
import com.demo.entrepreneur.enumeration.Currency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExchangeRateMapper {

    public ExchangeRateDto currencyToDto(ExchangeRate exchangeRate) {
        return ExchangeRateDto.builder()
                .currentCurrency(exchangeRate.getCurrentCurrency().name())
                .baseCurrency(exchangeRate.getBaseCurrency().name())
                .salePrice(exchangeRate.getSalePrice().toPlainString())
                .buyPrice(exchangeRate.getBuyPrice().toPlainString())
                .date(exchangeRate.getDate())
                .build();
    }

    public ExchangeRate dtoToCurrency(ExchangeRateDto dto) {
        return ExchangeRate.builder()
                .currentCurrency(Currency.valueOf(dto.getCurrentCurrency()))
                .baseCurrency(Currency.valueOf(dto.getBaseCurrency()))
                .salePrice(new BigDecimal(dto.getSalePrice()))
                .buyPrice(new BigDecimal(dto.getBuyPrice()))
                .date(dto.getDate())
                .build();
    }
}
