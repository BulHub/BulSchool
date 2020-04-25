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

import javax.servlet.http.HttpSession;

@Controller
public class SettingsController {

    private final UserService userService;
    private final HttpSession session;

    @Autowired
    public SettingsController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @GetMapping("/changePassword")
    public String getChangePassword(ModelMap modelMap, Model model){
        Attributes.addSuccessAttributes(modelMap,"Successfully visited the page");
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(PasswordDto passwordDto, ModelMap model, HttpSession session){
        userService.changePassword(passwordDto, model, session);
        return "changePassword";
    }
}
