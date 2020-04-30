package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.models.Feedback;

@Service
public interface TelegramService {

    void sendMessageForFeedback(Feedback feedback);
}
