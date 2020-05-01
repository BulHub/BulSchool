package ru.itis.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.itis.models.Course;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface CourseService {
    void checkRegistration(Model model, String courseName, HttpSession session);

    List<Course> findAll();

    Course findByName(String name);

    void delete(Course entity);

    void add(Course entity);
}
