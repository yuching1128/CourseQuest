package com.vt.coursequest.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
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
@Table(name = "course", schema = "CourseQuest")
public class Course implements Serializable {

	public Course() {
	}

	@Column(name = "id", updatable = false)
	@Id
	int id;

	@JsonIgnore
	@JoinColumn(name = "university_id")
	@ManyToOne
	@MapsId
	University university;

	@JsonIgnore
	@JoinColumn(name = "degree_id")
	@ManyToOne
	Degree degree;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	// @JoinColumn(name = "course_instructor_fid")
	Set<Instructor> instructor = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	// @JoinColumn(name = "course_crn_fid")
	private Set<CourseCRN> courseCRNs = new HashSet<>();

	@Column(name = "name", nullable = false)
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
		Set<Instructor> uniqueInstructors = new HashSet<>(instructor);
		this.instructor = uniqueInstructors;
	}

	public Set<CourseCRN> getCourseCRNs() {
		return courseCRNs;
	}

	public void setCourseCRNs(Set<CourseCRN> courseCRNs) {
		this.courseCRNs = courseCRNs;
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

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Course courseObj = (Course) obj;
		return id == courseObj.id;
	}

	@Override
	public int hashCode() {
		return id;
	}

}
