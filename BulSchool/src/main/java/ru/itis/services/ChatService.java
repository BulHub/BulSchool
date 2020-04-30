package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.models.Message;

import java.util.List;

@Service
public interface ChatService {
    List<Message> findAll();

    Message find(String message);

    void delete(Message entity);

    void add(Message entity);

    void update(Message entity);

    List<Message> findByEmail(String email);
}
