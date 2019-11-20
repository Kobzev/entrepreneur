package com.demo.entrepreneur.service;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyService {

    List<LocalDate> getAllMissedDates();
    void loadOldestMissedDateFromArchive();
    void updateExchangeRates();
}
