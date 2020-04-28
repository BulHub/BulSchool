package ru.itis.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;
import ru.itis.dto.AuthenticationRequestDto;
import ru.itis.dto.CaptchaResponseDto;
import ru.itis.dto.PasswordDto;
import ru.itis.models.Status;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.services.UserService;
import ru.itis.utils.Attributes;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${recaptcha.secret}")
    private String secret;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);


    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, @Qualifier("jdbcTemplateUserRepositoryImpl") UserRepository userRepository, RestTemplate restTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void register(User user, String captchaResponse) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setStatus(Status.INACTIVE);
        user.setToken(generateNewToken());
        user.setRole("USER");
        add(user);
    }

    private static String generateNewToken() {
        int length = 500;
        Random r = new Random();
        return r.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    @Override
    public boolean signIn(AuthenticationRequestDto userForm, ModelMap model, HttpSession session, String captchaResponse) {
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        if (!Objects.requireNonNull(response).isSuccess()) {
            Attributes.addErrorAttributes(model, "Fill captcha!");
            return false;
        }
        User user = find(userForm.getEmail());
        if (user == null || !passwordEncoder.matches(userForm.getPassword(), user.getPassword())) {
            log.info("User with this email and password could not log in: " + userForm.getEmail() + " and " + userForm.getPassword());
            Attributes.addErrorAttributes(model, "Wrong login or password!");
            return false;
        }
        if (user.getStatus() == Status.ACTIVE){
            Attributes.addSuccessAttributes(model, "Success!");
            session.setAttribute("email", user.getEmail());
            session.setAttribute("nickname", user.getNickname());
            log.info("User with this mail went to the site: " + userForm.getEmail());
            return true;
        }
        if (user.getStatus() == Status.INACTIVE){
            Attributes.addErrorAttributes(model, "Your account is inactive since you did not confirm your mail!");
            log.info("The user with this mail was not logged in as he did not confirm the mail: " + userForm.getEmail());
        }
        if (user.getStatus() == Status.BANNED){
            Attributes.addErrorAttributes(model, "Your account has been banned!");
            log.info("A user with this mail has not logged in as he is banned: " + userForm.getEmail());
        }
        if (user.getStatus() == Status.DELETED){
            Attributes.addErrorAttributes(model, "Your account has been deleted!");
            log.info("The user with this mail was not logged in as he was deleted: " + user.getEmail());
        }
        return false;
    }

    @Override
    @Transactional
    public boolean confirm(String token, ModelMap model, HttpSession session) {
        User user = findByToken(token);
        if (user != null) {
            userRepository.update(user);
            session.setAttribute("email", user.getEmail());
            session.setAttribute("nickname", user.getNickname());
            log.info("A user with this mail has confirmed it: " + user.getEmail());
            return true;
        } else {
            log.info("User attempt to confirm mail failed: " + token);
            return false;
        }
    }

    @Override
    public void changePassword(PasswordDto passwordDto, ModelMap model, HttpSession session){
        String email = (String) session.getAttribute("email");
        User user = find(email);
        if (!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())){
            Attributes.addErrorAttributes(model, "Wrong old password!");
            log.info("A user with this mail does not remember his old password: " + email);
        }else{
            updatePassword(passwordEncoder.encode(passwordDto.getNewPassword()), email);
            Attributes.addSuccessAttributes(model, "Success!");
            log.info("The password with this mail has changed the password: " + email);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User find(String email) {
        return userRepository.find(email);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateStatus(User user) {
        userRepository.update(user);
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public void updatePassword(String password, String email) {
        userRepository.update(password, email);
    }
}
