package com.demo.entrepreneur.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateDto {

    @JsonAlias({"ccy", "currency"})
    private String currentCurrency;
    @JsonAlias({"base_ccy", "baseCurrency"})
    private String baseCurrency;
    @JsonAlias({"buy", "purchaseRate", "purchaseRateNB"})
    private String buyPrice;
    @JsonAlias({"sale", "saleRate", "saleRateNB"})
    private String salePrice;
    @JsonProperty("date")
    @Builder.Default
    private LocalDate date = LocalDate.now();
}
