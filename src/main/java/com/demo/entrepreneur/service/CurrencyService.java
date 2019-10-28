package com.demo.entrepreneur.service;

import com.demo.entrepreneur.dto.ExchangeRateDto;
import com.demo.entrepreneur.entity.ExchangeRate;
import com.demo.entrepreneur.enumeration.Currency;
import com.demo.entrepreneur.mapping.mapper.impl.ExchangeRateMapper;
import com.demo.entrepreneur.repository.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class CurrencyService {


    @Value("${exchangeRate.api.url}")
    private String apiUrl;
    private ExchangeRateRepository rateRepository;
    private ExchangeRateMapper rateMapper;
    private RestTemplate restTemplate;

    @Autowired
    public CurrencyService(ExchangeRateRepository rateRepository, ExchangeRateMapper rateMapper, RestTemplate restTemplate) {
        this.rateRepository = rateRepository;
        this.rateMapper = rateMapper;
        this.restTemplate = restTemplate;
    }

    public void updateExchangeRates() {
        log.info("Call currency api({}) to update currency.", apiUrl);

        ResponseEntity<ExchangeRateDto[]> responseEntity = restTemplate.getForEntity(apiUrl, ExchangeRateDto[].class);
        ExchangeRateDto[] rates = responseEntity.getBody();
        List<ExchangeRate> exchangeRates = Stream.of(rates)
                .filter(this::isExchangeValid)
                .map(rateMapper::dataToTheNewEntity)
                .collect(Collectors.toList());
        List<ExchangeRate> saved = rateRepository.saveAll(exchangeRates);

        log.info("Saved rates: {}", saved);
    }

    private boolean isExchangeValid(ExchangeRateDto elem) {
        return Currency.isValid(elem.getBaseCurrency())
                && Currency.isValid(elem.getCurrentCurrency())
                && !elem.getCurrentCurrency().equals(elem.getBaseCurrency());
    }
}
