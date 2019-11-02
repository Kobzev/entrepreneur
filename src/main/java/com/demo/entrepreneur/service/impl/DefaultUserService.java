package com.demo.entrepreneur.service.impl;

import com.demo.entrepreneur.exception.UnsupportedEmailException;
import com.demo.entrepreneur.repository.UserRepository;
import com.demo.entrepreneur.dto.RequestUserDto;
import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.mapping.populator.impl.RequestUserPopulator;
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
    private RequestUserPopulator requestUserPopulator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailValidatorService emailValidator;

    @Autowired
    private EmailSenderService emailSender;

    @Override
    public User registerNewUser(RequestUserDto requestUserDto) {
        if (!emailValidator.isValid(requestUserDto.getEmail())) {
            throw new UnsupportedEmailException("User email is not valid");
        }
        final User user = requestUserPopulator.populateDataToEntity(requestUserDto, new User());
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
    public User updateUserByLogin(String login, RequestUserDto requestUserDto) {
        final User user = getUserByLogin(login);
        return requestUserPopulator.populateDataToEntity(requestUserDto, user);
    }

    @Override
    public void deleteUserByLogin(String login) {
        final User user = getUserByLogin(login);
        userRepository.delete(user);
    }

}
