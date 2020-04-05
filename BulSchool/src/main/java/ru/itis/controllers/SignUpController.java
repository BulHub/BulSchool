package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.models.User;
import ru.itis.services.EmailService;
import ru.itis.services.RoleService;
import ru.itis.services.UserService;
import ru.itis.utils.Attributes;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final UserService userService;
    private final EmailService emailService;
    private final RoleService roleService;

    @Autowired
    public SignUpController(UserService userService, EmailService emailService, RoleService roleService) {
        this.userService = userService;
        this.emailService = emailService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getSignUp(ModelMap map){
        Attributes.addSuccessAttributes(map,"You have successfully logged in!");
        return "signUp";
    }

    @PostMapping
    public String signUp(User user){
        roleService.setRole(user);
        userService.register(user);
        emailService.sendConfirmation(user.getEmail(), user.getToken());
        return "redirect:/signIn";
    }
}
