package ru.itis.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.List;

@Component
public class HibernateUserRepositoryImpl implements UserRepository {

    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.openSession();
    }

    @Autowired
    public HibernateUserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User find(String email) {
        //language=HQL
        Query<User> q = currentSession().
                createQuery("from User where email = :email", User.class);
        q.setParameter("email", email);
        return q.list().stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        //language=HQL
        return currentSession().createQuery("from User", User.class).list();
    }

    @Override
    public User save(User entity) {
        currentSession().save(entity);
        return entity;
    }

    @Override
    public void delete(User entity) {
        currentSession().delete(entity);
    }

    @Override
    public void update(User entity) {
        currentSession().update(entity);
    }

    @Override
    public User findByToken(String token) {
        Query<User> q = currentSession().
                createQuery("from User where token = :token", User.class);
        q.setParameter("token", token);
        return q.list().stream().findAny().orElse(null);
    }

    @Override
    public void update(String password, String email) {

    }
}
