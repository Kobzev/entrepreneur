package com.demo.entrepreneur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class EmailValidatorService {

    private EmailSenderService emailSenderService;

    @Autowired
    public EmailValidatorService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public void validateEmail(String email){
        checkIfEmailIsValid(email);

       /* User user = userService.getUserByEmail(email);
        if (user != null) {
            throw new RuntimeException("User with such email already exists");
        }*/

        sendVerificationEmail(email);
    }

    private void sendVerificationEmail(String email){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Entrepreneur app");
        msg.setText("Welcome to entrepreneur app!");
        emailSenderService.sendEmail(msg);
    }

    private void checkIfEmailIsValid(String email){
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException e) {
            throw new RuntimeException("Email address is not valid");
        }
    }
}
