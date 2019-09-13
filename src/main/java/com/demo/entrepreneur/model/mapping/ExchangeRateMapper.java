package com.demo.entrepreneur.model.mapping;

import com.demo.entrepreneur.model.dto.ExchangeRateDto;
import com.demo.entrepreneur.model.entity.ExchangeRate;
import com.demo.entrepreneur.model.enumeration.Currency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
public class ExchangeRateMapper {

    public ExchangeRateDto currencyToDto(ExchangeRate exchangeRate) {
        return new ExchangeRateDto().setCurrentCurrency(exchangeRate.getCurrentCurrency().name())
                .setBaseCurrency(exchangeRate.getBaseCurrency().name())
                .setSalePrice(exchangeRate.getSalePrice().toPlainString())
                .setBuyPrice(exchangeRate.getBuyPrice().toPlainString());
    }

    public ExchangeRate dtoToCurrency(ExchangeRateDto dto) {
        return new ExchangeRate().setId(BigInteger.ZERO)
                .setCurrentCurrency(Currency.valueOf(dto.getCurrentCurrency()))
                .setBaseCurrency(Currency.valueOf(dto.getBaseCurrency()))
                .setSalePrice(new BigDecimal(dto.getSalePrice()))
                .setBuyPrice(new BigDecimal(dto.getBuyPrice()));
    }

}
