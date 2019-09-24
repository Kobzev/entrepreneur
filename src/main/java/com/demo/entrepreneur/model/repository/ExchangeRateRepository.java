package com.demo.entrepreneur.model.repository;

import com.demo.entrepreneur.model.entity.ExchangeRate;
import org.springframework.data.repository.RepositoryDefinition;

import java.math.BigInteger;
import java.util.List;

@RepositoryDefinition(domainClass = ExchangeRate.class, idClass = BigInteger.class)
public interface ExchangeRateRepository {

    List<ExchangeRate> findAll();

    List<ExchangeRate> saveAll(Iterable<ExchangeRate> list);

    ExchangeRate save(ExchangeRate exchangeRate);
}
