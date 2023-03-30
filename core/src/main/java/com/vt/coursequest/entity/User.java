package com.vt.coursequest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @author: EugeneFeng
 * @date: 3/1/23 10:15 AM
 * @description: some desc
 */


@Data
@Entity
@Table(name = "user")
public class User {

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

    @JoinColumn(name = "major_id")
    @OneToOne
    Major major;

    String Concentration;

    String email;

    String firstName;

    String lastName;
    @JsonIgnore
    String phone;
    @JsonIgnore
    String password;
    
    public User() {}
}
