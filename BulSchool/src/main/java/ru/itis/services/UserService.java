package ru.itis.services;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import ru.itis.dto.AuthenticationRequestDto;
import ru.itis.dto.PasswordDto;
import ru.itis.models.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public interface UserService {
    void register(User user, String captchaResponse);

    boolean signIn(AuthenticationRequestDto userForm, ModelMap modelMap, HttpSession session, String captchaResponse);

    boolean confirm(String token, ModelMap model, HttpSession session);

    List<User> findAll();

    User find(String email);

    void delete(User user);

    void add(User user);

    void updateStatus(User user);

    User findByToken(String token);

    void updatePassword(String password, String email);

    void changePassword(PasswordDto passwordDto, ModelMap model, HttpSession session);
}
