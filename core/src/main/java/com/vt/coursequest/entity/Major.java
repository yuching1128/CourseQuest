package com.vt.coursequest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author: EugeneFeng
 * @date: 3/29/23 11:08 AM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "major", schema = "CourseQuest")
public class Major {

    @JsonProperty
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    @JsonProperty
	@Column(name = "name", unique = true)
    String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Major() {}

    public Major(int id) {
        this.id = id;
    }
}
