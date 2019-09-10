package com.demo.entrepreneur.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduledTasks {

    private CurrencyService apiClient;

    @Scheduled(cron = "${cron.template.exchangeRate}")
    public void rateUpdateTask() {
        apiClient.getUpdatedExchangeRatesAndSave();
    }

}
