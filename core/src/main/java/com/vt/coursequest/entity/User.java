package com.vt.coursequest.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @author: EugeneFeng
 * @date: 3/1/23 10:15 AM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "user", schema = "CourseQuest", uniqueConstraints = {
		@UniqueConstraint(name = "user_email_unique", columnNames = "email") })
public class User {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;

	@JoinColumn(name = "university_fid", referencedColumnName = "id")
	@ManyToOne
	University university;

	@JoinColumn(name = "degree_fid", referencedColumnName = "id")
	@ManyToOne
	Degree degree;

//	@JoinColumn(name = "major_fid", referencedColumnName = "id")
//	@ManyToOne()
//	Major major;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@JoinColumn(name = "department_fid", referencedColumnName = "id")
	@ManyToOne
	Department department;

	@ManyToMany
	Set<Course> course;

	@ManyToMany
	Set<Course> mentorCourse;

	public Set<Course> getMentorCourse() {
		return mentorCourse;
	}

	public void setMentorCourse(Set<Course> mentorCourse) {
		this.mentorCourse = mentorCourse;
	}

	@ManyToMany
	Set<Course> interestedCourse;

	public String getConcentration() {
		return concentration;
	}

	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}

	String concentration;

	@Column(name = "email")
	String email;

	String firstName;

	String lastName;
	@JsonIgnore
	String phone;
	@JsonIgnore
	String password;

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

//	public Major getMajor() {
//		return major;
//	}
//
//	public void setMajor(Major major) {
//		this.major = major;
//	}

	public Set<Course> getCourse() {
		return course;
	}

	public void setCourse(Set<Course> course) {
		this.course = course;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public User() {
	}
}
