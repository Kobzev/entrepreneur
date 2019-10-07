package com.demo.entrepreneur.service.impl;

import com.demo.entrepreneur.dao.UserRepository;
import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.mapping.populator.impl.UserPopulator;
import com.demo.entrepreneur.service.EmailSenderService;
import com.demo.entrepreneur.service.EmailValidatorService;
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

    @Autowired
    private EmailValidatorService emailValidator;

    @Autowired
    private EmailSenderService emailSender;

    @Override
    public User registerNewUser(UserDto userDto) {
        if (!emailValidator.isValid(userDto.getEmail())) {
            throw new IllegalArgumentException("User email is not valid");
        }
        final User user = userPopulator.populateDataToEntity(userDto, new User());
        emailSender.sendVerificationEmail(user.getEmail());
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
