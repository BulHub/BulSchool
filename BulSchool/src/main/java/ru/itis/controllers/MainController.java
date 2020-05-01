package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.models.Feedback;
import ru.itis.models.User;
import ru.itis.services.FeedbackService;
import ru.itis.services.TelegramService;
import ru.itis.services.UserService;
import ru.itis.utils.Attributes;
import ru.itis.utils.Before;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private final FeedbackService feedbackService;
    private final UserService userService;
    private final TelegramService telegramService;

    @Autowired
    public MainController(FeedbackService feedbackService, UserService userService,
                          TelegramService telegramService) {
        this.feedbackService = feedbackService;
        this.userService = userService;
        this.telegramService = telegramService;
    }

    @GetMapping("/developers")
    public String getDevelopers(ModelMap modelMap,HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        return "developers";
    }

    @GetMapping("/feedback")
    public String getFeedback(ModelMap modelMap,HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        return "feedback";
    }

    @PostMapping("/feedback")
    public String feedback(ModelMap modelMap, HttpSession session, Feedback feedback){
        User user = userService.find((String) session.getAttribute("email"));
        feedback.setOwner_id(user.getId());
        feedback.setProcessed(false);
        feedbackService.add(feedback);
        telegramService.sendMessageForFeedback(feedback);
        Attributes.addSuccessAttributes(modelMap,"Success!");
        return "/feedback";
    }

    @GetMapping("/chat")
    public String getChat(ModelMap modelMap,HttpSession session, Model model) {
        Attributes.addSuccessAttributes(modelMap, "Success!");
        String email = (String) session.getAttribute("email");
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
        model.addAttribute("email", email);
        return "chat";
    }

    @GetMapping("/logout")
    public String getExit(HttpSession session) {
        session.removeAttribute("email");
        session.removeAttribute("nickname");
        return "redirect:/signIn";
    }

    @GetMapping("/home")
    public String getHome(ModelMap modelMap,HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        return "home";
    }

    @GetMapping("/courses")
    public String getCourses(ModelMap modelMap,HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        return "courses";
    }

    @GetMapping("/course-single")
    public String getCourse(ModelMap modelMap,HttpSession session, Model model){
        Before.startPage(modelMap, session, model);
        return "/course-single";
    }

    @GetMapping("/aboutUs")
    public String getAboutUs(ModelMap modelMap,HttpSession session, Model model){
        Before.startPage(modelMap, session, model);
        return "about";
    }

    @GetMapping("/admissions")
    public String getAdmissions(ModelMap modelMap,HttpSession session, Model model){
        Before.startPage(modelMap, session, model);
        return "admissions";
    }
}