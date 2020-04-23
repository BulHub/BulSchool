package ru.itis.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.itis.models.Feedback;
import ru.itis.repositories.FeedbackRepository;

import java.util.List;

@Component
public class JdbcTemplateFeedbackRepositoryImpl implements FeedbackRepository {

    private static final String SQL_DELETE_BY_EMAIL = "DELETE FROM feedback where email = ?";
    //language=SQL
    private static final String SQL_INSERT = "insert into feedback(email, firstname, lastname, message, owner_id, telephone) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_EMAIL = "select * from feedback where email = ?";
    private static final String SQL_SELECT_ALL = "select * from feedback";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateFeedbackRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Feedback find(String email) {
        return jdbcTemplate.query(
                SQL_SELECT_BY_EMAIL,
                new Object[] { email },
                new BeanPropertyRowMapper<>(Feedback.class)
        ).stream().findAny().orElse(null);
    }

    @Override
    public List<Feedback> findAll() {
        return jdbcTemplate.query(
                SQL_SELECT_ALL,
                new BeanPropertyRowMapper<>(Feedback.class)
        );
    }

    @Override
    public Feedback save(Feedback entity) {
        jdbcTemplate.update(
                SQL_INSERT,
                entity.getEmail(), entity.getFirstName(), entity.getLastName(),
                entity.getMessage(), entity.getOwner_id(), entity.getTelephone()
        );
        return entity;
    }

    @Override
    public void delete(Feedback entity) {
        //TODO: Realization
    }

    @Override
    public void update(Feedback entity) {
        //TODO: Realization
    }
}
