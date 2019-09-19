package com.demo.entrepreneur.repository;

import com.demo.entrepreneur.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, BigInteger> {
}
