package com.vt.coursequest.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: EugeneFeng
 * @date: 3/1/23 10:15 AM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "course")
public class Course {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    @JsonIgnore
    @JoinColumn(name = "university_id")
    @OneToOne
    University university;

    @JsonIgnore
    @JoinColumn(name = "degree_id")
    @OneToOne
    Degree degree;

    @JsonIgnore
    @JoinColumn(name = "instructor_id")
    @OneToOne
    Instructor instructor;

//    @Column(name = "crnNumber")
//    @JsonProperty
//    String crnNumber;

    @JoinColumn(referencedColumnName = "id")
    @OneToMany
    List<CourseCRN> CourseCRNs = new ArrayList<>();

    @Column(name = "name")
    @JsonProperty
    String name;

    @Column(name = "rating")
    @JsonProperty
    Float rating;

    @Column(name = "description")
    @JsonProperty
    String description;

}
