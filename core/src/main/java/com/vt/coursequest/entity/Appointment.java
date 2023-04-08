package com.vt.coursequest.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "appointment", schema = "CourseQuest")
public class Appointment {
    public Appointment() {}

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    private Date createdAt;

    @JoinColumn(name = "advisingTimeslotId", referencedColumnName = "id")
    @OneToOne
    AdvisingTimeslot advisingTimeslot;

    @JoinColumn(name = "advisorId", referencedColumnName = "id")
    @OneToOne
    User advisor;

    @JoinColumn(name = "adviseeId", referencedColumnName = "id")
    @OneToOne
    User advisee;

    @Enumerated
    @Column(name = "appointmentStatus")
    AppointmentStatus appointmentStatus;

    @JoinColumn(name = "courseId")
    @OneToOne
    Course course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public AdvisingTimeslot getAdvisingTimeslot() { return advisingTimeslot; }

    public void setAdvisingTimeslot(AdvisingTimeslot advisingTimeslot) { this.advisingTimeslot = advisingTimeslot; }

    public User getAdvisor() { return advisor; }

    public void setAdvisor(User advisor) { this.advisor = advisor; }

    public User getAdvisee() { return advisee; }

    public void setAdvisee(User advisee) { this.advisee = advisee; }

    public AppointmentStatus getAppointmentStatus() { return appointmentStatus; }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) { this.appointmentStatus = appointmentStatus; }

    public Course getCourse() { return this.course; }

    public void setCourse(Course course) { this.course = course; }

}
