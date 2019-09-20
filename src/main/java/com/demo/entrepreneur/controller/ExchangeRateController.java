package com.demo.entrepreneur.controller;

import com.demo.entrepreneur.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/rates")
public class ExchangeRateController {

    private CurrencyService currencyService;

    @PostMapping("/load")
    public void loadFromSomeDatePleaseRenameThis() {
        currencyService.loadFromArchiveTillToday();
    }

    @GetMapping("/missed")
    public ResponseEntity<Map<String, List<LocalDate>>> getAllMissedDates() {
        List<LocalDate> allMissedDates = currencyService.getAllMissedDates();
        return ResponseEntity.ok(Collections.singletonMap("Missed dates", allMissedDates));
    }
}