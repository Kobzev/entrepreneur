package com.demo.entrepreneur.service;

import com.demo.entrepreneur.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmailValidatorServiceTest {

    @InjectMocks
    private EmailValidatorService emailValidator;

    @Mock
    private EmailSenderService emailSenderService;


    @Test
    void whenEmailIsValidThenVoidReturn() {
        User user = new User();
        user.setId(123L);
        user.setEmail("user1@gmail.com");

        assertDoesNotThrow(() -> emailValidator.checkIfEmailIsValid(user));
    }

    @Test
    void whenEmailIsNotValidThenThrowException() {
        User user = new User();
        user.setId(123L);
        user.setEmail("user1g.c");

        assertThrows(RuntimeException.class, () -> emailValidator.checkIfEmailIsValid(user));
    }

    @Test
    void testSendVerificationEmail() {
        User user = new User();
        user.setId(123L);
        user.setEmail("user1@gmail.com");

        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject("Entrepreneur app");
        smm.setText("Welcome to entrepreneur app!");
        smm.setTo(user.getEmail());

        emailValidator.sendVerificationEmail(user);

        verify(emailSenderService, atLeastOnce()).sendEmail(smm);
    }

}