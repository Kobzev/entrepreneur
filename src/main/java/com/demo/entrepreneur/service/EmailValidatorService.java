package com.demo.entrepreneur.service;

import com.demo.entrepreneur.model.ConfirmationToken;
import com.demo.entrepreneur.model.User;
import com.demo.entrepreneur.model.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class EmailValidatorService {

    private EmailSenderService emailSenderService;
    private ConfirmationTokenRepository tokenRepository;

    @Autowired
    public EmailValidatorService(EmailSenderService emailSenderService,
            ConfirmationTokenRepository tokenRepository) {
        this.emailSenderService = emailSenderService;
        this.tokenRepository = tokenRepository;
    }

    public void checkIfEmailIsValid(User user){
        try {
            InternetAddress emailAddress = new InternetAddress(user.getEmail());
            emailAddress.validate();
        } catch (AddressException e) {
            throw new RuntimeException("Email address is not valid");
        }
    }

    public void sendVerificationEmail(User user){
        SimpleMailMessage msg = new SimpleMailMessage();
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        tokenRepository.save(confirmationToken);
        msg.setTo(user.getEmail());
        msg.setSubject("Entrepreneur app");
        msg.setText("Welcome to entrepreneur app!");
        msg.setText("Click here to confirm your account: " +
        "http://localhost:8080/rest/users/confirm-email?token=" + confirmationToken.getToken());
        emailSenderService.sendEmail(msg);
    }
}
