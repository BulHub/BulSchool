package ru.itis.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.models.User;
import ru.itis.services.EmailService;
import ru.itis.services.UserService;
import ru.itis.utils.Attributes;
import ru.itis.utils.UserValidator;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final UserService userService;
    private final EmailService emailService;
    private final UserValidator userValidator;
    private static final Logger log = Logger.getLogger(SignUpController.class);


    @Autowired
    public SignUpController(UserService userService, EmailService emailService, UserValidator userValidator) {
        this.userService = userService;
        this.emailService = emailService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String getSignUp(ModelMap map){
        Attributes.addSuccessAttributes(map,"You have successfully logged in!");
        return "signUp";
    }

    @PostMapping
    public String signUp(User user, BindingResult result, ModelMap model){
        userValidator.validate(user, result);
        if (result.hasErrors()){
            Attributes.addErrorAttributes(model, "This mail is already taken.");
            return "/signUp";
        }else {
            Attributes.addSuccessAttributes(model, "Are you registered! A confirmation email will be sent to your email!");
        }
        userService.register(user);
        emailService.sendConfirmation(user.getEmail(), user.getToken());
        log.info("User registered: " + user.getEmail());
        return "/signUp";
    }
}
