package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Course;
import ru.itis.repositories.CourseRepository;
import ru.itis.services.CourseService;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> find(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public void delete(Course entity) {
        courseRepository.delete(entity);
    }

    @Override
    public void add(Course entity) {
        courseRepository.save(entity);
    }
}
