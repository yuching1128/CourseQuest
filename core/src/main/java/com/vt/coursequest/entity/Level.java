package com.vt.coursequest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @description: some desc
 */
@Data
@Entity
@Table(name = "level", schema = "CourseQuest")
public class Level {
	
	@JsonProperty
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;

	@JsonProperty
	@Column(name = "name", unique = true)
	String name;

	@JsonIgnore
    @JoinColumn(name = "university_id")
	@ManyToOne
    University university;
	
	public Level() {}
}
