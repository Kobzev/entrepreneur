package com.demo.entrepreneur.model.repository;

import com.demo.entrepreneur.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, BigInteger> {

    @Query("SELECT DISTINCT exchange.date FROM ExchangeRate exchange")
    Set<LocalDate> findDistinctDate();
}
