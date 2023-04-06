package com.vt.coursequest.service.impl;

import com.vt.coursequest.dao.MajorRepository;
import com.vt.coursequest.dao.UniversityRepository;
import com.vt.coursequest.dao.UserRepository;
import com.vt.coursequest.entity.*;

import com.vt.coursequest.service.UserDataService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author: EugeneFeng
 * @date: 3/30/23 11:19 PM
 * @description: some desc
 */
@Service
public class UserDataServiceImpl implements UserDataService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UniversityRepository universityRepository;

    @Resource
    private MajorRepository majorRepository;

    @Override
    public Optional<User> findUser(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<University> findAllUniversity() {
        return universityRepository.getAllUniversity();
    }

    @Override
    public User createUserUniversity(Integer userId, Integer universityId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User curUser = user.get();
            University university = new University(universityId);
            curUser.setUniversity(university);
            return userRepository.save(curUser);
        }
        else throw new Exception("user not found with id " + userId + " ------------------------------");

    }

//    @Override
//    public User updateUserUniversity(Integer userId, Integer universityId) {
//        return null;
//    }

    @Override
    public User createUserDegree(Integer userId, Integer degreeId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User curUser = user.get();
            Degree degree = new Degree();
            degree.setId(degreeId);
            curUser.setDegree(degree);
            return userRepository.save(curUser);
        }
        else throw new Exception("user not found with id " + userId + " ------------------------------");
    }

    @Override
    public List<Major> findAllMajor() {
        return majorRepository.findAll();
    }

    @Override
    public User createUserMajor(Integer userId, Integer majorId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User curUser = user.get();
            Major major = new Major(majorId);
            curUser.setMajor(major);
            return userRepository.save(curUser);
        }
        else throw new Exception("user not found with id " + userId + " ------------------------------");
    }

    @Override
    public User createTakenCourse(Integer userId, List<Course> courseList) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User curUser = user.get();
            Set<Course> curTakenCourse = new HashSet<>(courseList);
            curUser.setCourse(curTakenCourse);
            return userRepository.save(curUser);
        }
        else throw new Exception("user not found with id " + userId + " ------------------------------");
    }

    @Override
    public List<Course> updateTakenCourse(Integer userId, List<Course> courseList) {
        return null;
    }

    @Override
    public User createInterestedCourse(Integer userId, List<Course> courseList) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User curUser = user.get();
            Set<Course> curInterestedCourse = new HashSet<>(courseList);
            curUser.setInterestedCourse(curInterestedCourse);
            return userRepository.save(curUser);
        }
        else throw new Exception("user not found with id " + userId + " ------------------------------");
    }

    @Override
    public List<Course> updateInterestedCourse(Integer userId, List<Course> courseList) {
        return null;
    }

    @Override
    public User createConcentration(Integer userId, List<Major> majorList) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User curUser = user.get();
            Set<Major> curMajor = new HashSet<>(majorList);
            curUser.setConcentration(curMajor);
            return userRepository.save(curUser);
        }
       return null;
    }
}
