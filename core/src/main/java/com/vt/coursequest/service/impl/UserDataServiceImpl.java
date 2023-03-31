package com.vt.coursequest.service.impl;

import com.vt.coursequest.dao.UserRepository;
import com.vt.coursequest.entity.User;
import com.vt.coursequest.service.UserDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author: EugeneFeng
 * @date: 3/30/23 11:19 PM
 * @description: some desc
 */
@Service
public class UserDataServiceImpl implements UserDataService {

    @Resource
    private UserRepository userRepository;

    @Override
    public Optional<User> findUser(Integer userId) {
        return userRepository.findById(userId);
    }
}
