package com.vt.coursequest.entity;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @description: some desc
 */
@Data
@Entity
@Table(name = "department")
public class Department {
	
	@JsonProperty
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;

	@JsonProperty
	@Column(name = "name")
	String name;

	@JsonIgnore
    @JoinColumn(name = "university_id")
	@ManyToOne
    University university;
	
	public Department() {}
}
