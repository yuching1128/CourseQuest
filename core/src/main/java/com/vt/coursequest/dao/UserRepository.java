package com.vt.coursequest.dao;

import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author: EugeneFeng
 * @date: 3/30/23 11:20 PM
 * @description: some desc
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
