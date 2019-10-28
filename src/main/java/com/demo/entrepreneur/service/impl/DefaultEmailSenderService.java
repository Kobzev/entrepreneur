package com.demo.entrepreneur.service.impl;

import com.demo.entrepreneur.entity.ConfirmationToken;
import com.demo.entrepreneur.entity.User;
import com.demo.entrepreneur.repository.ConfirmationTokenRepository;
import com.demo.entrepreneur.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmailSenderService implements EmailSenderService {

    private JavaMailSender javaMailSender;

    private ConfirmationTokenRepository tokenRepository;

    @Autowired
    public DefaultEmailSenderService(JavaMailSender javaMailSender, ConfirmationTokenRepository tokenRepository) {
        this.javaMailSender = javaMailSender;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void sendVerificationEmail(User user){
        SimpleMailMessage msg = new SimpleMailMessage();
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        tokenRepository.save(confirmationToken);
        msg.setTo(user.getEmail());
        msg.setSubject("Entrepreneur app");
        msg.setText("Welcome to entrepreneur app!");
        msg.setText("Click here to confirm your account: " +
                "http://localhost:8080/rest/users/confirm-email?token=" + confirmationToken.getToken());
        javaMailSender.send(msg);
    }
}
