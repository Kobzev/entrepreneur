package com.demo.entrepreneur.repository;

import com.demo.entrepreneur.entity.ExchangeRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RepositoryDefinition(domainClass = ExchangeRate.class, idClass = BigInteger.class)
public interface ExchangeRateRepository {

    List<ExchangeRate> findAll();

    List<ExchangeRate> saveAll(Iterable<ExchangeRate> list);

    ExchangeRate save(ExchangeRate exchangeRate);

    @Query("SELECT DISTINCT date FROM ExchangeRate rate WHERE rate.date >= :from_date")
    Set<LocalDate> findAllDistinctDates(@Param("from_date") LocalDate fromDate);

    @Query("SELECT DISTINCT date FROM ExchangeRate rate")
    Set<LocalDate> findAllDistinctDates();
}
