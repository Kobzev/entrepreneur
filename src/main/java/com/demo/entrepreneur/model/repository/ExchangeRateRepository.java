package com.demo.entrepreneur.model.repository;

import com.demo.entrepreneur.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, BigInteger> {
}
