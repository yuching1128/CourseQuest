package com.vt.coursequestbackend.entity;

import lombok.Data;

/**
 * @author: EugeneFeng
 * @date: 3/1/23 10:15 AM
 * @description: some desc
 */

@Data
public class User {
    int id;
    String firstName;
    String lastName;
    String phone;
}
