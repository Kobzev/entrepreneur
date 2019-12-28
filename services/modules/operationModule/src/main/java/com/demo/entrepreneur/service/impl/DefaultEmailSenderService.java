package com.demo.entrepreneur.service.impl;

import com.demo.entrepreneur.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmailSenderService implements EmailSenderService {

    private JavaMailSender javaMailSender;

    @Autowired
    public DefaultEmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendVerificationEmail(String email){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Entrepreneur app");
        msg.setText("Welcome to com.demo.entrepreneur app!");
        javaMailSender.send(msg);
    }
}
