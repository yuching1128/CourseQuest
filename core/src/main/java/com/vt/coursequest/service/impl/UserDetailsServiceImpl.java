package com.vt.coursequest.service.impl;

import com.vt.coursequest.dao.UserRepository;
import com.vt.coursequest.entity.User;
import com.vt.coursequest.security.local.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @author: EugeneFeng
 * @date: 4/15/23 11:39 PM
 * @description: some desc
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            return new MyUserDetails(user.get());
        } else throw new UsernameNotFoundException("Could not find User");

    }
}
