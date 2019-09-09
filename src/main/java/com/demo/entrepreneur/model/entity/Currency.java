package com.demo.entrepreneur.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Currency {

    @JsonProperty("ccy")
    private String currentCurrency;
    @JsonProperty("base_ccy")
    private String baseCurrency;
    @JsonProperty("buy")
    private Double buyPrice;
    @JsonProperty("sale")
    private Double salePrice;

}
