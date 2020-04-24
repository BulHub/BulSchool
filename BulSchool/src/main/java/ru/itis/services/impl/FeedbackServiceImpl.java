package ru.itis.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Feedback;
import ru.itis.repositories.FeedbackRepository;
import ru.itis.services.FeedbackService;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private static final Logger log = Logger.getLogger(FeedbackServiceImpl.class);

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback find(String email) {
        return feedbackRepository.find(email);
    }

    @Override
    public void delete(Feedback entity) {
        feedbackRepository.delete(entity);
    }

    @Override
    public void add(Feedback entity) {
        feedbackRepository.save(entity);
    }
}
