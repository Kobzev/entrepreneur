package com.demo.entrepreneur.rest;

import com.demo.entrepreneur.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/exchanges")
public class ExchangeRateController {

    private CurrencyService currencyService;

    @Autowired
    public ExchangeRateController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/missed/dates")
    public ResponseEntity getMissedDates() {
        return ResponseEntity.ok(Collections.singletonMap("Missed dates", currencyService.getAllMissedDates()));
    }
}
