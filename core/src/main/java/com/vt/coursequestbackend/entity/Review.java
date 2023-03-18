package com.vt.coursequestbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @author: EugeneFeng
 * @date: 3/17/23 11:16 AM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "review")
public class Review {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    @JsonIgnore
    @JoinColumn(name = "user_id")
    @OneToOne
    User user;

    @JsonIgnore
    @JoinColumn(name = "course_id")
    @OneToOne
    Course course;

    @JoinColumn(name = "instructor_id")
    @OneToOne
    Instructor instructor;

    @Column(name = "rating")
    Float rating;

    @Column(name = "content")
    String content;

    @Enumerated
    @Column(name = "delivery")
    Delivery delivery;

    @Enumerated
    @Column(name = "workload")
    Workload workload;



}

