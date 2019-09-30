package com.demo.entrepreneur.repository;

import com.demo.entrepreneur.entity.ExchangeRate;
import org.springframework.data.repository.RepositoryDefinition;

import java.math.BigInteger;
import java.util.List;

@RepositoryDefinition(domainClass = ExchangeRate.class, idClass = BigInteger.class)
public interface ExchangeRateRepository {

    List<ExchangeRate> findAll();

    List<ExchangeRate> saveAll(Iterable<ExchangeRate> list);

    ExchangeRate save(ExchangeRate exchangeRate);
}
