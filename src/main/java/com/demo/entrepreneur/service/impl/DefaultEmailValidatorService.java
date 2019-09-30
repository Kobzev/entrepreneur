package com.demo.entrepreneur.service.impl;

import com.demo.entrepreneur.service.EmailValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Slf4j
@Service
public class DefaultEmailValidatorService implements EmailValidatorService {

    @Override
    public boolean checkIfEmailIsValid(String email){
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException e) {
            log.info("Email {} is not valid", email);
            return false;
        }
        return true;
    }

}
