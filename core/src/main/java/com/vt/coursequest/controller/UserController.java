package com.vt.coursequest.controller;

import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.Department;
import com.vt.coursequest.entity.University;
import com.vt.coursequest.entity.User;
import com.vt.coursequest.interceptor.UserDetailsFromGoogle;
import com.vt.coursequest.service.UserDataService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * @author: EugeneFeng
 * @date: 3/30/23 11:26 PM
 * @description: some desc
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserDataService uds;

    @Autowired
    private HttpSession session;

    @ApiOperation("This service is used to get a specific user's profile info")
    @GetMapping("/api/user/profile")
    public ResponseEntity<Optional<User>> getUserProfile() {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(uds.findUser(Integer.parseInt(String.valueOf(user.getId()))), HttpStatus.OK);
    }

    @ApiOperation("This service is used to get all universities")
    @GetMapping("/api/university/types")
    public ResponseEntity<List<University>> getAllUniversity() {
        return new ResponseEntity<>(uds.findAllUniversity(), HttpStatus.OK);
    }

    @ApiOperation("This service is used to add user's university")
    @PostMapping("/api/user/university")
    public ResponseEntity<User> addUserUniversity(@RequestParam Integer universityId) throws Exception {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(uds.createUserUniversity(user.getId(), universityId), HttpStatus.OK);
    }

    @ApiOperation("This service is used to add user's degree")
    @PostMapping("/api/user/degree")
    public ResponseEntity<User> addUserDegree(@RequestParam Integer degreeId) throws Exception {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(uds.createUserDegree(user.getId(), degreeId), HttpStatus.OK);
    }

    @ApiOperation("This service is used to get all majors")
    @GetMapping("/api/major/types")
    public ResponseEntity<List<Department>> getAllDepartment() {
        return new ResponseEntity<>(uds.findAllMajor(), HttpStatus.OK);
    }

    @ApiOperation("This service is used to add user's major")
    @PostMapping("/api/user/major")
    public ResponseEntity<User> addUserDepartment(@RequestParam Integer majorId) throws Exception {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(uds.createUserDepartment(user.getId(), majorId), HttpStatus.OK);
    }

    @ApiOperation("This service is used to add user's taken courses")
    @PostMapping("/api/user/course")
    public ResponseEntity<User> addUserTakenCourse(@RequestBody List<Course> courseList) throws Exception {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(uds.createTakenCourse(user.getId(), courseList), HttpStatus.OK);
    }

    @ApiOperation("This service is used to add user's interested courses")
    @PostMapping("/api/user/interested")
    public ResponseEntity<User> addUserInterestedCourse(@RequestBody List<Course> courseList) throws Exception {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(uds.createInterestedCourse(user.getId(), courseList), HttpStatus.OK);
    }

    @ApiOperation("This service is used to add user's concentration")
    @PostMapping("/api/user/concentration")
    public ResponseEntity<User> addUserConcentration(@RequestParam String concentration) {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(uds.createConcentration(user.getId(), concentration), HttpStatus.OK);
    }

    @ApiOperation("This service is used to add user's mentor courses")
    @PostMapping("api/user/mentorCourse")
    public ResponseEntity<User> addUserMentorCourse(@RequestBody List<Course> courseList) {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(uds.createMentorCourse(user.getId(), courseList), HttpStatus.OK);
    }
}
