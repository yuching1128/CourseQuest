package com.vt.coursequest.service;

import com.vt.coursequest.entity.*;
import com.vt.coursequest.interceptor.UserDetailsFromGoogle;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * @author: EugeneFeng
 * @date: 3/30/23 11:01 PM
 * @description: some desc
 */
public interface UserDataService {
    /**
     * This method is to get the user profile information
     * which includes name, e-mail, university,
     * degree, major, subject taken, subject interested, concentration interested, mentoring on class.
     *
     * @param
     * @return
     */
    Optional<User> findUser(Integer userId);

    Optional<User> findUserByEmail(String email);

    /**
     * This method is to get all universities
     * @return the list of universities
     */
    List<University> findAllUniversity();

    /**
     * This method is to create university for a new user
     * @param userId: the unique id of user
     * @param universityId: the unique id of university
     * @return current user's information
     */
    User createUserUniversity(Integer userId, Integer universityId) throws Exception;

    /**
     * This method is to create degree for a new user
     * @param userId: the unique id of user
     * @param degreeId: the unique id of degree
     * @return current user's information
     */
    User createUserDegree(Integer userId, Integer degreeId) throws Exception;

    /**
     * This method is to get all departments
     * @return the list of departments
     */
    List<Department> findAllMajor();

    /**
     * This method is to create major for a new user
     * @param userId: the unique id of user
     * @param departmentId: the unique id of department
     * @return current user's information
     */
    User createUserDepartment(Integer userId, Integer departmentId) throws Exception;


    /**
     * This method is to create a user's taken courses
     * @param userId: the unique id of user
     * @param courseList: the chosen list of courses
     * @return the chosen course list
     */
    User createTakenCourse(Integer userId, List<Course> courseList) throws Exception;

    /**
     * This method is to update a user's taken courses
     * @param userId: the unique id of user
     * @param courseList: the chosen list of courses
     * @return the chosen course list
     */
    List<Course> updateTakenCourse(Integer userId, List<Course> courseList);


    /**
     * This method is to create a user's interested courses
     * @param userId: the unique id of user
     * @param courseList: the chosen list of interested courses
     * @return the user information
     */
    User createInterestedCourse(Integer userId, List<Course> courseList) throws Exception;

    /**
     * This method is to update a user's interested courses
     * @param userId: the unique id of user
     * @param courseList: the chosen list of interested courses
     * @return the interested course list
     */
    List<Course> updateInterestedCourse(Integer userId, List<Course> courseList);

    /**
     * This method is to update a user's concentration
     * @param userId: the unique id of user
     * @param concentration: the concentration text
     * @return user information
     */
    User createConcentration(Integer userId, String concentration);

    /**
     * This method is to update a user's concentration
     * @param userId
     * @param courseList
     * @return user information
     */
    User createMentorCourse(Integer userId, List<Course> courseList);

    User findOrCreateUser(UserDetailsFromGoogle userDetails);


    void createNewUserOAuthLoginSuccess(String email, String name, AuthenticationProvider provider);

    void updateNewUserOAuthLoginSuccess(User user, String email, String name, AuthenticationProvider google);
}
