package com.vt.coursequest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "auth_provider")
	AuthenticationProvider authenticationProvider;

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	Integer id;
	@JoinColumn(name = "university_fid", referencedColumnName = "id")
	@ManyToOne
	University university;
	@JoinColumn(name = "degree_fid", referencedColumnName = "id")
	@ManyToOne
	Degree degree;
	
	@JoinColumn(name = "department_fid", referencedColumnName = "id")
	@ManyToOne
	Department department;
	@ManyToMany
	Set<Course> course = new HashSet<>();
	@ManyToMany
	Set<Course> mentorCourse;
	String concentration;
	@Column(name = "email")
	String email;
	String firstName;
	String lastName;
	@JsonIgnore
	String phone;
	@JsonIgnore
	String password;

	String role;

	Boolean enabled;

	@ManyToMany
	Set<Course> interestedCourse = new HashSet<>();

	public User() {
	}
}
