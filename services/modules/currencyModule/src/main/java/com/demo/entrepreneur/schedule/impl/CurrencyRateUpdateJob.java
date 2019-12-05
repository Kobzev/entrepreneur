package com.demo.entrepreneur.schedule.impl;

import com.demo.entrepreneur.schedule.ScheduledJob;
import com.demo.entrepreneur.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRateUpdateJob implements ScheduledJob {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyRateUpdateJob(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    @Scheduled(cron = "${cron.template.exchangeRate}")
    public void execute() {
        currencyService.updateExchangeRates();
    }
}
