package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.services.CourseService;
import ru.itis.utils.Attributes;
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

    @PostMapping("/java")
    public String courseJava(HttpSession session, ModelMap modelMap){
        check(session, modelMap, "Java Junior Developer");
        return "/java";
    }

    @GetMapping("/php")
    public String getCoursePHP(ModelMap modelMap, HttpSession session, Model model){
        Before.startPage(modelMap, session, model);
        courseService.checkRegistration(model, "PHP Junior Developer", session);
        return "php";
    }

    @PostMapping("/php")
    public String coursePHP(HttpSession session, ModelMap modelMap){
        check(session, modelMap, "PHP Junior Developer");
        return "/php";
    }

    @GetMapping("/python")
    public String getCoursePython(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        courseService.checkRegistration(model, "Python Junior Developer", session);
        return "python";
    }

    @PostMapping("/python")
    public String coursePython(HttpSession session, ModelMap modelMap){
        check(session, modelMap, "Python Junior Developer");
        return "/python";
    }

    @GetMapping("/database")
    public String getCourseDatabase(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        courseService.checkRegistration(model, "Database Junior Developer", session);
        return "database";
    }

    @PostMapping("/database")
    public String courseDatabase(HttpSession session, ModelMap modelMap){
        check(session, modelMap, "Database Junior Developer");
        return "/database";
    }

    @GetMapping("/iOs")
    public String getCourseIOs(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        courseService.checkRegistration(model, "iOS Junior Developer", session);
        return "iOS";
    }

    @PostMapping("/iOs")
    public String courseIOS(HttpSession session, ModelMap modelMap){
        check(session, modelMap, "iOS Junior Developer");
        return "/iOs";
    }

    @GetMapping("/net")
    public String getCourseNET(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        courseService.checkRegistration(model, ".NET Junior Developer", session);
        return "net";
    }

    @PostMapping("/net")
    public String courseNET(HttpSession session, ModelMap modelMap){
        check(session, modelMap, ".NET Junior Developer");
        return "/net";
    }

    private void check(HttpSession session, ModelMap modelMap, String courseName){
        boolean check = (boolean) session.getAttribute("check");
        if (check){
            Attributes.addErrorAttributes(modelMap,"The course has not begun!");
        }else{
            courseService.registration(session, courseName);
            Attributes.addSuccessAttributes(modelMap, "You have successfully registered for the course!");
            session.setAttribute("check", true);
        }
        modelMap.addAttribute("something", "Enter the course");
    }
}
