package com.demo.entrepreneur.schedule;

import com.demo.entrepreneur.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CurrencyRateUpdateServiceTest {

    @InjectMocks private CurrencyRateUpdateJob currencyRateUpdateService;
    @Mock private CurrencyService currencyService;

    @Test
    void whenScheduleCallUpdateRates() {
        currencyRateUpdateService.updateRates();

        verify(currencyService).getUpdatedExchangeRates();
    }
}