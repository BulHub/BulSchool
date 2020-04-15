package ru.itis.services;

import ru.itis.models.Message;

import java.util.List;

public interface ChatService {
    List<Message> findAll();

    Message find(String message);

    void delete(Message entity);

    void add(Message entity);

    void update(Message entity);

    List<Message> findByEmail(String email);
}
