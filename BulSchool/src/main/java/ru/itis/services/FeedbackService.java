package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.models.Feedback;

import java.util.List;

@Service
public interface FeedbackService {
    List<Feedback> findAll();

    Feedback find(String email);

    void delete(Feedback entity);

    void add(Feedback entity);
}
