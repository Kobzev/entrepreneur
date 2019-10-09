package com.demo.entrepreneur.service;

import com.demo.entrepreneur.dto.ArchivedRatesDto;
import com.demo.entrepreneur.dto.ExchangeRateDto;
import com.demo.entrepreneur.entity.ExchangeRate;
import com.demo.entrepreneur.enumeration.Currency;
import com.demo.entrepreneur.mapping.ExchangeRateMapper;
import com.demo.entrepreneur.repository.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class CurrencyService {

    @Value("${exchangeRate.api.url}")
    private String apiUrl;
    @Value("${exchangeRate.archive.api.url}")
    private String archiveApiUrl;
    @Value("#{T(java.time.LocalDate).parse('${archive.date.begin}')}")
    private LocalDate archiveStartDate;
    @Value("#{T(java.time.format.DateTimeFormatter).ofPattern('${archive.date.api.format}')}")
    private DateTimeFormatter apiDateFormat;

    private ExchangeRateRepository rateRepository;
    private ExchangeRateMapper rateMapper;
    private RestTemplate restTemplate;

    @Autowired
    public CurrencyService(ExchangeRateRepository rateRepository, ExchangeRateMapper rateMapper, RestTemplate restTemplate) {
        this.rateRepository = rateRepository;
        this.rateMapper = rateMapper;
        this.restTemplate = restTemplate;
    }

    public void updateExchangeRates() {
        log.info("Call currency api({}) to update currency.", apiUrl);

        ResponseEntity<ExchangeRateDto[]> responseEntity = restTemplate.getForEntity(apiUrl, ExchangeRateDto[].class);
        log.info("Look here {}", responseEntity.getBody());
        ExchangeRateDto[] rates = responseEntity.getBody();
        List<ExchangeRate> exchangeRates = Stream.of(rates)
                .filter(this::isExchangeValid)
                .map(rateMapper::dtoToCurrency)
                .collect(Collectors.toList());
        List<ExchangeRate> saved = rateRepository.saveAll(exchangeRates);

        log.info("Saved rates: {}", saved);
    }

    public void loadOldestMissedDateFromArchive() {
        LocalDate lastDate = getOldestMissedDate();
        if (lastDate.isBefore(LocalDate.now())) {
            ResponseEntity<ArchivedRatesDto> archivedRate =
                    restTemplate.getForEntity(archiveApiUrl + lastDate.format(apiDateFormat), ArchivedRatesDto.class);
            log.info("Got archived exchange rate: {}", archivedRate.getBody());

            List<ExchangeRate> exchangeRatesByDay = archivedRate.getBody()
                    .getExchangeRate().stream()
                    .filter(this::isExchangeValid)
                    .peek(elem -> elem.setDate(lastDate))
                    .map(rateMapper::dtoToCurrency)
                    .collect(Collectors.toList());

            List<ExchangeRate> saved = rateRepository.saveAll(exchangeRatesByDay);
            log.info("Saved exchangeRate: {}", saved);
        }
    }

    public List<LocalDate> getAllMissedDates() {
        LocalDate endDate = LocalDate.now().plusDays(1);
        Set<LocalDate> datesInDB = rateRepository.findAllDistinctDates(archiveStartDate);
        List<LocalDate> rangeOfDates = Stream.iterate(archiveStartDate, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(archiveStartDate, endDate))
                .collect(Collectors.toList());
        rangeOfDates.removeAll(datesInDB);
        return rangeOfDates;
    }

    private LocalDate getOldestMissedDate() {
        LocalDate oldestMissedDate = getAllMissedDates().stream()
                .min(LocalDate::compareTo)
                .orElse(LocalDate.now());
        log.debug("The oldest missed date {}", oldestMissedDate);
        return oldestMissedDate;
    }

    private boolean isExchangeValid(ExchangeRateDto elem) {
        return Currency.isValid(elem.getBaseCurrency())
                && Currency.isValid(elem.getCurrentCurrency())
                && !elem.getCurrentCurrency().equals(elem.getBaseCurrency());
    }
}
