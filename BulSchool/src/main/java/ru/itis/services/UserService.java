package ru.itis.services;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.dto.AuthenticationRequestDto;
import ru.itis.models.User;
import ru.itis.transfer.UserForm;

import java.util.List;

public interface UserService {
    void register(User user);
    boolean signIn(AuthenticationRequestDto userForm, ModelMap modelMap);
    boolean confirm(String token);
    List<User> findAll();
    User find(String email);
    void delete(User user);
    void add(User user);
    void update(User user);
    User findByToken(String token);
}
