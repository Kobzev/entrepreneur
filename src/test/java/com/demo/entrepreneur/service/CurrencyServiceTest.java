package com.demo.entrepreneur.service;

import com.demo.entrepreneur.model.dto.ExchangeRateDto;
import com.demo.entrepreneur.model.entity.ExchangeRate;
import com.demo.entrepreneur.model.enumeration.Currency;
import com.demo.entrepreneur.model.mapping.ExchangeRateMapper;
import com.demo.entrepreneur.model.repository.ExchangeRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @InjectMocks
    private CurrencyService service;
    @Mock
    private ExchangeRateRepository rateRepository;
    @Mock
    private RestTemplate restTemplate;
    @Spy
    private ExchangeRateMapper rateMapper = new ExchangeRateMapper();

    private static final Currency CURRENT_CURRENCY = Currency.USD;
    private static final Currency BASE_CURRENCY = Currency.UAH;
    private static final BigDecimal SALE_PRICE = BigDecimal.valueOf(25.5);
    private static final BigDecimal BUY_PRICE = BigDecimal.valueOf(25.5);
    private static final String API_URL = "https://somehost/api.test/currecy";
    private static final String INVALID_CURRENCY = "SMTH";

    private ExchangeRateDto exchangeRateDto;

    @BeforeEach
    void setUp() {
        exchangeRateDto = ExchangeRateDto.builder()
                .baseCurrency(BASE_CURRENCY.name())
                .currentCurrency(CURRENT_CURRENCY.name())
                .buyPrice(BUY_PRICE.toPlainString())
                .salePrice(SALE_PRICE.toPlainString())
                .build();
        ReflectionTestUtils.setField(service, "apiUrl", API_URL);
    }

    @Test
    void whenUrlIsValidAndRestTemplateReturnObject() {
        ResponseEntity responseEntity = Mockito.mock(ResponseEntity.class);
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(new ExchangeRateDto[]{exchangeRateDto});
        ArgumentCaptor<List<ExchangeRate>> listArg = ArgumentCaptor.forClass(List.class);

        service.updateExchangeRates();

        verify(restTemplate).getForEntity(anyString(), any());
        verify(rateMapper, atLeastOnce()).dtoToCurrency(any(ExchangeRateDto.class));
        verify(rateRepository).saveAll(listArg.capture());
        listArg.getValue().forEach(elem -> {
            assertEquals(Currency.valueOf(exchangeRateDto.getCurrentCurrency()), elem.getCurrentCurrency());
            assertEquals(Currency.valueOf(exchangeRateDto.getBaseCurrency()), elem.getBaseCurrency());
            assertEquals(new BigDecimal(exchangeRateDto.getBuyPrice()), elem.getBuyPrice());
            assertEquals(new BigDecimal(exchangeRateDto.getSalePrice()), elem.getSalePrice());
        });
    }

    @Test
    void whenUrlIsInvalid() {
        when(restTemplate.getForEntity(anyString(), any())).thenThrow(new RuntimeException("Could not extract response"));

        assertThrows(RuntimeException.class, service::updateExchangeRates);

        verify(restTemplate).getForEntity(anyString(), any());
        verify(rateMapper, never()).dtoToCurrency(any());
        verify(rateRepository, never()).saveAll(anyIterable());
    }

    @Test
    void whenBaseCurrencyIsInvalid() {
        exchangeRateDto.setBaseCurrency(INVALID_CURRENCY);
        ResponseEntity responseEntity = Mockito.mock(ResponseEntity.class);
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(new ExchangeRateDto[]{exchangeRateDto});
        ArgumentCaptor<List<ExchangeRate>> listArg = ArgumentCaptor.forClass(List.class);

        service.updateExchangeRates();

        verify(restTemplate).getForEntity(anyString(), any());
        verify(rateMapper, never()).dtoToCurrency(any(ExchangeRateDto.class));
        verify(rateRepository).saveAll(listArg.capture());
        assertTrue(listArg.getValue().isEmpty());
    }

    @Test
    void whenCurrentCurrencyIsInvalid() {
        exchangeRateDto.setCurrentCurrency(INVALID_CURRENCY);
        ResponseEntity responseEntity = Mockito.mock(ResponseEntity.class);
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(new ExchangeRateDto[]{exchangeRateDto});
        ArgumentCaptor<List<ExchangeRate>> listArg = ArgumentCaptor.forClass(List.class);

        service.updateExchangeRates();

        verify(restTemplate).getForEntity(anyString(), any());
        verify(rateMapper, never()).dtoToCurrency(any(ExchangeRateDto.class));
        verify(rateRepository).saveAll(listArg.capture());
        assertTrue(listArg.getValue().isEmpty());
    }
}