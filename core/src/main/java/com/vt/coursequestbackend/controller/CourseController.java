package com.vt.coursequestbackend.controller;

import com.vt.coursequestbackend.dao.CourseRepository;
import com.vt.coursequestbackend.dao.DegreeRepository;
import com.vt.coursequestbackend.entity.Course;
import com.vt.coursequestbackend.entity.Degree;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: EugeneFeng
 * @date: 3/3/23 5:02 PM
 * @description: some desc
 */

@RestController
public class CourseController {

    @Resource
    private CourseRepository courseRepository;

    @Resource
    private DegreeRepository degreeRepository;

    @ApiOperation("This service is used to get the list of all the courses available in the university")
    @GetMapping("/api/university/{universityid}/courses")
    public List<Course> getCourseList(@PathVariable String universityid) {
        return courseRepository.findByUniversityId(Integer.parseInt(universityid));
    }

    @ApiOperation("This service is used to get a particular coursedetails")
    @GetMapping("/api/university/{universityid}/courses/{courseid}")
    public List<Course> getCourseDetails(@PathVariable String courseid, @PathVariable String universityid) {
       return courseRepository.findByUniversityIdAndId(Integer.parseInt(universityid), Integer.parseInt(courseid));
    }

    @ApiOperation("This service is used to get the list of all the degree types available in the university\n")
    @GetMapping("/api/university/{universityid}/degreetypes")
    public List<Degree> getDegreeList(@PathVariable String universityid) {
        return degreeRepository.findAll();
    }



}
