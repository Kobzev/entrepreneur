package com.demo.entrepreneur.service;

import com.demo.entrepreneur.model.dto.ExchangeRateDto;
import com.demo.entrepreneur.model.entity.ExchangeRate;
import com.demo.entrepreneur.model.enumeration.Currency;
import com.demo.entrepreneur.model.mapping.ExchangeRateMapper;
import com.demo.entrepreneur.model.repository.ExchangeRateRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class CurrencyServiceTest {

    @InjectMocks private static CurrencyService service;
    @Mock private ExchangeRateRepository rateRepository;
    @Mock private RestTemplate restTemplate;
    @Spy  private ExchangeRateMapper rateMapper = new ExchangeRateMapper();

    private static final BigInteger ID = BigInteger.ONE;
    private static final Currency CURRENT_CURRENCY = Currency.USD;
    private static final Currency BASE_CURRENCY = Currency.UAH;
    private static final BigDecimal SALE_PRICE = BigDecimal.valueOf(25.5);
    private static final BigDecimal BUY_PRICE = BigDecimal.valueOf(25.5);
    private static final String API_URL = "https://somehost/api.test/currecy";

    private static ExchangeRateDto exchangeRateDto;
    private static ExchangeRate exchangeRate;

    @BeforeAll
    static void setUp() {
        exchangeRateDto = new ExchangeRateDto().setBaseCurrency(BASE_CURRENCY.name())
                .setCurrentCurrency(CURRENT_CURRENCY.name())
                .setBuyPrice(BUY_PRICE.toPlainString())
                .setSalePrice(SALE_PRICE.toPlainString());
        exchangeRate = new ExchangeRate().setId(ID)
                .setBaseCurrency(BASE_CURRENCY)
                .setCurrentCurrency(CURRENT_CURRENCY)
                .setBuyPrice(BUY_PRICE)
                .setSalePrice(SALE_PRICE);
        ReflectionTestUtils.setField(service, "apiUrl", API_URL);
    }

    @Test
    void whenUrlIsValidAndRestTemplateReturnObject() {
        ResponseEntity responseEntity = Mockito.mock(ResponseEntity.class);
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(new ExchangeRateDto[] {exchangeRateDto});
        ArgumentCaptor<List<ExchangeRate>> listArg = ArgumentCaptor.forClass(List.class);

        service.getUpdatedExchangeRatesAndSave();

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

        assertThrows(RuntimeException.class, service::getUpdatedExchangeRatesAndSave);

        verify(restTemplate).getForEntity(anyString(), any());
        verify(rateMapper, never()).dtoToCurrency(any());
        verify(rateRepository, never()).saveAll(anyIterable());
    }

}