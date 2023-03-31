package com.vt.coursequest.service;

import com.vt.coursequest.entity.User;

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
     * @param userId
     * @return
     */
    Optional<User> findUser(Integer userId);

}
