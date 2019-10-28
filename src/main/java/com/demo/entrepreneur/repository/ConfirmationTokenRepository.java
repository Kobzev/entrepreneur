package com.demo.entrepreneur.repository;

import com.demo.entrepreneur.entity.ConfirmationToken;
import org.springframework.data.repository.RepositoryDefinition;

import java.math.BigInteger;

@RepositoryDefinition(domainClass = ConfirmationToken.class, idClass = BigInteger.class)
public interface ConfirmationTokenRepository {

    ConfirmationToken findByToken(String confirmationToken);

    ConfirmationToken save(ConfirmationToken confirmationToken);
}
