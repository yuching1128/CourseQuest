package com.vt.coursequest.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

	@JoinColumn(name = "major_fid", referencedColumnName = "id")
	@ManyToOne()
	Major major;

	@OneToMany
	Set<Course> course;

	public void setConcentration(Set<Major> concentration) {
		this.concentration = concentration;
	}

	@OneToMany
	Set<Course> interestedCourse;

	@OneToMany
	Set<Major> concentration;

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

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Set<Course> getCourse() {
		return course;
	}

	public void setCourse(Set<Course> course) {
		this.course = course;
	}

	public Set<Course> getInterestedCourse() {
		return interestedCourse;
	}

	public void setInterestedCourse(Set<Course> interestedCourse) {
		this.interestedCourse = interestedCourse;
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
