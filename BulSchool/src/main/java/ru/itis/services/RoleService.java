package ru.itis.services;

import ru.itis.models.Role;
import ru.itis.models.User;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role find(String name);

    void delete(Role user);

    void add(Role user);

    void setRole(User user);
}
