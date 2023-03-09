package com.vt.coursequestbackend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

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
    String crnNumber;

    @Column(name = "name")
    String name;

    @Column(name = "rating")
    Float rating;

    @Column(name = "description")
    String description;

}
