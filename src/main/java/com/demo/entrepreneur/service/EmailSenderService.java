package com.demo.entrepreneur.service;

import com.demo.entrepreneur.entity.User;

public interface EmailSenderService {

    void sendVerificationEmail(User user);
}
