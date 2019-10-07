package com.demo.entrepreneur.service.impl;

import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.service.EmailValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DefaultEmailValidatorServiceTest {

    private EmailValidatorService emailValidator;
    private List<String> prohibitedDomains;


    @BeforeEach
    void setUp() {
        emailValidator = new DefaultEmailValidatorService();
        prohibitedDomains = Arrays.asList("mail.ru","yandex");
        ReflectionTestUtils.setField(emailValidator, "prohibitedDomains", prohibitedDomains);
    }

    @Test
    void whenEmailIsValidThenReturnTrue() {
        User user = new User();
        user.setId(123L);
        user.setEmail("user1@gmail.com");

        assertTrue(emailValidator.isValid(user.getEmail()));
    }

    @Test
    void whenEmailIsNotValidThenReturnFalse() {
        User user = new User();
        user.setId(123L);
        user.setEmail("user1g.c");

        assertFalse(emailValidator.isValid(user.getEmail()));
    }

    @Test
    void whenEmailIsNotAllowedThenReturnFalse() {
        User user = new User();
        user.setId(123L);
        user.setEmail("ololffffo@mail.ru");

        assertFalse(emailValidator.isValid(user.getEmail()));
    }
}