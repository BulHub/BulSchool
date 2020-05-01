package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.itis.models.Course;
import ru.itis.models.User;
import ru.itis.models.UserCourse;
import ru.itis.repositories.CourseRepository;
import ru.itis.repositories.UserCourseRepository;
import ru.itis.repositories.UserRepository;
import ru.itis.services.CourseService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final UserCourseRepository userCourseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             @Qualifier("jdbcTemplateUserRepositoryImpl") UserRepository userRepository,
                             UserCourseRepository userCourseRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.userCourseRepository = userCourseRepository;
    }

    @Override
    public void checkRegistration(Model model, String courseName, HttpSession session) {
        String email = (String) session.getAttribute("email");
        User user = userRepository.find(email);
        Course course = courseRepository.findByName(courseName);
        UserCourse userCourse = userCourseRepository.findByUserId(user.getId());
        if (userCourse != null){
            if (userCourse.getCourseId().equals(course.getId())){
                model.addAttribute("something", "Enter the course");
            }else{
                model.addAttribute("something", "Sign up");
            }
        }else{
            model.addAttribute("something", "Sign up");
        }
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findByName(String name) {
        return courseRepository.findByName(name);
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
