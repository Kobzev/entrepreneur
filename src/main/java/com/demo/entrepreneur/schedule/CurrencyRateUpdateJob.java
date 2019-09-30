package com.demo.entrepreneur.schedule;

import com.demo.entrepreneur.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRateUpdateJob {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyRateUpdateJob(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Scheduled(cron = "${cron.template.exchangeRate}")
    public void updateRates() {
        currencyService.updateExchangeRates();
    }
}
