package ru.itis.services;

import org.springframework.ui.ModelMap;
import ru.itis.dto.AuthenticationRequestDto;
import ru.itis.dto.PasswordDto;
import ru.itis.models.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    void register(User user);

    boolean signIn(AuthenticationRequestDto userForm, ModelMap modelMap, HttpSession session);

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
