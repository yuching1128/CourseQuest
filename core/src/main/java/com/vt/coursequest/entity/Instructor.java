package com.vt.coursequest.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: EugeneFeng
 * @date: 3/17/23 5:43 PM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "instructor")
public class Instructor {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;

	@Column(name = "name")
	String name;

	public Instructor(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Instructor instructor = (Instructor) obj;
		return name.equals(instructor.name);
	}
	
	public Instructor() {}
}
