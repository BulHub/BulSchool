package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.PasswordDto;
import ru.itis.services.UserService;
import ru.itis.utils.Attributes;
import ru.itis.utils.Before;

import javax.servlet.http.HttpSession;

@Controller
public class SettingsController {

    private final UserService userService;

    @Autowired
    public SettingsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/changePassword")
    public String getChangePassword(ModelMap modelMap,HttpSession session, Model model){
        Before.startPage(modelMap, session, model);
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(PasswordDto passwordDto, ModelMap model, HttpSession session){
        userService.changePassword(passwordDto, model, session);
        return "changePassword";
    }
}
