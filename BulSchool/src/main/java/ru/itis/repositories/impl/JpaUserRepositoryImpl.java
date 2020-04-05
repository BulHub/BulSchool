package ru.itis.repositories.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public User find(String email) {
        TypedQuery<User> q = entityManager.createQuery(
                "select u from User u where u.email = :email", User.class
        );
        q.setParameter("email", email);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery(
                "select u from User u", User.class
        ).getResultList();
    }

    @Override
    @Transactional
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(User entity) {
        entityManager.merge(entity);
    }

    @Override
    public void update(User entity) {
        //TODO: Realization
    }

    @Override
    public User findByToken(String token) {
        TypedQuery<User> q = entityManager.createQuery(
                "select u from User u where u.token = :token", User.class
        );
        q.setParameter("token", token);
        return q.getResultList().stream().findAny().orElse(null);
    }
}
