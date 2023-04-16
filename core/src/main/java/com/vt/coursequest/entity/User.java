package com.vt.coursequest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author: EugeneFeng
 * @date: 3/1/23 10:15 AM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "user", schema = "CourseQuest", uniqueConstraints = {
        @UniqueConstraint(name = "user_email_unique", columnNames = "email")})
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
    //	@JoinColumn(name = "major_fid", referencedColumnName = "id")
//	@ManyToOne()
//	Major major;
    @JoinColumn(name = "department_fid", referencedColumnName = "id")
    @ManyToOne
    Department department;
    @ManyToMany
    Set<Course> course;
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
    Set<Course> interestedCourse;

    public User() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Course> getMentorCourse() {
        return mentorCourse;
    }

    public void setMentorCourse(Set<Course> mentorCourse) {
        this.mentorCourse = mentorCourse;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
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

//	public Major getMajor() {
//		return major;
//	}
//
//	public void setMajor(Major major) {
//		this.major = major;
//	}

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

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
}
