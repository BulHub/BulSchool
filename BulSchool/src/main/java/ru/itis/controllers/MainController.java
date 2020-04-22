package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.models.Feedback;
import ru.itis.services.FeedbackService;
import ru.itis.utils.Attributes;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private final FeedbackService feedbackService;
    private final HttpSession session;

    @Autowired
    public MainController(FeedbackService feedbackService, HttpSession session) {
        this.feedbackService = feedbackService;
        this.session = session;
    }

    @GetMapping("/developers")
    public String getDevelopers(ModelMap modelMap, Model model) {
        Attributes.addSuccessAttributes(modelMap,"Successfully visited the page");
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
        return "developers";
    }

    @GetMapping("/feedback")
    public String getFeedback(ModelMap modelMap, Model model) {
        Attributes.addSuccessAttributes(modelMap,"Successfully visited the page");
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
        return "feedback";
    }

    @PostMapping("/feedback")
    public String feedback(ModelMap modelMap, Feedback feedback){
        feedbackService.add(feedback);
        Attributes.addSuccessAttributes(modelMap,"Success!");
        return "/feedback";
    }

    @GetMapping("/chat")
    public String getChat(ModelMap modelMap, Model model) {
        Attributes.addSuccessAttributes(modelMap, "Success!");
        String email = (String) session.getAttribute("email");
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
        model.addAttribute("email", email);
        return "chat";
    }

    @GetMapping("/logout")
    public String getExit() {
        session.removeAttribute("email");
        session.removeAttribute("nickname");
        return "redirect:/signIn";
    }

    @GetMapping("/home")
    public String getHome(ModelMap modelMap, Model model) {
        Attributes.addSuccessAttributes(modelMap,"Successfully visited the page");
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
        return "home";
    }

    @GetMapping("/courses")
    public String getCourses(ModelMap modelMap, Model model) {
        Attributes.addSuccessAttributes(modelMap,"Successfully visited the page");
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
        return "courses";
    }

    @GetMapping("/course-single")
    public String getCourse(ModelMap modelMap, Model model){
        Attributes.addSuccessAttributes(modelMap,"Successfully visited the page");
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
        return "course-single";
    }
}
