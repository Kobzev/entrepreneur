package com.demo.entrepreneur.service;

import com.demo.entrepreneur.model.dto.ExchangeRateDto;
import com.demo.entrepreneur.model.entity.ExchangeRate;
import com.demo.entrepreneur.model.mapping.ExchangeRateMapper;
import com.demo.entrepreneur.model.repository.ExchangeRateRepository;
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

    private final Logger log = LoggerFactory.getLogger(CurrencyService.class);

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

    public void getUpdatedExchangeRatesAndSave() {
        log.info("Call currency api({}) to update currency.", apiUrl);

        ResponseEntity<ExchangeRateDto[]> responseEntity = restTemplate.getForEntity(apiUrl, ExchangeRateDto[].class);
        ExchangeRateDto[] rates = responseEntity.getBody();
        List<ExchangeRate> exchangeRates = Stream.of(rates).map(rateMapper::dtoToCurrency)
                .collect(Collectors.toList());
        rateRepository.saveAll(exchangeRates);

        Stream.of(rates).forEach(rateDto -> log.info(rateDto.toString()));
    }
}
