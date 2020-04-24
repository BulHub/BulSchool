package ru.itis.repositories.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Message;
import ru.itis.repositories.ChatRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class JpaChatRepositoryImpl implements ChatRepository {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public Message find(String email) {
        TypedQuery<Message> q = entityManager.createQuery(
                "select u from Message u where u.email = :email", Message.class
        );
        q.setParameter("email", email);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<Message> findAll() {
        return entityManager.createQuery(
                "select u from Message u", Message.class
        ).getResultList();
    }

    @Override
    @Transactional
    public Message save(Message entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(Message entity) {

    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public List<Message> findByEmail(String email) {
        TypedQuery<Message> q = entityManager.createQuery(
                "select u from Message u where u.email = :email", Message.class
        );
        q.setParameter("email", email);
        return q.getResultList();
    }
}
