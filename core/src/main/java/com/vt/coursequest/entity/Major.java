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
    int id;

    @JsonProperty
	@Column(name = "name", unique = true)
    String name;
    
    public Major() {}
}
