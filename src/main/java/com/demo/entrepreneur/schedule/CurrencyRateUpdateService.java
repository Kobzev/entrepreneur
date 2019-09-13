package com.demo.entrepreneur.schedule;

import com.demo.entrepreneur.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrencyRateUpdateService {

    private CurrencyService currencyService;

    @Scheduled(cron = "${cron.template.exchangeRate}")
    public void updateRates() {
        currencyService.getUpdatedExchangeRates();
    }
}
