package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Message;
import ru.itis.repositories.ChatRepository;
import ru.itis.services.ChatService;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public List<Message> findAll() {
        return chatRepository.findAll();
    }

    @Override
    public Message find(String message) {
        return chatRepository.find(message);
    }

    @Override
    public void delete(Message entity) {
        chatRepository.delete(entity);
    }

    @Override
    public void add(Message entity) {
        chatRepository.save(entity);
    }

    @Override
    public void update(Message entity) {
        chatRepository.update(entity);
    }

    @Override
    public List<Message> findByEmail(String email) {
        return chatRepository.findByEmail(email);
    }
}
