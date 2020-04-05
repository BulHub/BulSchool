package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.dto.AuthenticationRequestDto;
import ru.itis.services.UserService;
import ru.itis.utils.Attributes;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    private final UserService userService;

    @Autowired
    public SignInController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignIn(ModelMap modelMap){
        Attributes.addSuccessAttributes(modelMap,"You have successfully logged in!");
        return "signIn";
    }

    @PostMapping
    public String signIn(AuthenticationRequestDto authenticationRequestDto, ModelMap modelMap){
        if (userService.signIn(authenticationRequestDto, modelMap)){
            return "developers";
        }else{
            return "/signIn";
        }
    }
}
