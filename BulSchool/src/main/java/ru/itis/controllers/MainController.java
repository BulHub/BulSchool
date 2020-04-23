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
import ru.itis.services.UserService;
import ru.itis.utils.Attributes;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private final FeedbackService feedbackService;
    private final UserService userService;
    private final HttpSession session;

    @Autowired
    public MainController(FeedbackService feedbackService, UserService userService, HttpSession session) {
        this.feedbackService = feedbackService;
        this.userService = userService;
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
        User user = userService.find((String) session.getAttribute("email"));
        feedback.setOwner_id(user.getId());
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

    @GetMapping("/aboutUs")
    public String getAboutUs(ModelMap modelMap, Model model){
        Attributes.addSuccessAttributes(modelMap,"Successfully visited the page");
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
        return "about";
    }

    @GetMapping("/admissions")
    public String getAdmissions(ModelMap modelMap, Model model){
        Attributes.addSuccessAttributes(modelMap,"Successfully visited the page");
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
        return "admissions";
    }
}
