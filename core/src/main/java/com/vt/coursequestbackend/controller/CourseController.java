package com.vt.coursequestbackend.controller;

import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("This service is used to get the list of all the courses available in the university")
    @GetMapping("/api/university/{universityid}/courses")
    public String getCourseList(@PathVariable String universityid) {
        return "getCourseList: " + universityid;
    }

    @ApiOperation("This service is used to get a particular coursedetails")
    @GetMapping("/api/university/{universityid}/courses/{courseid}")
    public String getCourseDetails(@PathVariable String courseid,  @PathVariable String universityid) {
        return "getCourseDetails: " + "courseid: " + courseid + "universityid: " + universityid;
    }

    @ApiOperation("This service is used to get the list of all the degree types available in the university\n")
    @GetMapping("/api/university/{universityid}/degreetypes")
    public String getDegreeList(@PathVariable String universityid) {
        return "getDegreeList: " + "universityid: " + universityid;
    }



}
