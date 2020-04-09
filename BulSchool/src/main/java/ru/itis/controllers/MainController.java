package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.models.Feedback;
import ru.itis.services.EmailService;
import ru.itis.services.FeedbackService;
import ru.itis.utils.Attributes;

@Controller
public class MainController {

    private final FeedbackService feedbackService;
    private final EmailService emailService;

    @Autowired
    public MainController(FeedbackService feedbackService, EmailService emailService) {
        this.feedbackService = feedbackService;
        this.emailService = emailService;
    }

    @GetMapping("/developers")
    public String getDevelopers(ModelMap modelMap){
        Attributes.addSuccessAttributes(modelMap,"Successfully visited the page");
        return "developers";
    }

    @GetMapping("/feedback")
    public String getFeedback(ModelMap modelMap){
        Attributes.addSuccessAttributes(modelMap,"Successfully visited the page");
        return "feedback";
    }

    @PostMapping("/feedback")
    public String feedback(ModelMap modelMap, Feedback feedback){
        feedbackService.add(feedback);
        //TODO:
        Attributes.addSuccessAttributes(modelMap,"Success!");
        return "/feedback";
    }

    @GetMapping("/quickSupport")
    public String getQuickSupport() {
        return "quickSupport";
    }
}
