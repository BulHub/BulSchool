package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.UserCourse;

import java.util.Optional;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    Optional<UserCourse> findByCourseId(Long courseId);

    Optional<UserCourse> findByUserId(Long userId);
}
