package com.vt.coursequestbackend.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: EugeneFeng
 * @date: 3/8/23 11:25 AM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "university")
public class University {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    String name;
}
