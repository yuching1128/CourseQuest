package com.vt.coursequest.controller;

import com.vt.coursequest.entity.User;
import com.vt.coursequest.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/api/user/{userId}/profile")
    public ResponseEntity<Optional<User>> getUserProfile(@PathVariable String userId) {
        return new ResponseEntity<>(uds.findUser(Integer.parseInt(userId)), HttpStatus.OK);
    }

}
