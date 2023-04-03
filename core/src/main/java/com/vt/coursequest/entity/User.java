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

	@JsonIgnore
	@JoinColumn(name = "university_fid", referencedColumnName = "id")
	@ManyToOne
	University university;

	@JsonIgnore
	@JoinColumn(name = "degree_fid", referencedColumnName = "id")
	@ManyToOne
	Degree degree;

	@JoinColumn(name = "major_fid", referencedColumnName = "id")
	@ManyToOne()
	Major major;

	@OneToMany
	Set<Course> course;

	@OneToMany
	Set<Course> interestedCourse;

	String Concentration;

	@Column(name = "email")
	String email;

	String firstName;

	String lastName;
	@JsonIgnore
	String phone;
	@JsonIgnore
	String password;

	public User() {
	}
}
