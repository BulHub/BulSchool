package ru.itis.repositories;

import ru.itis.models.User;

public interface UserRepository extends CrudRepository<Long, User> {
    User findByToken(String token);
}
