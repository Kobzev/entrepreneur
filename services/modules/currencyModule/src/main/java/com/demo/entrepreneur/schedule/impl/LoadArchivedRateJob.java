package com.demo.entrepreneur.schedule.impl;

import com.demo.entrepreneur.schedule.ScheduledJob;
import com.demo.entrepreneur.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoadArchivedRateJob implements ScheduledJob {

    private CurrencyService service;

    @Autowired
    public LoadArchivedRateJob(CurrencyService service) {
        this.service = service;
    }

    @Override
    @Scheduled(fixedRateString = "${archive.load.rate.millis}")
    public void execute() {
        service.loadOldestMissedDateFromArchive();
    }
}
