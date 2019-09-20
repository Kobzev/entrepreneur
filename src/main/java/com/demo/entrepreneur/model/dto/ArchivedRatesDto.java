package com.demo.entrepreneur.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArchivedRatesDto {

    @JsonProperty("date")
    @JsonFormat(pattern = "dd.MM.YYYY")
    private String date;
    @JsonProperty("bank")
    private String bank;
    @JsonProperty("exchangeRate")
    private List<ExchangeRateDto> exchangeRate;
}
