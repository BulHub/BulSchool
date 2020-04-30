package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.UserCourse;
import ru.itis.repositories.UserCourseRepository;
import ru.itis.services.UserCourseService;

import java.util.List;
import java.util.Optional;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    private final UserCourseRepository userCourseRepository;

    @Autowired
    public UserCourseServiceImpl(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }

    @Override
    public List<UserCourse> findAll() {
        return userCourseRepository.findAll();
    }

    @Override
    public Optional<UserCourse> findByUserId(Long userId) {
        return userCourseRepository.findByUserId(userId);
    }

    @Override
    public Optional<UserCourse> findByCourseId(Long courseId) {
        return userCourseRepository.findByCourseId(courseId);
    }

    @Override
    public void delete(UserCourse entity) {
        userCourseRepository.delete(entity);

    }

    @Override
    public void add(UserCourse entity) {
        userCourseRepository.save(entity);
    }
}
