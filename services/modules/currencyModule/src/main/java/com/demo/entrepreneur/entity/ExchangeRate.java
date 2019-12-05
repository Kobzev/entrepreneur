package com.demo.entrepreneur.entity;

import com.demo.entrepreneur.enumeration.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Enumerated(EnumType.STRING)
    private Currency currentCurrency;
    @Enumerated(EnumType.STRING)
    private Currency baseCurrency;
    private BigDecimal buyPrice;
    private BigDecimal salePrice;
    @Builder.Default
    private LocalDate date = LocalDate.now();
}
