package ru.itis.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

@Repository
interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
    User findByEmail(String email);
}
