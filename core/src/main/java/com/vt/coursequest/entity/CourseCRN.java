package com.vt.coursequest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author: EugeneFeng
 * @date: 3/28/23 11:26 PM
 * @description: some desc
 */
@Data
@Entity
@Table(name = "courseCRN", schema = "CourseQuest")
public class CourseCRN {
	
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	Integer id;

	@Column(name = "crnNumber")
	String crnNumber;
	

	public String getCrnNumber() {
		return crnNumber;
	}

	public void setCrnNumber(String crnNumber) {
		this.crnNumber = crnNumber;
	}

	public CourseCRN(String crnNumber) {
		this.crnNumber = crnNumber;
	}

	public CourseCRN() {
	}

	@Override
	public int hashCode() {
		return crnNumber.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		CourseCRN crnObj = (CourseCRN) obj;
		return crnNumber.equals(crnObj.crnNumber);
	}
}
