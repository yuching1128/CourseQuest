package com.vt.coursequestbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: EugeneFeng
 * @date: 3/3/23 5:02 PM
 * @description: some desc
 */

@RestController
public class CourseController {

    @GetMapping("/api/university/{universityid}/courses")
    public String getCourseList(@PathVariable String universityid) {
        return "getCourseList: " + universityid;
    }

    @GetMapping(" /api/university/{universityid}/courses/{courseid}")
    public String getCourseDetails(@PathVariable String courseid, String universityid) {
        return "getCourseDetails: " + "courseid: " + courseid + "universityid: " + universityid;
    }


}
