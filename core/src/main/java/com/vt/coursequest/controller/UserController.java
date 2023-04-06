package com.vt.coursequest.controller;

import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.Major;
import com.vt.coursequest.entity.University;
import com.vt.coursequest.entity.User;
import com.vt.coursequest.service.UserDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author: EugeneFeng
 * @date: 3/30/23 11:26 PM
 * @description: some desc
 */
@RestController
public class UserController {
    @Autowired
    private UserDataService uds;

    @ApiOperation("This service is used to get a specific user's profile info")
    @GetMapping("/api/user/{userId}/profile")
    public ResponseEntity<Optional<User>> getUserProfile(@PathVariable String userId) {
        return new ResponseEntity<>(uds.findUser(Integer.parseInt(userId)), HttpStatus.OK);
    }

    @ApiOperation("This service is used to get all universities")
    @GetMapping("/api/university/types")
    public ResponseEntity<List<University>> getAllUniversity() {
        return new ResponseEntity<>(uds.findAllUniversity(), HttpStatus.OK);
    }

    @ApiOperation("This service is used to add user's university")
    @PostMapping("/api/user/university")
    public ResponseEntity<User> addUserUniversity(@RequestParam Integer userId, @RequestParam Integer universityId) throws Exception {
        return new ResponseEntity<>(uds.createUserUniversity(userId, universityId), HttpStatus.OK);
    }

    @ApiOperation("This service is used to add user's degree")
    @PostMapping("/api/user/degree")
    public ResponseEntity<User> addUserDegree(@RequestParam Integer userId, @RequestParam Integer degreeId) throws Exception {
        return new ResponseEntity<>(uds.createUserDegree(userId, degreeId), HttpStatus.OK);
    }

    @ApiOperation("This service is used to get all majors")
    @GetMapping("/api/major/types")
    public ResponseEntity<List<Major>> getAllMajor() {
        return new ResponseEntity<>(uds.findAllMajor(), HttpStatus.OK);
    }

    @ApiOperation("This service is used to add user's major")
    @PostMapping("/api/user/major")
    public ResponseEntity<User> addUserMajor(@RequestParam Integer userId, @RequestParam Integer majorId) throws Exception {
        return new ResponseEntity<>(uds.createUserMajor(userId, majorId), HttpStatus.OK);
    }


    @ApiOperation("This service is used to add user's taken courses")
    @PostMapping("/api/user/course")
    public ResponseEntity<User> addUserTakenCourse(@RequestParam Integer userId, @RequestBody List<Course> courseList) throws Exception {
        return new ResponseEntity<>(uds.createTakenCourse(userId, courseList), HttpStatus.OK);
    }

    @ApiOperation("This service is used to add user's interested courses")
    @PostMapping("/api/user/interested")
    public ResponseEntity<User> addUserInterestedCourse(@RequestParam Integer userId, @RequestBody List<Course> courseList) throws Exception {
        return new ResponseEntity<>(uds.createInterestedCourse(userId, courseList), HttpStatus.OK);
    }

    @PostMapping("/api/user/concentration")
    public ResponseEntity<User> addUserConcentration(@RequestParam Integer userId, @RequestParam Integer majorId) {
        return new ResponseEntity<>(uds.createConcentration(userId, majorId), HttpStatus.OK);
    }




}
