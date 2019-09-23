package com.demo.entrepreneur.model.repository;

import com.demo.entrepreneur.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, BigInteger> {
    ConfirmationToken findByToken(String confirmationToken);

}
