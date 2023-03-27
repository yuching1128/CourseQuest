package com.vt.coursequest.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author: EugeneFeng
 * @date: 3/1/23 10:14 AM
 * @description: some desc
 */

@RestController
public class MainController {

    @RequestMapping("/index")
    @ResponseBody
    @CrossOrigin
    public String index(){
        return "Welcome to the CourseQuest!!";
    }


    @GetMapping("/error")
    @CrossOrigin
    public int error(){
        int i = 9/0;
        return i;
    }
}
