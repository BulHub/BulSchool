package ru.itis.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.models.Role;
import ru.itis.repositories.RoleRepository;

import java.util.List;

@Component
public class HibernateRoleRepositoryImpl implements RoleRepository {

    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.openSession();
    }

    @Autowired
    public HibernateRoleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role find(String name) {
        //language=HQL
        Query<Role> q = currentSession().
                createQuery("from Role where name = :name", Role.class);
        q.setParameter("name", name);
        return q.list().stream().findAny().orElse(null);
    }

    @Override
    public List<Role> findAll() {
        //language=HQL
        return currentSession().createQuery("from Role", Role.class).list();
    }

    @Override
    public Role save(Role entity) {
        currentSession().save(entity);
        return entity;
    }

    @Override
    public void delete(Role entity) {
        currentSession().delete(entity);
    }

    @Override
    public void update(Role entity) {
        currentSession().update(entity);
    }
}
