package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
