package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.services.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/token")
public class ConfirmController {

    private final UserService userService;

    public ConfirmController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{token}")
    public String checkToken(@PathVariable("token") String token, ModelMap model, HttpSession session){
        if (userService.confirm(token, model, session)) {
            return "redirect:/developers";
        }
        return "/signIn";
    }
}
