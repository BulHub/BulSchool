package ru.itis.services;

import ru.itis.models.Feedback;

public interface TelegramService {

    void sendMessageForFeedback(Feedback feedback);
}
