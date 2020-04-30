package ru.itis.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendConfirmation(String toEmail, String token);
}
