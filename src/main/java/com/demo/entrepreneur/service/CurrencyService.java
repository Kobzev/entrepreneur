package com.demo.entrepreneur.service;

import com.demo.entrepreneur.dto.ExchangeRateDto;
import com.demo.entrepreneur.entity.ExchangeRate;
import com.demo.entrepreneur.enumeration.Currency;
import com.demo.entrepreneur.mapping.mapper.impl.ExchangeRateMapper;
import com.demo.entrepreneur.mapping.populator.impl.ExchangeRatePopulator;
import com.demo.entrepreneur.repository.ExchangeRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);

    @Value("${exchangeRate.api.url}")
    private String apiUrl;

    @Autowired
    private ExchangeRateRepository rateRepository;

    @Autowired
    private ExchangeRatePopulator ratePopulator;

    @Autowired
    private RestTemplate restTemplate;


    public void updateExchangeRates() {
        LOGGER.info("Call currency api({}) to update currency.", apiUrl);

        ResponseEntity<ExchangeRateDto[]> responseEntity = restTemplate.getForEntity(apiUrl, ExchangeRateDto[].class);
        ExchangeRateDto[] rates = responseEntity.getBody();
        List<ExchangeRate> exchangeRates = Stream.of(rates)
                .filter(this::isExchangeValid)
                .map(ratePopulator::populateDataToEntity)
                .collect(Collectors.toList());
        List<ExchangeRate> saved = rateRepository.saveAll(exchangeRates);

        LOGGER.info("Saved rates: {}", saved);
    }

    private boolean isExchangeValid(ExchangeRateDto elem) {
        return Currency.isValid(elem.getBaseCurrency())
                && Currency.isValid(elem.getCurrentCurrency())
                && !elem.getCurrentCurrency().equals(elem.getBaseCurrency());
    }
}
