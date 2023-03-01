package com.vt.coursequestbackend.controller;

import com.vt.coursequestbackend.entity.Course;
import com.vt.coursequestbackend.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: EugeneFeng
 * @date: 3/1/23 10:14 AM
 * @description: some desc
 */

@Controller
public class MainController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "Welcome to the CourseQuest!";
    }


    @RequestMapping("/course")
    @ResponseBody
    public Course course() {
        Course course = new Course();
        course.setId(1);
        course.setName("Software Engineering");
        course.setCrnNumber("CRN123456");
        return course;
    }

    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setId(1);
        user.setPhone("8623579779");
        user.setLastName("Feng");
        user.setFirstName("Yuechen");
        return user;
    }
}
