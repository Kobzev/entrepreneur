package com.demo.entrepreneur.repository;

import com.demo.entrepreneur.entity.User;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {

    User save(User user);

    List<User> findAll();

    Optional<User> findByLogin(String login);

    void delete(User user);

    User findByEmail(String email);
}
