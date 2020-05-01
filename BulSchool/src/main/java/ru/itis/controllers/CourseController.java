package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.services.CourseService;
import ru.itis.utils.Before;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/java")
    public String getCourseJava(ModelMap modelMap, HttpSession session, Model model){
        Before.startPage(modelMap, session, model);
        courseService.checkRegistration(model, "Java Junior Developer", session);
        return "java";
    }

    @GetMapping("/php")
    public String getCoursePHP(ModelMap modelMap, HttpSession session, Model model){
        Before.startPage(modelMap, session, model);
        courseService.checkRegistration(model, "PHP Junior Developer", session);
        return "php";
    }

    @GetMapping("/python")
    public String getCoursePython(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        courseService.checkRegistration(model, "Python Junior Developer", session);
        return "python";
    }

    @GetMapping("/database")
    public String getCourseDatabase(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        courseService.checkRegistration(model, "Database Junior Developer", session);
        return "database";
    }

    @GetMapping("/iOs")
    public String getCourseIOs(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        courseService.checkRegistration(model, "iOS Junior Developer", session);
        return "iOS";
    }

    @GetMapping("/net")
    public String getCourseNET(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        courseService.checkRegistration(model, ".NET Junior Developer", session);
        return "net";
    }
}
