package com.demo.entrepreneur.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateDto {

    @JsonProperty("ccy")
    private String currentCurrency;
    @JsonProperty("base_ccy")
    private String baseCurrency;
    @JsonProperty("buy")
    private Double buyPrice;
    @JsonProperty("sale")
    private Double salePrice;

}
