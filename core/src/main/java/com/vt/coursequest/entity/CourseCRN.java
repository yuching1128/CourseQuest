package com.vt.coursequest.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: EugeneFeng
 * @date: 3/28/23 11:26 PM
 * @description: some desc
 */
@Data
@Entity
@Table(name = "courseCRN")
public class CourseCRN {
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;

	@Column(name = "crnNumber")
	String crnNumber;

	public CourseCRN(String crnNumber) {
		this.crnNumber = crnNumber;
	}
	
	public CourseCRN() {}

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
