package com.vt.coursequest.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: EugeneFeng
 * @date: 3/9/23 11:23 PM
 * @description: some desc
 */
@Data
@Entity
@Table(name = "degree")
public class Degree {
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;

	@Column(name = "name")
	String name;

	public Degree(String name) {
		this.name = name;
	}
	
	public Degree() {}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Degree degreeObj = (Degree) obj;
		return name.equals(degreeObj.name);
	}
}
