package com.vt.coursequest.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: EugeneFeng
 * @date: 4/3/23 11:23 PM
 * @description: Time slots (1-hour increments) for available and booked advising appointments
 */
@Data
@Entity
@Table(name = "advisingTimeslot")
public class AdvisingTimeslot {
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;

	@JoinColumn(name = "userId")
	@OneToOne
	User user;

	@Column(name = "time")
	Date time;

	@Column(name = "subject")
	String subject;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}