package com.demo.entrepreneur.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduledTasks {
    private Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private CurrencyClient apiClient;

    @Scheduled(cron = "${cron.template.currency}")
    public void currencyUpdateTask() {
        apiClient.updateCurrency();
    }

}
