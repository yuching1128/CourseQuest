package com.vt.coursequest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: EugeneFeng
 * @date: 3/17/23 11:16 AM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "review")
public class Review {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    @JsonIgnore
    @JoinColumn(name = "userId")
    @OneToOne
    User user;

    @JsonIgnore
    @JoinColumn(name = "courseId")
    @OneToOne
    Course course;

    @JsonIgnore
    @JoinColumn(name = "universityId")
    @OneToOne
    University university;


    @JoinColumn(name = "instructorId")
    @OneToOne
    Instructor instructor;

    @Column(name = "rating")
    Float rating;

    @Column(name = "content")
    String content;

    @Enumerated
    @Column(name = "delivery")
    Delivery delivery;

    @Enumerated
    @Column(name = "workload")
    Workload workload;

    @Column(name = "isAnonymous")
    Boolean isAnonymous;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Float getRating() {
        return rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Workload getWorkload() {
        return workload;
    }

    public void setWorkload(Workload workload) {
        this.workload = workload;
    }
}

