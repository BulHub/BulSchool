package ru.itis.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.List;

@Component
public class JdbcTemplateUserRepositoryImpl implements UserRepository {

    private static final String SQL_DELETE_BY_EMAIL = "DELETE FROM itis_user where email = ?";
    private static final String SQL_INSERT = "insert into itis_user(created, status, updated, email, nickname, password, token)" +
            " VALUES(?, ?, ?, ?, ?, ?, ?) ";
    private static final String SQL_SELECT_BY_EMAIL = "select * from itis_user where email = ?";
    private static final String SQL_SELECT_BY_TOKEN = "select * from itis_user where token = ?";
    private static final String SQL_SELECT_ALL = "select * from itis_user";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                SQL_SELECT_ALL,
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    @Override
    public User find(String email) {
        return jdbcTemplate.query(
                SQL_SELECT_BY_EMAIL,
                new Object[] { email },
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }

    @Override
    public User save(User user) {
        jdbcTemplate.update(
                SQL_INSERT,
                user.getCreated(), user.getStatus(), user.getUpdated(),
                user.getEmail(), user.getNickname(), user.getPassword(),
                user.getToken()
        );
        return user;
    }

    @Override
    public void delete(User entity) {
        //TODO: Realization
    }

    @Override
    public void update(User entity) {
        //TODO: Realization
    }

    @Override
    public User findByToken(String token) {
        return jdbcTemplate.query(
                SQL_SELECT_BY_TOKEN,
                new Object[] { token },
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }
}
