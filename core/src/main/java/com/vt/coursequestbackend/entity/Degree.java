package com.vt.coursequestbackend.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: EugeneFeng
 * @date: 3/9/23 11:23 PM
 * @description: some desc
 */
@Data
@Entity
@Table(name = "degree")
public class Degree {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    String name;
}
