package com.demo.entrepreneur.service;

import com.demo.entrepreneur.model.User;
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
        msg.setTo(user.getEmail());
        msg.setSubject("Entrepreneur app");
        msg.setText("Welcome to entrepreneur app!");
        emailSenderService.sendEmail(msg);
    }
}
