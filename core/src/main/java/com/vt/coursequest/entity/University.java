package com.vt.coursequest.entity;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    
    @JsonIgnore
    @JoinColumn(name = "university_id")
    @OneToOne
    University university;
}
