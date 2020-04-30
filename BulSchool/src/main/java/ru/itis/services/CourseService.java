package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.models.Course;

import java.util.List;
import java.util.Optional;

@Service
public interface CourseService {
    List<Course> findAll();

    Optional<Course> find(Long id);

    void delete(Course entity);

    void add(Course entity);
}
