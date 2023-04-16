package com.vt.coursequest.security.local;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: EugeneFeng
 * @date: 4/16/23 12:11 AM
 * @description: some desc
 */
public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}
