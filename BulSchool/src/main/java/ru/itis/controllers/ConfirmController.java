package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.services.UserService;

@Controller
@RequestMapping("/token")
public class ConfirmController {

    private final UserService userService;

    public ConfirmController(UserService userService) {
        this.userService = userService;
    }

    //FIXME: method update() doesn't work right because confirm() method doesn't work in one session!
    //FIXME: also need to look at the correct implementation of user (User.class) relationships and roles (Role.class)

    @GetMapping("/{token}")
    public String checkToken(@PathVariable("token") String token){
        if (userService.confirm(token)) {
            return "developers";
        }
        return "/signIn";
    }
}
