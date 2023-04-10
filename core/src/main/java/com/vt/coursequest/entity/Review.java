package com.vt.coursequest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

/**
 * @author: EugeneFeng
 * @date: 3/17/23 11:16 AM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "review", schema = "CourseQuest")
public class Review {
	
	public Review() {}
	
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    @JoinColumn(name = "userId" ,referencedColumnName = "id", updatable = false)
    @OneToOne
     User user;


    @JoinColumn(name = "courseId")
    @ManyToOne
    Course course;


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
    Boolean anonymous;


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

//    public University getUniversity() {
//        return university;
//    }
//
//    public void setUniversity(University university) {
//        this.university = university;
//    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
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

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setWorkload(Workload workload) {
        this.workload = workload;
    }
}

