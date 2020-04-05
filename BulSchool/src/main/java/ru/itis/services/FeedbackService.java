package ru.itis.services;

import ru.itis.models.Feedback;
import ru.itis.models.Role;

import java.util.List;

public interface FeedbackService {
    List<Feedback> findAll();
    Feedback find(String email);
    void delete(Feedback entity);
    void add(Feedback entity);
}
