package com.demo.entrepreneur.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

import com.demo.entrepreneur.model.User;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {

    User save(User user);

    List<User> findAll();

    Optional<User> findByLogin(String login);

    void delete(User user);
}
