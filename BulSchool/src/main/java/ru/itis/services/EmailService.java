package ru.itis.services;

public interface EmailService {
    void sendConfirmation(String toEmail, String token);
}
