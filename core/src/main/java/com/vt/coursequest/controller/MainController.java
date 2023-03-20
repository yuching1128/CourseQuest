package com.vt.coursequest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: EugeneFeng
 * @date: 3/1/23 10:14 AM
 * @description: some desc
 */

@RestController
public class MainController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "Welcome to the CourseQuest!!";
    }


    @GetMapping("/error")
    public int error(){
        int i = 9/0;
        return i;
    }
}
