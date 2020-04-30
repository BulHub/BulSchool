package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.models.Course;
import ru.itis.models.UserCourse;

import java.util.List;
import java.util.Optional;

@Service
public interface UserCourseService {
    List<UserCourse> findAll();

    Optional<UserCourse> findByUserId(Long userId);

    Optional<UserCourse> findByCourseId(Long courseId);

    void delete(UserCourse entity);

    void add(UserCourse entity);
}
