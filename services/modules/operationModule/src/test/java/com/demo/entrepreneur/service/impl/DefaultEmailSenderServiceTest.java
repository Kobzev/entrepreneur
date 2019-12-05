package com.demo.entrepreneur.service.impl;

import com.demo.entrepreneur.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultEmailSenderServiceTest {
    @InjectMocks
    private DefaultEmailSenderService emailSender;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void testSendVerificationEmailPositiveScenario() {
        User user = new User();
        user.setId(123L);
        user.setEmail("user1@gmail.com");

        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject("Entrepreneur app");
        smm.setText("Welcome to com.demo.entrepreneur app!");
        smm.setTo(user.getEmail());

        emailSender.sendVerificationEmail(user.getEmail());

        verify(javaMailSender, atLeastOnce()).send(smm);
    }
}