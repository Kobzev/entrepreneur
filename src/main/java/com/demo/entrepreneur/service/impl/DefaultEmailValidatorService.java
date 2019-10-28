package com.demo.entrepreneur.service.impl;

import com.demo.entrepreneur.service.EmailValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;

@Slf4j
@Service
public class DefaultEmailValidatorService implements EmailValidatorService {

    @Value("#{'${mail.prohibited.domains}'.split(',')}")
    private List<String> prohibitedDomains;

    @Override
    public boolean isValid(String email){
        return isEmailValid(email) && isDomainAllowed(email);
    }

    private boolean isEmailValid(String email) {
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException e) {
            log.info("Email {} is not valid", email);
            return false;
        }
        return true;
    }

    private boolean isDomainAllowed(String email) {
        for(String str : prohibitedDomains) {
            String pattern = "(.*)@" + str + "(.*)";
            if (email.matches(pattern)){
                log.info("{} - email domain is not allowed", email);
                return false;
            }
        }
        return true;
    }
}
