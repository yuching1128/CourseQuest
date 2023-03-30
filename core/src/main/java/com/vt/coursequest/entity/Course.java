package com.vt.coursequest.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

	public Course() {
	}

	@Column(name = "id")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@OneToMany
	Set<Instructor> instructor = new HashSet<>();

	@JoinColumn(referencedColumnName = "id")
	@OneToMany
	Set<CourseCRN> CourseCRNs = new HashSet<>();

	@Column(name = "name")
	@JsonProperty
	String name;

	@Column(name = "rating")
	@JsonProperty
	Float rating;

	@Column(name = "description")
	@JsonProperty
	String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public Set<Instructor> getInstructor() {
		return instructor;
	}

	public void setInstructor(Set<Instructor> instructor) {
		this.instructor = instructor;
	}

	public Set<CourseCRN> getCourseCRNs() {
		return CourseCRNs;
	}

	public void setCourseCRNs(Set<CourseCRN> courseCRNs) {
		CourseCRNs = courseCRNs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
