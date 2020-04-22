package ru.itis.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import ru.itis.dto.AuthenticationRequestDto;
import ru.itis.models.Status;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.services.UserService;
import ru.itis.utils.Attributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, @Qualifier("hibernateUserRepositoryImpl") UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setStatus(Status.INACTIVE);
        user.setToken(generateNewToken());
        add(user);
        log.info("IN register - user: {} successfully registered: ", user);
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
    public boolean signIn(AuthenticationRequestDto userForm, ModelMap model, HttpSession session) {
        User user = find(userForm.getEmail());
        if (user == null || !passwordEncoder.matches(userForm.getPassword(), user.getPassword())) {
            Attributes.addErrorAttributes(model, "Wrong login or password!");
            return false;
        }
        if (user.getStatus() == Status.ACTIVE){
            Attributes.addSuccessAttributes(model, "Success!");
            session.setAttribute("email", user.getEmail());
            session.setAttribute("nickname", user.getNickname());
            return true;
        }
        if (user.getStatus() == Status.INACTIVE){
            Attributes.addErrorAttributes(model, "Your account is inactive since you did not confirm your mail!");
        }
        if (user.getStatus() == Status.BANNED){
            Attributes.addErrorAttributes(model, "Your account has been banned!");
        }
        if (user.getStatus() == Status.DELETED){
            Attributes.addErrorAttributes(model, "Your account has been deleted!");
        }
        return false;
    }

    @Override
    @Transactional
    public boolean confirm(String token, HttpSession session) {
        User user = findByToken(token);
        if (user != null) {
            user.setStatus(Status.ACTIVE);
            userRepository.update(user);
            session.setAttribute("email", user.getEmail());
            session.setAttribute("nickname", user.getNickname());
            return true;
        } else {
            return false;
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
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }
}
