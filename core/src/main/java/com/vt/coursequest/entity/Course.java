package com.vt.coursequest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Course() {
	}
	
	@UpdateTimestamp
	@Column(name = "modificationDate")
	private Date modificationDate;

	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	Integer id;

	@Column(name = "courseNum", updatable = false, nullable = false)
	String courseNum;

	@JsonProperty
	@JoinColumn(name = "university_id")
	@ManyToOne
	University university;

	@JoinColumn(name = "degree_id")
	@ManyToOne
	Degree degree;

	@JoinColumn(name = "dept_id")
	@ManyToOne
	Department dept;

	@JoinColumn(name = "level_id")
	@ManyToOne
	Level level;



	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinColumn(name = "course_instructor_fid")
	Set<Instructor> instructor = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinColumn(name = "course_crn_fid")
	private Set<CourseCRN> courseCRNs = new HashSet<>();

	@Column(name = "name", nullable = false)
	@JsonProperty
	String name;

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	@Column(name = "rating")
	@JsonProperty
	Double rating;

	@Column(name = "description", columnDefinition = "LONGTEXT")
	@JsonProperty
	String description;

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
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



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Course courseObj = (Course) obj;
		String unique = dept + courseNum + university.id;
		return unique.equals(courseObj.dept + courseObj.courseNum + courseObj.university.id);
	}

	@Override
	public int hashCode() {
		return id;
	}

}
