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

	@JoinColumn(name = "advisorId", referencedColumnName = "id")
	@OneToOne
	User advisor;

	@Column(name = "time")
	Date time;

	@Enumerated
	@Column(name = "advisingTimeslotStatus")
	AdvisingTimeslotStatus advisingTimeslotStatus;

	public Integer getId() { return id; }

	public User getAdvisor() { return advisor; }

	public void setAdvisor(User advisor) { this.advisor = advisor; }

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public AdvisingTimeslotStatus getStatus() { return advisingTimeslotStatus; }

	public void setStatus(AdvisingTimeslotStatus advisingTimeslotStatus) {
		this.advisingTimeslotStatus = advisingTimeslotStatus;
	}
}