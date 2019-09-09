package com.demo.entrepreneur.service;

import com.demo.entrepreneur.model.entity.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

@Service
public class CurrencyClient {

    private Logger log = LoggerFactory.getLogger(CurrencyClient.class);

    @Value("${currency.api.url}")
    private String apiUrl;

    public void updateCurrency() {
        log.info("Call currency api({}) to update currency.", apiUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Currency[]> responseEntity = restTemplate.getForEntity(apiUrl, Currency[].class);
        Currency[] currencies = responseEntity.getBody();
        Stream.of(currencies).forEach(currency -> log.info(currency.toString()));
    }
}
