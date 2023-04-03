package com.vt.coursequest.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author: EugeneFeng
 * @date: 3/17/23 5:43 PM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "instructor", schema = "CourseQuest")
public class Instructor {

	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	Integer id;

	@Column(name = "name", nullable = false)
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "instructor")
	private Set<Course> courses = new HashSet<>();

	public Instructor(String name) {
		this.name = name;
		this.id = null;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Instructor instructor = (Instructor) obj;
		return name.equals(instructor.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public Instructor() {
	}
}
