package com.demo.entrepreneur.service.impl;

import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.service.EmailValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultEmailValidatorService.class)
@TestPropertySource("/application-test.properties")
class DefaultEmailValidatorServiceTest {

    @Autowired
    private EmailValidatorService emailValidator;

    private long defaultUserId = 123L;

    @Test
    void whenEmailIsValidThenReturnTrue() {
        User user = new User();
        user.setId(defaultUserId);
        user.setEmail("user1@gmail.com");

        assertThat("Email is valid", emailValidator.isValid(user.getEmail()));
    }

    @Test
    void whenEmailIsNotValidThenReturnFalse() {
        User user = new User();
        user.setId(defaultUserId);
        user.setEmail("user1g.c");

        assertThat("Email is not valid", emailValidator.isValid(user.getEmail()), equalTo(false));
    }

    @Test
    void whenEmailIsNotAllowedThenReturnFalse() {
        User user = new User();
        user.setId(defaultUserId);
        user.setEmail("ololffffo@mail.ru");

        assertThat("Email is not allowed", emailValidator.isValid(user.getEmail()), equalTo(false));
    }
}