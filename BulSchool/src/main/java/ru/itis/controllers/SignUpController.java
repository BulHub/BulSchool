package ru.itis.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.itis.dto.CaptchaResponseDto;
import ru.itis.models.User;
import ru.itis.services.EmailService;
import ru.itis.services.UserService;
import ru.itis.utils.Attributes;
import ru.itis.utils.UserValidator;

import java.util.Collections;
import java.util.Objects;

@Controller
@RequestMapping("/signUp")
public class SignUpController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${recaptcha.secret}")
    private String secret;

    private final UserService userService;
    private final EmailService emailService;
    private final UserValidator userValidator;
    private final RestTemplate restTemplate;
    private static final Logger log = Logger.getLogger(SignUpController.class);


    @Autowired
    public SignUpController(UserService userService, EmailService emailService, UserValidator userValidator, RestTemplate restTemplate) {
        this.userService = userService;
        this.emailService = emailService;
        this.userValidator = userValidator;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getSignUp(ModelMap map) {
        Attributes.addSuccessAttributes(map, "You have successfully logged in!");
        return "signUp";
    }

    @PostMapping
    public String signUp(User user, BindingResult result, ModelMap model,
                         @RequestParam("g-recaptcha-response") String captchaResponse) {
        String error = "";
        userValidator.validate(user, result);
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        if (!Objects.requireNonNull(response).isSuccess()) {
            error += "Fill captcha! ";
        }
        if (result.hasErrors()) {
            error += "This mail is already taken! ";
        }
        if (error.isEmpty()) {
            Attributes.addSuccessAttributes(model, "Are you registered! A confirmation email will be sent to your email!");
            userService.register(user, captchaResponse);
            emailService.sendConfirmation(user.getEmail(), user.getToken());
            log.info("User registered: " + user.getEmail());
        }else{
            Attributes.addErrorAttributes(model, error);
        }
        return "/signUp";
    }
}
