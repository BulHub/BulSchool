package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.utils.Before;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/course")
public class CourseController {

    @GetMapping("/java")
    public String getCourseJava(ModelMap modelMap, HttpSession session, Model model){
        Before.startPage(modelMap, session, model);
        return "java";
    }

    @GetMapping("/php")
    public String getCoursePHP(ModelMap modelMap, HttpSession session, Model model){
        Before.startPage(modelMap, session, model);
        return "php";
    }

    @GetMapping("/python")
    public String getCoursePython(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        return "python";
    }

    @GetMapping("/database")
    public String getCourseDatabase(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        return "database";
    }

    @GetMapping("/iOs")
    public String getCourseIOs(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        return "iOS";
    }

    @GetMapping("/net")
    public String getCourseNET(ModelMap modelMap, HttpSession session, Model model) {
        Before.startPage(modelMap, session, model);
        return "net";
    }
}
