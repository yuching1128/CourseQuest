package com.vt.coursequest.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

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

    @Column(name = "crnNumber")
    @JsonProperty
    String crnNumber;

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
