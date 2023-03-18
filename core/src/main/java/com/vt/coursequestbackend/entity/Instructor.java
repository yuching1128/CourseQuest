package com.vt.coursequestbackend.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: EugeneFeng
 * @date: 3/17/23 5:43 PM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "instructor")
public class Instructor {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    @Column(name = "name")
    String name;
}
