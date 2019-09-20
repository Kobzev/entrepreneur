package com.demo.entrepreneur.service;

import com.demo.entrepreneur.model.dto.ArchivedRatesDto;
import com.demo.entrepreneur.model.dto.ExchangeRateDto;
import com.demo.entrepreneur.model.entity.ExchangeRate;
import com.demo.entrepreneur.model.enumeration.Currency;
import com.demo.entrepreneur.model.mapping.ExchangeRateMapper;
import com.demo.entrepreneur.model.repository.ExchangeRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CurrencyService {

    private final Logger log = LoggerFactory.getLogger(CurrencyService.class);

    @Value("${api.url.exchange}")
    private String apiUrl;
    @Value("${api.url.archive.exchange}")
    private String archiveApiUrl;
    @Value("#{T(java.time.LocalDate).parse('${archive.date.begin}')}")
    private LocalDate archiveStartDate;
    @Value("#{T(java.time.format.DateTimeFormatter).ofPattern('${api.archive.date.format}')}")
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

    public void getUpdatedExchangeRates() {
        log.info("Call currency api({}) to update currency.", apiUrl);

        ResponseEntity<ExchangeRateDto[]> responseEntity = restTemplate.getForEntity(apiUrl, ExchangeRateDto[].class);
        ExchangeRateDto[] rates = responseEntity.getBody();
        List<ExchangeRate> exchangeRates = Stream.of(rates)
                .filter(this::isExchangeValid)
                .map(rateMapper::dtoToCurrency)
                .collect(Collectors.toList());
        rateRepository.saveAll(exchangeRates);

        log.info(Arrays.toString(rates));
    }

    public void loadFromArchiveTillToday() {
        log.info("Begin loading from archive starting from {}", archiveStartDate);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        getAllMissedDates().stream()
                .forEach(date -> executorService.submit(
                        () -> loadByDate(date))
                );
    }

    public List<LocalDate> getAllMissedDates() {
        LocalDate endDate = LocalDate.now().plusDays(1);
        Set<LocalDate> datesInDB = rateRepository.findDistinctDate();
        List<LocalDate> rangeOfDates = Stream.iterate(archiveStartDate, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(archiveStartDate, endDate))
                .collect(Collectors.toList());
        rangeOfDates.removeAll(datesInDB);
        return rangeOfDates;
    }

    private List<ExchangeRate> loadByDate(final LocalDate temp) {
        ResponseEntity<ArchivedRatesDto> archivedRate =
                restTemplate.getForEntity(archiveApiUrl + temp.format(apiDateFormat), ArchivedRatesDto.class);
        log.info("Got archived exchange rate: {}", archivedRate.getBody());

        List<ExchangeRate> exchangeRatesByDay = archivedRate.getBody()
                .getExchangeRate().stream()
                .filter(this::isExchangeValid)
                .peek(elem -> elem.setDate(temp))
                .map(rateMapper::dtoToCurrency)
                .collect(Collectors.toList());
        List<ExchangeRate> saved = rateRepository.saveAll(exchangeRatesByDay);
        log.info("Saved exchangeRate: {}", saved);
        return exchangeRatesByDay;
    }

    private boolean isExchangeValid(ExchangeRateDto elem) {
        return Currency.isValid(elem.getBaseCurrency())
                && Currency.isValid(elem.getCurrentCurrency())
                && !elem.getCurrentCurrency().equals(elem.getBaseCurrency());
    }
}
