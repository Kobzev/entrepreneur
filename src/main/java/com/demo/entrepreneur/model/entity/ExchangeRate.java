package com.demo.entrepreneur.model.entity;


import com.demo.entrepreneur.model.enumeration.Currency;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private BigInteger id;
    @Enumerated(EnumType.STRING)
    private Currency currentCurrency;
    @Enumerated(EnumType.STRING)
    private Currency baseCurrency;
    private BigDecimal buyPrice;
    private BigDecimal salePrice;

}
