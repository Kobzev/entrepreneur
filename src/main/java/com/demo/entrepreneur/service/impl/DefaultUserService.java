package com.demo.entrepreneur.service.impl;

import com.demo.entrepreneur.repository.UserRepository;
import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.mapping.populator.impl.UserPopulator;
import com.demo.entrepreneur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserPopulator userPopulator;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerNewUser(UserDto userDto) {
        final User user = userPopulator.populateDataToEntity(userDto, new User());
        return userRepository.save(user);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public User updateUserByLogin(String login, UserDto userDto) {
        final User user = getUserByLogin(login);
        return userPopulator.populateDataToEntity(userDto, user);
    }

    @Override
    public void deleteUserByLogin(String login) {
        final User user = getUserByLogin(login);
        userRepository.delete(user);
    }

}
